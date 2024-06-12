package study;

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

		Scanner sc = new Scanner(System.in);

		boolean flag = true;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

			String query = "SELECT  * from employee where id = ? ";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			while (flag) {
				String input = sc.next();
				
				if (input.equals("2")) {
					preparedStatement.setString(1, input);
					ResultSet r = preparedStatement.executeQuery();
					while (r.next()) {
						System.out.println("ID : " + r.getInt(1) + "|| NAME : " + r.getString(2) + " || Department : " + r.getString(3) + "|| Salary : " + r.getString(4));
						return;
					}
				} else if (input.equals("3")) {
					preparedStatement.setString(1, input);
					ResultSet r = preparedStatement.executeQuery();
					while (r.next()) {
						System.out.println("ID : " + r.getInt(1) + "|| NAME : " + r.getString(2) + " || Department : " + r.getString(3) + "|| Salary : " + r.getString(4));
						return;
					}
				} else if (input.equals("5")) {
					preparedStatement.setString(1, input);
					ResultSet r = preparedStatement.executeQuery();
					while (r.next()) {
						System.out.println("ID : " + r.getInt(1) + "|| NAME : " + r.getString(2) + " || Department : " + r.getString(3) + "|| Salary : " + r.getString(4));
						return;
					}
				} else if (input.equals("7")) {
					preparedStatement.setString(1, input);
					ResultSet r = preparedStatement.executeQuery();
					while (r.next()) {
						System.out.println("ID : " + r.getInt(1) + "|| NAME : " + r.getString(2) + " || Department : " + r.getString(3) + "|| Salary : " + r.getString(4));
						return;
					}
				} else if (input.equals("8")) {
					preparedStatement.setString(1, input);
					ResultSet r = preparedStatement.executeQuery();
					while (r.next()) {
						System.out.println("ID : " + r.getInt(1) + "|| NAME : " + r.getString(2) + " || Department : " + r.getString(3) + "|| Salary : " + r.getString(4));
						return;
					}
				} else {
					System.out.println("입력한 번호의 Row가 존재하지 않습니다.");
					flag = false;
				}
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
