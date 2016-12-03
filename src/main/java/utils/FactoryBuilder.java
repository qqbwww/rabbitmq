package utils;

import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by qianqb on 16/12/3.
 */
public class FactoryBuilder {

  public static ConnectionFactory getDev(){
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.4.210");
    factory.setUsername("test");
    factory.setPassword("test");
    return factory;
  }

  public static ConnectionFactory getLocal(){
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    factory.setUsername("guest");
    factory.setPassword("guest");
    return factory;
  }
}
