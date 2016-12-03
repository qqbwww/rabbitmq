package basic;

import com.rabbitmq.client.*;
import utils.FactoryBuilder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by qianqb on 16/12/3.
 */
public class Send {

  public static final String QUEUE_NAME = "hello-queue";
  public static final String EXCHANGE = "hello-exchange";
  public static final String ROUTING_KEY = "hola";

  public static void main(String[] args) throws IOException, TimeoutException {
    //建立到代理服务器的连接
    ConnectionFactory factory = FactoryBuilder.getLocal();
    Connection connection = factory.newConnection();
    //获取信道
    Channel channel = connection.createChannel();
    //声明交换器
    channel.exchangeDeclare(EXCHANGE,"direct");
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//    channel.queueDeclarePassive(QUEUE_NAME);
    String message  = "Hello World!";

    //创建纯文本消息
    AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().contentType("text/plain").build();
    //发布消息
    channel.basicPublish(EXCHANGE,ROUTING_KEY,properties,message.getBytes());
    System.out.println(" [X] sent '" + message + "'");

    channel.close();
    connection.close();

  }


}
