package basic;

import com.rabbitmq.client.*;
import utils.FactoryBuilder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by qianqb on 16/12/3.
 */
public class Receiver {

  public static final String QUEUE_NAME = "hello-queue";
  public static final String EXCHANGE = "hello-exchange";
  public static final String ROUTING_KEY = "hola";

  public static void main(String[] argv)
      throws java.io.IOException,
      java.lang.InterruptedException, TimeoutException {

    ConnectionFactory factory = FactoryBuilder.getLocal();
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE,"direct");
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    channel.queueBind(QUEUE_NAME,EXCHANGE,ROUTING_KEY);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel){
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body,"UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME,true,consumer);

//    channel.close();
//    connection.close();
  }
}