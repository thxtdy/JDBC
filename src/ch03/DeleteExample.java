package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteExample {
	public static void main(String[] args) {
		
	
	String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
	String user = "root";
	String password = "asd123";
	
	// Connection 객체를 얻어서 insert 구문 만들어보기
	
	// mydb2, employee
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(url, user, password);
		
		String query = "DELETE FROM employee where name = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, "최지아");
		
		int rowCount = preparedStatement.executeUpdate();
		System.out.println("RowCount : " + rowCount);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
}
