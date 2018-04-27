import org.apache.thrift.TException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sc.leveldb.KvService;
import sc.leveldb.ThriftClientProxy;

/**
 * Created by zhongchao03 on 2018/1/21.
 */
public class ClientRun {
    public static void main(String[] args)throws TException{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kvclient.xml");

        ThriftClientProxy thriftClientProxy = (ThriftClientProxy) context.getBean(ThriftClientProxy.class);
        KvService.Iface client = (KvService.Iface)thriftClientProxy.getClient(KvService.class);
        int i = 5;
        while (i > 0) {
            System.out.println("client调用返回：" + client.getValue("张三"));
            i--;
        }

    }
}
