package j;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/productsserv")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductsServlet() {
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
		System.out.println("[ViewCartItemsServlet]: Asked to show Cart Items");

		PrintWriter webpage = response.getWriter();

		webpage
				.write("<!Doctype html><html lang='en'><head>"
						+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
						+ "<link href='styles.css' rel='stylesheet'/></head><body>");
		webpage.write("<div id='page'>");
		webpage.write("<div id='banner'>");
		webpage.write("<h1>ShoppingAddA</h1><br><br><hr></div>");
		webpage
				.write("<table border='3'><tr><td>PRODUCT</td><td>PRICE</td></tr></table>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/shopaddadb", "root",
					"root");
			String query = "select * from product";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {

					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String comp = resultSet.getString(3);
					int price = resultSet.getInt(4);

					webpage.write("<div id='contents'>");
					webpage.write("<article id='product'>");
					webpage.write("<form action='addToCart'>");
					webpage
							.write("<input type='hidden' name='productName' value='"
									+ name
									+ "'> <input type='hidden' name='productPrice' value='"
									+ price + "'>");
					webpage
							.write(""
									+ "<table>"
									+ "<tr>"
									+ " <td>"
									+ "    <img id='prodLogo' src='"
									+ id
									+ ".png'>"
									+ "  	 <div><span id='name'> "
									+ name
									+ " </span> <span id='price'>   &#8377; &nbsp; <img src='currency.png' width='15px'  height='15px'> &nbsp;"
									+ price
									+ " </span></div>"
									+ " </td>"
									+ " <td><input id='submitButton' type='submit' value='addToCart'>"
									+ " </td>" + "</tr>" + "</table>");
					webpage.write("</form>");
					webpage.write("</article>");
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		webpage
				.write("<article id='actions'> <a id='checkout' href='Checkout'>Checkout</a>");
		webpage
				.write("<a id='viewCart' href='viewCart'>View My Cart</a> </article>");
		webpage
				.write("<footer> Copyrights &copy; APTECH COMPUTER EDUCATION </footer></div></div></body></html>");

	}
}
