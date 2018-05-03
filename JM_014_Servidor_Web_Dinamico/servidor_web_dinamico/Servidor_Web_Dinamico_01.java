
package servidor_web_dinamico;

import javax.swing.JTextArea;
import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author NewUser
 */
public class Servidor_Web_Dinamico_01 {
    
    int int_porta;
    JTextArea jTextArea_01;
    
    public Servidor_Web_Dinamico_01(int int_porta2, JTextArea jTextArea_012) throws IOException {
        
        int_porta = int_porta2;
        jTextArea_01 = jTextArea_012;      
        jTextArea_01.append( "Servidor Iniciado Com Sucesso!" + "\n" );
        iniciar();        
    }
    
    private void iniciar(){
        
        new Thread() { @Override public void run() { try{
            /*
            Server server = new Server(int_porta);
            ResourceHandler resource_handler = new ResourceHandler();
            //resource_handler.setDirectoriesListed(true);
            resource_handler.setWelcomeFiles(new String[]{ "index.html" });
            resource_handler.setResourceBase(".");
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
            server.setHandler(handlers);
            server.start();
            server.join();
            */        
           
            Server server = new Server(int_porta);
            ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            contextHandler.setContextPath("/");
            Servlet_Home Servlet_Home = new Servlet_Home();
            Servlet_Home.jTextArea_01 = jTextArea_01;
            ServletHolder sh = new ServletHolder(Servlet_Home);
            contextHandler.addServlet(sh, "/*");
            server.setHandler(contextHandler);
            server.start();
            server.join();
            
        } catch( Exception e ){ 
            e.printStackTrace();
            jTextArea_01.append( "Erro ao iniciar o servidor: " + e.getMessage() + "\n" );
        } } }.start();     
    }        
}
