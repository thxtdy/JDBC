package study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ch01.dto.Employee;

public class ObserveMyDataBase {

	public static void main(String[] args) {
	
		// url 주소 설정 , user 설정 , password 설정
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		// Connection 설정 null 값 == 연결을 하기 위함
		// ResultSet connection 의 값을 받아오기
		Connection connection = null;
		ResultSet resultSet = null;
		
		// 항상 돌아가게끔
		boolean flag = true;

		System.out.println("DataBase Service -- Employee");
		System.out.println("1 : Select || 2 : Insert || 3. Update || 4. Delete");
		
		// Scanner 값 받기
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 연결할때 위에 설정해두었던 url, user, password 정보 삽입
			connection = DriverManager.getConnection(url, user, password);
			
			// select 쿼리문
			String query = "SELECT  * from employee";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			List<Employee> employees = new ArrayList<>();

			while (flag) {
				String numInput = sc.next();
				
				// mydb2 - table : employee 에 대한 row 를 모두 반환
				if (numInput.equals("1")) {
					while (resultSet.next()) {
						Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"),
								resultSet.getString("department"), resultSet.getInt("salary"),
								resultSet.getString("hire_date"));
						employees.add(employee);
					}
					for (Employee employee : employees) {
						System.out.println(employee.toString());
					}
					
				  // mydb2 - table : employee 에 데이터 insert
				} else if (numInput.equals("2")) {
					String insertQuery = "INSERT INTO employee values (?,?,?,?,now())";
					preparedStatement = connection.prepareStatement(insertQuery);
					
					System.out.println("ID를 입력해주세요");
					int insert = sc.nextInt();
					preparedStatement.setInt(1, insert+1);
					
					System.out.println("이름을 입력해주세요");
					String nameinput = sc.next();
					preparedStatement.setString(2, nameinput);
					
					System.out.println("직업을 입력해주세요");
					String jobinput = sc.next();
					preparedStatement.setString(3, jobinput);
					
					System.out.println("희망 연봉을 입력해주세요");
					String salaryInput = sc.next();
					preparedStatement.setString(4, salaryInput);
					
					int rowCount = preparedStatement.executeUpdate();
					System.out.println("전송된 Row :" + rowCount);
				
				  // mydb2 - table : employee 에 대한 부서 수정
				} else if(numInput.equals("3")) {
					String updateQuery = "UPDATE employee set department = ? where name = ?";
					preparedStatement = connection.prepareStatement(updateQuery);
					
					System.out.println("수정하고자 하는 부서를 입력해주세요");
					String updateInput = sc.next();
					preparedStatement.setString(1, updateInput);
					
					System.out.println("수정하고자 하는 이름을 입력해주세요");
					String updateValues = sc.next();
					preparedStatement.setString(2, updateValues);
					
					int rowCount = preparedStatement.executeUpdate();
					System.out.println("전송된 Row : " + rowCount);
				
				  // mydb2 - table : employee 에 대한 row(행) 삭제
				} else if (numInput.equals("4")){
					String deleteQuery = "DELETE FROM employee where name = ?";
					preparedStatement = connection.prepareStatement(deleteQuery);
					
					System.out.println("삭제할 Row(행)의 이름을 입력해주세요.");
					String deleteValues = sc.next();
					preparedStatement.setString(1, deleteValues);
					System.out.println(deleteValues + "에 대한 행이 삭제되었습니다.");
					
					int rowCount = preparedStatement.executeUpdate();
					System.out.println("전송된 Row : " + rowCount);
				}

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
