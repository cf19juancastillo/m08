package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//Usamos doPost ya que el envío es mediante un formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user= request.getParameter("username");
        String pass = request.getParameter("password");
        
        //Si el usuario es correcto (admin -admin) redirigimos a inicio.html, en caso contrario, a error.html
        if (user.equals("admin") && pass.equals("admin")){
            response.sendRedirect("menu.html");
        }else{
            response.sendRedirect("error.html");
        }        
	}
}
