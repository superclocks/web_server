package sc.pool;
import org.apache.thrift.transport.TSocket;
/**
 * Created by zhongchao03 on 2018/1/20.
 */
public interface ConnectionProvider {
    /**
     * 取链接池中的一个链接
     * @return TSocket
     */
    TSocket getConnection();

    /**
     * 返回链接
     * @param socket
     */
    void returnCon(TSocket socket);
}
