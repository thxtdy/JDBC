package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectExample {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

			String query = "SELECT  * from employee where id = ? ";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "3");
			
			ResultSet r = preparedStatement.executeQuery();
			
			while(r.next()) {
				System.out.println("ID : " + r.getInt(1) + "|| NAME : " + r.getString(2) + " || Department : " + r.getString(3) + "|| Salary : " + r.getString(4));
				
			}
			
			
//			int rowCount = preparedStatement.execute();
//			System.out.println("RowCount : " + rowCount);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
