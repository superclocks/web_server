package sc.leveldb;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;
/**
 * Created by zhongchao03 on 2018/1/20.
 */
public class ThriftClient {
    public static void a()
    {
        try {
            TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 8191, 5000));
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            KvService.Client client = new KvService.Client(protocol);
            transport.open();
            boolean string = client.setValue("fuck", "haha");
            System.out.println(string);
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void nonblockingSocket() throws Exception{
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8080));
        TProtocol protocol = new TBinaryProtocol(transport);
        KvService.Client client = new KvService.Client(protocol);
        transport.open();
        int i = 5;
        while (i > 0) {
            System.out.println("client调用返回：" + client.getValue("张三"));
            i--;
        }
        transport.close();
    }

    public static void main(String[] args)throws Exception{
        nonblockingSocket();
    }
}

