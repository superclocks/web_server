package sc.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhongchao03 on 2018/5/20.
 */
public class ZookeeperServerRun {
    public static void main(String[] args) {
        try {
            new ClassPathXmlApplicationContext("classpath:spring-context-thrift-server.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("服务启动成功"); }
}
