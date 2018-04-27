package sc.leveldb;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * Created by zhongchao03 on 2018/1/20.
 */
public class ThriftServerProxy {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int port;// 端口
    private String serviceInterface;// 实现类接口
    private Object serviceImplObject;// 实现类

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void start() {
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("HelloWorld TNonblockingServer start ....");
                    TNonblockingServerTransport serverSocket=new TNonblockingServerSocket(getPort());
                    TThreadedSelectorServer.Args serverParams=new TThreadedSelectorServer.Args(serverSocket);

                    // 实现类处理类class
                    Class Processor = Class.forName(getServiceInterface() + "$Processor");
                    // 接口
                    Class Iface = Class.forName(getServiceInterface() + "$Iface");
                    // 接口构造方法类
                    Constructor con = Processor.getConstructor(Iface);
                    TProcessor processor = (TProcessor) con.newInstance(serviceImplObject);

                    serverParams.protocolFactory(new TBinaryProtocol.Factory());
                    serverParams.processor(processor);
                    TServer server=new TThreadedSelectorServer(serverParams); //简单的单线程服务模型，常用于测试
                    server.serve();
                } catch (TTransportException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public Object getServiceImplObject() {
        return serviceImplObject;
    }

    public void setServiceImplObject(Object serviceImplObject) {
        this.serviceImplObject = serviceImplObject;
    }
}
