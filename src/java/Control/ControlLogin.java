
package Control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Entidad.Login;
import Operaciones.OperacionesSQL;


public class ControlLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OperacionesSQL operaciones = new OperacionesSQL();
        String accion = request.getParameter("accion");
        if(accion.equals("Ingresar")){
            Login lo = new Login(request.getParameter("txtUsuario"),
                                request.getParameter("txtClave"));
            
            if(operaciones.validar(lo)==1){
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
               
            }else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
               
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
