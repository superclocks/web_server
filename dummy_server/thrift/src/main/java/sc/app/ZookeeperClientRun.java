package sc.app;

import sc.server.api.KvService;
import sc.zookeeper.ThriftServiceClientProxyFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket; import org.apache.thrift.transport.TTransport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


/**
 * Created by zhongchao03 on 2018/5/20.
 */
public class ZookeeperClientRun {

    public static void main(String[] args) {
        //simple();
        spring();
    }
    public static void spring() { try {
        final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-thrift-client.xml");
        KvService.Iface kvSerivce = (KvService.Iface)context.getBean("kvSerivce");
        System.out.println(kvSerivce.getValue("hello--echo"));
        //关闭连接的钩子
        Runtime.getRuntime().addShutdownHook(
                new Thread() {public void run() {
                Map<String,ThriftServiceClientProxyFactory> clientMap = context.getBeansOfType(ThriftServiceClientProxyFactory.class);
                for(Map.Entry<String, ThriftServiceClientProxyFactory> client :
                        clientMap.entrySet()){
                    System.out.println("serviceName : "+client.getKey() + ",class obj: "+client.getValue());
                    client.getValue().close(); } } });
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
