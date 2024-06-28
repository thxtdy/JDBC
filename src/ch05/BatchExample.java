package ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchExample {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/demo3?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 자바 표준 API
			conn = DriverManager.getConnection(url, user, password);
			// 트랜잭션 처리
			conn.setAutoCommit(false);

			// 배치 처리리리 -> User 테이블에 한번 사용자 3명을 넣어보자.
			String query = "INSERT INTO user(name, email) VALUES(?,?) ";

			PreparedStatement psmt = conn.prepareStatement(query);
			// 1번 사용자 처리
			psmt.setString(1, "빢턔현");
			psmt.setString(2, "thth@gmail.com");

			// psmt.executeUpdate();

			// 배치 처리를 위해 호출할 메소드가 필요
			psmt.addBatch();

			// 2번 사용자 처리
			psmt.setString(1, "낌남철");
			psmt.setString(2, "namba@gmail.com");

			psmt.addBatch();

			// 3번 사용자 처리
			psmt.setString(1, "최이쪠");
			psmt.setString(2, "now@gmail.com");
			
			psmt.addBatch();

			int[] rowCounts = psmt.executeBatch();

			conn.commit(); // 이상 없으면 물리적인 장치에 영구히 반영

			System.out.println("RowCounts : " + rowCounts.length);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	} // end of main

} // end of class
