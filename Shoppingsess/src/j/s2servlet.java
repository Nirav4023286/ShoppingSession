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


@WebServlet("/s2serv")
public class s2servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		d3(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		d3(request,response);
	}
    protected void d3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String uname = request.getParameter("uname");
		HttpSession session = request.getSession(false);
		if (session == null) {
			System.out.println("Session: Does not exist");
			session = request.getSession();
			System.out.println("Session Created");
			showSessionInfo(session);
		} else {
			System.out.println("Session: Exists");
			showSessionInfo(session);
		}
		create(response.getWriter(),uname);
		session.invalidate();
		System.out.println("Session: Destroyed");
	}
    void create(PrintWriter webpage,String uname) {
		webpage.write("<html><body bgcolor='brown'>");

		webpage.write("<h1>BookBoon</h1>");
		webpage.write("<br><br><hr>");
		webpage.write("<h3> " +uname+ ", You Logged out successfully.Click Here to &nbsp;<a href='Onl.html'>Login</a>");

		webpage.write("</body></html>");
	}

	private void showSessionInfo(HttpSession session) {
		System.out.println("ID        : " + session.getId());
		System.out
				.println("Created On: " + new Date(session.getCreationTime()));
		System.out.println("Last Seen : "
				+ new Date(session.getLastAccessedTime()));
		System.out.println("Timeout   : " + session.getMaxInactiveInterval()
				/ 60 + ":" + session.getMaxInactiveInterval() % 60);

	}
}


