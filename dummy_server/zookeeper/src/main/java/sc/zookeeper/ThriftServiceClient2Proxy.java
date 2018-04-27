package sc.zookeeper;

import org.apache.thrift.TServiceClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * Created by zhongchao03 on 2018/1/21.
 */
public class ThriftServiceClient2Proxy implements InvocationHandler{
    private  TServiceClient client;

    public ThriftServiceClient2Proxy(TServiceClient client){
        this.client = client;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(client, args);
        } catch (Exception e) {
            throw e;
        }
    }
}