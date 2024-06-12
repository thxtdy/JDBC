package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch01.dto.Employee;


public class MySQLJdbcExample {
	
	public static void main(String[] args) {
		
		
		// 준비물
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 루트 계정 사용 금지
		String password = "asd123";
		
		// 필요 데이터 타입
		// JDBC API 레벨(Java 개발자들이 개념 시켜놓은 클래스이다.)
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		// **************  nullpointerException **************
		// 객체 생성 또는 주소값 미참조
		
		// 1. MySQL 구현체를 사용하겠다는 설정을 해야 한다.
		// JDBC 드라이버 로드(MySQL 구현 클래스를 로드)
		try {
			// 1. 메모리에 사용하는 드라이버(JDBC API를 구현한 클래스) 클래스를 띄운다.
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 데이터 베이스 연결 설정
			conn = DriverManager.getConnection(url, user, password);
			
			// 3. SQL 실행
			statement = conn.createStatement();
			// Query 를 실행시키는 메소드. 2개는 꼭 기억하자 

			resultSet = statement.executeQuery("SELECT * FROM employee"); // Select 실행  시 사용한다
//			statement.executeUpdate(password); --> Insert, Update, Delete 사용
			
			
			
			// 구문 분석 == 파싱
			// 4. 결과 처리
//			while(resultSet.next()) {
//				
//				System.out.println("USER ID : " + resultSet.getInt("id"));
//				System.out.println("USER NAME : " + resultSet.getString("name"));
//				System.out.println("department : " + resultSet.getString("department"));
//				System.out.println("===================================================");
//				
//			}
			List<Employee> employees = new ArrayList<>();
			
			while(resultSet.next()) {	
				Employee employee = new Employee(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("department"), resultSet.getInt("salary"), resultSet.getString("hire_date"));
				employees.add(employee);
			}
			for (Employee employee : employees) {
				System.out.println(employee.toString());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	} // end of main
	 
} // end of class
