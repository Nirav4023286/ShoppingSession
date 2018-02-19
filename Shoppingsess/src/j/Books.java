package j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Books")
public class Books extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		d2(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		d2(request,response);
	}
	protected void d2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/productdb", "root",
					"root");
			String query = "select * from prod";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {

					int pid = resultSet.getInt(1);
					String bname = resultSet.getString(2);
					String publication = resultSet.getString(3);
					String edition=resultSet.getString(4);
					String price = resultSet.getString(5);
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
		}
	}
