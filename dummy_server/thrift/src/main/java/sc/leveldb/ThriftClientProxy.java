package sc.leveldb;

import org.apache.thrift.transport.TFramedTransport;
import sc.pool.ConnectionManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

import java.lang.reflect.Constructor;

/**
 * Created by zhongchao03 on 2018/1/20.
 */
public class ThriftClientProxy {
    private ConnectionManager connectionManager;

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    public Object getClient(Class clazz) {
        Object result = null;
        try {
            TTransport transport = connectionManager.getSocket();
            TProtocol protocol = new TBinaryProtocol(new TFramedTransport(transport));
            Class client = Class.forName(clazz.getName() + "$Client");
            Constructor con = client.getConstructor(TProtocol.class);
            result = con.newInstance(protocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
