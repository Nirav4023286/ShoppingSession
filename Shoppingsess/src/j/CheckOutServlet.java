package j;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkoutserv")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public CheckOutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		go(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		go(request, response);
	}

	protected void go(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[CheckoutServlet]: Asked to CHECKOUT");
		
		PrintWriter webpage = response.getWriter();
		float total=0;
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			
		} else {
			webpage.write("<html><body><center>");
			webpage.write("<h1>Your Payment is Successfully Done!!!</h1>");
			webpage.write("You paid  " + total);
			webpage.write("</center></body></html>");
			session.removeAttribute("mycart");

		}
	}
}