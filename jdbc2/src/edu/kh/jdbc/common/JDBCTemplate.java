package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/* DB 연결 (Connection 생성), 자동 커밋 off,
	 * 트랜잭션 제어, JDBC 객체 자원 반환(close)
	 * 
	 * 이러한 JDBC에서 반복 사용되늰 코드를 모아둔 클래스
	 * 
	 *  * 모든 필드, 메서드가 static*
	 *  -> 어디서든지 클래스명.필드명 / 클래스명.메서드명
	 *  	호출가능 (별도 객체 생성X)
	 * */
	
	private static Connection conn = null;
	// -> static 메서드에서 필드를 사용하기 위해서는
	//	  필드도 static 필드 이여야 한다.
	
	
	/** DB 연결 정보를 담고있는 Connection 객체 생성 및 반환 메서드
	 * @return conn
	 */
	public static Connection getConnection() {
		
		try {
			
			// 현재 커넥션 객체가 없을 경우 -> 새 커넥션 객체를 생성
			if(conn == null || conn.isClosed()) {
				
				// conn.isClose() : 커넥션이 close 상태이면 true 반환
				
				Properties prop = new Properties();
				// Map<String, String> 형태의 객체, XML 입출력 특화
				
				// driver.xml 파일 읽어오기
				prop.loadFromXML(new FileInputStream("driver.xml"));
				// XML 파일에 작성된 내용이 Properties 객체에 모두 저장됨.
				
				// XML에서 읽어온 값을 모두 String 변수에 저장
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				// 커넥션 생성
				Class.forName(driver); // oracle.jdbc.driver.OracleDriver 객체 메모리 로드
				
				// DriverManager를 이용해 Connection 객체 생성
				conn = DriverManager.getConnection(url, user, password);
				
				// 개발자가 직접 트랜잭션을 제어할 수 있도록
				// 자동 커밋 비활성화
				conn.setAutoCommit(false);
			
			}
			
		} catch (Exception e) {
			System.out.println("[Connection 생성 중 예외 발생]");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	/** Connection 객체 반환 메서드
	 * @param conn
	 */
	public static void close (Connection conn) {
		
	}
	
	// 오버로딩이라서 같은 close 이름을 갖고있어도 매개변수가 달라서 에러가 뜨지 않음.
	
	/** Statement(부모), PreparedStatement(자식) 객체 반환 메서드 
	 *  (다형성, 동적바인딩)
	 * @param stmt
	 */
	public static void close (Statement stmt) {
		
	}
	
	
	/** ResultSet 객체 반환 메서드
	 * @param rs
	 */
	public static void close (ResultSet rs) {
		
	}
	
	
	/** 트랜잭션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
	}
	
	
	/** 트랜잭션 Rollback
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
	}
	
	
	
}
