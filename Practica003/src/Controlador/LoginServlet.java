package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOFactory;
import DAO.TelefonoDAO;
import DAO.UsuarioDAO;
import Modelo.Usuario;


@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession sesion = request.getSession(true);
		sesion.setAttribute("accesos", sesion.getAttribute("accesos"));
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html:charset=UTF-8");
		response.setContentType("text/html:charset=UTF-8");
		System.out.print("Iniciar Sesion \n");
		
		UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
		String correo ="";
		String contrasena = "";
		String url = null;
		int i=0;
		
		String accion  = request.getParameter("resp");
		Usuario user = new Usuario();
		
		
		if(accion.equals("Login")) {
			correo = request.getParameter("user");
			contrasena = request.getParameter("password");
			user = usuarioDAO.buscar(correo, contrasena);
		}
			System.out.println("retorno de usuario: "+ usuarioDAO.buscar(correo, contrasena));
			url="/JSPs/Usuario.jsp";
			try {
				if(user != null) {
					TelefonoDAO telfDAO = DAOFactory.getFactory().getTelefonoDAO();
					
					request.setAttribute("telefono", telfDAO.buscarCedula(user.getCedula()));
					request.setAttribute("usuario", user);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}else {
					getServletContext().getRequestDispatcher("/JSPs/login.jsp").forward(request, response);
				}
			}catch(Exception e) {
				System.out.println(">>>Error:LoginServlet:DOPOST "+e.getMessage());
			}
		

	}

}