package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExample {

	public static void main(String[] args) {

		// Driver => MySQL 개발자들이 자바 코드로 작성한 클래스의 묶음 (.jar)

		String url = "jdbc:mysql://localhost:3306/m_board?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 루트 계정 사용 금지
		String password = "asd123";

		try {
			// 클래스 Class <-- 최상위 Object 안에 있음
			// 동적 바인딩 처리
			// mysql 드라이버 (구현 클래스) 메모리에 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try (Connection connection = DriverManager.getConnection(url, user, password)) {
				connection.setAutoCommit(false);
				String sqlInsert = "INSERT INTO user(username, password, email, userRole, address, createDate)\r\n"
						+ "values( ?, ?, ?, ?, ?, now())";

				PreparedStatement insertPsmt = connection.prepareStatement(sqlInsert);
				insertPsmt.setString(1, "안철수");
				insertPsmt.setString(2, "asd123");
				insertPsmt.setString(3, "a@gmail.com");
				insertPsmt.setString(4, "user");
				insertPsmt.setString(5, "경기 성남시분당구갑");
				insertPsmt.executeUpdate();
				
				String sqlUpdate ="UPDATE user SET email = ? where username = ?";
				
				PreparedStatement updatePsmt = connection.prepareStatement(sqlUpdate);
				updatePsmt.setString(1, "b@gmail.com");
				updatePsmt.setString(2, "김유신");
				updatePsmt.executeUpdate();
				
				// 수동 모드 커밋을 설정했다면 직접 commit() 을 실행해야
				// 물리적인 저장 장치에 영구히 반영이 된다.
				connection.commit();
				
				if(connection == null) {
					connection.rollback();
				} 
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // end of main

} // end of class
