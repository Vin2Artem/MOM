import javax.jms.*;  
import javax.naming.InitialContext;  
  
public class MyReceiver {  
    public static void main(String[] args) {  
        try {
            InitialContext ctx = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("myQueueConFac");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue t = (Queue)ctx.lookup("myQueue");

            QueueReceiver receiver = session.createReceiver(t);
            
            MyListener listener = new MyListener();
            
            receiver.setMessageListener(listener);
              
            System.out.println("Ожидание сообщений...");
            while (true) {
                Thread.sleep(1000);
            }
        } catch(Exception e) {
        	System.out.println(e);
        }
    }  
  
}  