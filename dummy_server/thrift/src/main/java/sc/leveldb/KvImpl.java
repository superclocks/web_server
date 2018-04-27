/**
 * Created by zhongchao03 on 2018/1/19.
 */
package sc.leveldb;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class KvImpl implements KvService.Iface  {
    public KvImpl(){};
    @Override
    public String getValue(String key) throws TException{
        return key + ":super clocks";
    }
    @Override
    public java.util.List<String> mgetValues(java.util.List<String> keys) throws TException{
        List<String> r = new ArrayList<String>();
        for(int i = 0; i < keys.size(); i++){
            r.add(keys.get(i) + ":super clocks");
        }
        return null;
    }
    @Override
    public boolean setValue(String key, String value) throws TException{
        System.out.print(key + ":" + value + "\n");
        return true;
    }
    @Override
    public boolean msetValues(List<String> keys, java.util.List<String> values) throws TException{
        for(int i = 0; i < keys.size(); i++){
            System.out.print(keys.get(i) + ":" + values.get(i) + "\n");
        }
        return true;
    }
}
