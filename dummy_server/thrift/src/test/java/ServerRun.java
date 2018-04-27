import org.springframework.context.support.ClassPathXmlApplicationContext;
import sc.leveldb.ThriftServerProxy;

/**
 * Created by zhongchao03 on 2018/1/20.
 */
public class ServerRun {
    public static void main(String[] args) {
        //直接启动服务端程序
        //ThriftServer kvServiceStart = new ThriftServer();
        //kvServiceStart.threadedSelectorServerStart();
        //通过Spring启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:kvserver.xml");
        ThriftServerProxy thriftServerProxy = (ThriftServerProxy) context.getBean(ThriftServerProxy.class);
        thriftServerProxy.start();

    }
}
