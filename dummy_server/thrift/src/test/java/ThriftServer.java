import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import sc.server.api.KvService;
import sc.server.service.KvServiceImpl;

/**
 * Created by zhongchao03 on 2018/1/20.
 */
public class ThriftServer {
    public void simpleServerStart(){
        TServerSocket serverTransport = null;
        try {
            System.out.println("Starting Thrift Server......");
            TProcessor processor = new KvService.Processor<KvService.Iface>(new KvServiceImpl());
            serverTransport = new TServerSocket(8191);
            TTransportFactory transportFactory = new TFramedTransport.Factory();
            Factory factory = new Factory();
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.protocolFactory(factory);
            tArgs.transportFactory(transportFactory);
            tArgs.processor(processor);
            // 简单的单线程服务模型，一般用于测试
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            System.out.println("Starting Thrift Server......Error!!!");
            e.printStackTrace();
        } finally {
            serverTransport.close();
        }
    }

    public void threadedSelectorServerStart(){
        try {
            System.out.println("HelloWorld TNonblockingServer start ....");
            TNonblockingServerTransport serverSocket=new TNonblockingServerSocket(8888);
            TThreadedSelectorServer.Args serverParams=new TThreadedSelectorServer.Args(serverSocket);
            serverParams.protocolFactory(new Factory());
            serverParams.processor(new KvService.Processor<KvService.Iface>(new KvServiceImpl()));
            TServer server=new TThreadedSelectorServer(serverParams); //简单的单线程服务模型，常用于测试
            server.serve();
        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args) {
        start();
    }*/
}
