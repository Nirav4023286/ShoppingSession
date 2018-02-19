package j;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addtocartserv")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public AddToCartServlet() {
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
		System.out.println("[AddToCartServlet]: Asked to addToCart");
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");

		HttpSession session = request.getSession(true);

		Object obj = session.getAttribute("mycart");
		HashMap<String, String> cart = null;

		if (obj == null) {
			cart = new HashMap<String, String>();
		} else {
			cart = (HashMap<String, String>) obj;
		}

		cart.put(productName, productPrice);

		session.setAttribute("mycart", cart);

		response.sendRedirect("addedToCart.html");
	}
}