import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import javax.naming.*;  
import javax.jms.*;  
  
public class MySender {
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("myQueueConFac");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();
        
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
            Queue t = (Queue)ctx.lookup("myQueue");
               
            QueueSender sender = session.createSender(t);
  
            TextMessage msg = session.createTextMessage();

            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println("Введите сообщение; end для завершения: ");
                String s = buf.readLine();
                if (s.equals("end"))
                    break;  
                msg.setText(s);

                sender.send(msg);
                System.out.println("Отправлено");
            }
            
            connection.close();
        } catch(Exception e) {
        	System.out.println(e);
        }  
    }  
}  