package servidor_web_dinamico;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JTextArea;


/**
 *
 * @author pc
 */
public class Servlet_Home extends HttpServlet {
    
    public JTextArea jTextArea_01;

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        jTextArea_01.append( "\n-----------------------------------------------------------------------------------------------------------------------" );
        try{ jTextArea_01.append( "IP do Cliente                - " + request.getRemoteAddr()        + "\n" ); }catch(Exception e){}
        //String getSession_getId = ""; try{ getSession_getId = request.getSession(true).getId(); jTextArea_01.append( "request.getSession(true).getId() - " + getSession_getId + "\n" ); }catch(Exception e){}      
        try{ jTextArea_01.append( "request.getMethod()            - " + request.getMethod()            + "\n" ); }catch(Exception e){}
        String getRequestURI = ""; try{ getRequestURI = request.getRequestURI(); jTextArea_01.append( "request.getRequestURI()        - " + getRequestURI + "\n" ); }catch(Exception e){}
        String getRequestURL = ""; try{ getRequestURL = request.getRequestURL().toString(); jTextArea_01.append( "request.getRequestURL().toString() - " + getRequestURL + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getAuthType()          - " + request.getAuthType()          + "\n" ); }catch(Exception e){}   
        try{ jTextArea_01.append( "request.getCharacterEncoding() - " + request.getCharacterEncoding() + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getContentLength()     - " + request.getContentLength()     + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getContentType()       - " + request.getContentType()       + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getContextPath()       - " + request.getContextPath()       + "\n" ); }catch(Exception e){}            
        try{ jTextArea_01.append( "request.getPathInfo()          - " + request.getPathInfo()          + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getPathTranslated()    - " + request.getPathTranslated()    + "\n" ); }catch(Exception e){}                                                                        
        try{ jTextArea_01.append( "request.getLocale()            - " + request.getLocale()            + "\n" ); }catch(Exception e){}            
        try{ jTextArea_01.append( "request.getLocalAddr()         - " + request.getLocalAddr()         + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getLocalPort()         - " + request.getLocalPort()         + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getLocalName()         - " + request.getLocalName()         + "\n" ); }catch(Exception e){}            
        try{ jTextArea_01.append( "request.getParameterMap()      - " + request.getParameterMap()      + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getParameterNames()    - " + request.getParameterNames()    + "\n" ); }catch(Exception e){}            
        try{ jTextArea_01.append( "request.getHeaderNames()       - " + request.getHeaderNames()       + "\n" ); }catch(Exception e){}
        try{ jTextArea_01.append( "request.getParameterValues('') - " + request.getParameterValues("") + "\n" ); }catch(Exception e){}                                    
        try{ 
            Cookie[] cookies = request.getCookies ();
            for (int i = 0; cookies != null && i < cookies.length; i++) {
                Cookie cookie = cookies [i];
                
                if (cookie != null) {
                    String nome = cookie.getName();
                    String valor = cookie.getValue();
                    String dominio = cookie.getDomain();
                    boolean seguro = cookie.getSecure();
                    int versao = cookie.getVersion();
                    String comentario = cookie.getComment();
                    int maxAge = cookie.getMaxAge();
                    String path = cookie.getPath();
                    
                    try{ jTextArea_01.append( "Cookie -" + 
                            " - Nome: " + nome + 
                            " - Valor: " + valor + 
                            " - Domínio: " + dominio + 
                            " - Seguro: " + seguro + 
                            " - Versão: " + versao + 
                            " - Comentário: " + comentario + 
                    "\n" ); }catch(Exception e){}    
                    
                }
            }
        }catch(Exception e){} 
        
        String externo = System.getProperty( "user.dir" );
        
        try {  
            
            if ( getRequestURI.equals("/") ) {
                
                response.sendRedirect( "/00_Externo/index.html" );
                //RequestDispatcher rd = getServletContext().getRequestDispatcher("/00_Externo/index.html"); 
                //rd.forward(request, response);
            }
            else if ( getRequestURI.equals("/00_Externo/index.html") ) {
                
                File arquivo = null;
                try{ arquivo = new File( externo + "/00_Externo/index.html" ); }catch(Exception e){}
                
                //se o arquivo existir então criamos a reposta de sucesso, com status 200
                if (arquivo.exists()) {
                    
                    response.setContentType("text/html");
                    response.setStatus(HttpServletResponse.SC_OK);
                    
                    //PrintWriter out = response.getWriter();
                    //byte[] conteudo = Files.readAllBytes(arquivo.toPath());
                    OutputStream out = response.getOutputStream();
                    out.write( Files.readAllBytes(arquivo.toPath()) );
                    out.flush();
                    out.close();
                                        
                } else {
                    
                    response.setContentType("text/html");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    
                    try{ arquivo = new File( externo + "/00_Externo/404.html" ); }catch(Exception e){}
                    OutputStream out = response.getOutputStream();
                    out.write( Files.readAllBytes(arquivo.toPath()) );
                    out.flush();
                    out.close();
                }
            }
                                                          
        }catch(Exception e){ 
            e.printStackTrace();            
            PrintWriter out = response.getWriter();
            out.println("<HTML><HEAD>");
            out.println("<TITLE>JMarySystems</TITLE>");
            out.println("</HEAD><BODY>");
            out.println( e.getMessage() );
            out.println("</BODY></HTML>");
            out.flush();
            out.close();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
