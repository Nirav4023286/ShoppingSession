package j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/s1")
public class s1servlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		d(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		d(request,response);
	}
    protected void d(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		HttpSession session = request.getSession(false);
		if (session == null) {
			System.out.println("Session: Does not exist");
			session = request.getSession();
			System.out.println("Session: Created");
			showSessionInfo(session);
		} else {
			System.out.println("Session: Exists");
			session.invalidate();
			session = request.getSession();
			System.out.println("Session: New Session Created");
			showSessionInfo(session);
		}
		create(response.getWriter(),uname);
		session.setAttribute("currentUser", email);
	}

	void create(PrintWriter webpage, String uname) {
		webpage.write("<html><head>");
		webpage.write("<style type='text/css'>" +
				"#table{" +
				"margin-left:5%;" +
				"}" +
				"#h5{" +
				"margin-left:95%;" +
				"}" +
				"#h6{" +
				"margin-left:90%;" +
				"}" +
				"#hr{" +
				"margin-left:4%;" +
				"margin-right:25%;" +
				"}");
		webpage.write("</style></head><body>");
		webpage.write("<h1><center>BOOKBOON FOR ONLINE SHOP!!!!</center></h1>");
		webpage.write("<h5 id='h5'><a href='log'>LOGOUT</a></h5>");
		webpage.write("<h6 id='h6'>Welcome " + uname + "</h6><hr id='hr'>");
	    webpage.write("<table id='table' cellpadding='8'>");
		webpage.write("<tr><td><a href=''>HOME</a></td>");
		webpage.write("<td><a href='Books'>PRODUCTS</a></td>");
		webpage.write("<td><a href=''>SELL ON SITE</a></td>");
		webpage.write("<td><a href=''>ABOUT US</a></td>");
		webpage.write("</tr></table>");
		webpage.write("<hr id='hr'><br>");
        webpage.write("</body></html>");
	}

	private void showSessionInfo(HttpSession session) {
		System.out.println("ID        : " + session.getId());
		System.out.println("Account Created on: " + new Date(session.getCreationTime()));
		System.out.println("Last Seen : "+ new Date(session.getLastAccessedTime()));
		System.out.println("Timeout   : " + session.getMaxInactiveInterval()
				/ 60 + ":" + session.getMaxInactiveInterval() % 60);

	}
	}

