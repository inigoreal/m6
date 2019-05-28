package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("accion");
		if (op.equals("alta")) {
			 System.out.println("depurando");
			 response.sendRedirect("alta.jsp");
				
			 }
		if (op.equals("lista")) {
			 System.out.println("lista");
			 
			 response.sendRedirect("lista.jsp");
			 DBManager db = new DBManager();
			 db.obtenerDepartamento();
			 db.cerrarDB();
			 //response.getWriter().append(depart.getDnombre());
			 
			 }
		if (op.equals("insertar")) {
			control.Departamento dep = (control.Departamento) request.getAttribute("depart");
			System.out.println(dep.getDeptno());
			
			DBManager db = new DBManager();
			db.insertarElemento(dep);
			db.cerrarDB();
			
		}
		System.out.println("Estoy en el servlet"+op);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
