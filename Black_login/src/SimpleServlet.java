import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().write("GET/POST response");
	}
	
	//implement service() method
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException{
		String yesOrNoParam = request.getParameter("param");
		PrintWriter writer = response.getWriter();
		writer.write("<html><body>GET/POST response<br></body></html>");
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		session.setAttribute("userName", "theUserName");
		String userName = (String) session.getAttribute("userName");
		
		((HttpServletResponse) response).setHeader("Content-type", "text/html");
		PrintWriter writer2 = response.getWriter();
		writer2.write("This is just a plain text\n" + userName);
		
		if("yes".equals(yesOrNoParam)) {
			response.getWriter().write("<html><body style=\"background-color:red\"><h1>You said yes!</h1></body></html>");

		}
		if("no".equals(yesOrNoParam)) {
			response.getWriter().write("<html><body>You said no!</body></html>");
			
			//this command redirect to a different URL
			//((HttpServletResponse) response).sendRedirect("http://jenkov.com");
		}				
	}

}
