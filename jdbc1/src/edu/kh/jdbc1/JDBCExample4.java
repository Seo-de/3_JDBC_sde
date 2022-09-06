package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		// 직급명, 급여를 입력 받아
		// 해당 직급에서 입력 받은 급여보다 많이 받는 사원의
		// 이름, 직급명, 급여, 연봉을 조회하여 출력
		
		// 단, 조회 결과가 없으면 "조회 결과 없음" 출력
		
		// 조회 결과가 있으면
		// 선동일 / 대표 / 8000000 / 96000000
		// 송종일 / 부장 / 6000000 / 72000000
		// ....
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("직급명 : ");
			String inputN = sc.next();
			System.out.print("급여 : ");
			int inputSal = sc.nextInt();
			
			// 커넥션 객체 만드는 부분
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
//			String ip = "localhost"; // DB 서버 컴퓨터 IP
//			String port = ":1521"; // 포트번호
//			String sid = ":XE"; // DB 이름
			// 위에 4줄을 밑의 url 한줄로 만듦.
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh_sde";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT EMP_NAME, NVL(JOB_NAME, '없음') AS 직급명, SALARY, SALARY*12 AS 연봉"
					+ " FROM EMPLOYEE"
					+ " JOIN JOB USING(JOB_CODE)"
					+ " WHERE NVL(JOB_NAME, '없음')='"+ inputN+"'"
					+ " AND SALARY > "+inputSal;
					
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("직급명");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("연봉");
				
//				Employee emp = new Employee(empName, jobName, salary, annualIncome);
//				list.add(emp);
				// 밑에 한줄로 수정 가능
				list.add(new Employee(empName, jobName, salary, annualIncome));
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			}else {
				for(Employee emp : list) {
					System.out.println(emp);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
