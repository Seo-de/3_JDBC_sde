package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.main.view.MainView;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception {
		// 결과 저장용 변수 선언
		List<Board> boardList = new ArrayList();
		
		try {
			// SQL ~
			String sql = prop.getProperty("selectAllBoard");
			
			// Statement ~
			stmt = conn.createStatement();
			
			// SQL 수행 후 ~ ResultSet ~
			rs = stmt.executeQuery(sql);
			
			// ResultSet에 저장된 값을 List에 옮겨 담기
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				int readCount = rs.getInt("READ_COUNT");
				
				String createDate = rs.getString("CREATE_DT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				boardList.add(board);
			}

		} finally {
			// 사용한 JDBC 객체 반환
			close(rs);
			close(conn);
		}
		// 조회 결과 반환
		return boardList;
	}

	/** 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception {
		// 결과 저장용 변수 선언
		Board board = null; // 앞에 조건식 용도로 사용해야 되니까
		
		try {
			String spl = prop.getProperty("selectBoard"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(spl); // PreparedStatement 생성
			pstmt.setInt(1, boardNo); // ? 알맞은 값 대입
			
			rs = pstmt.executeQuery(); // SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			
			if(rs.next()) { // 조회 결과가 있을 경우
				board = new Board(); // Board 객체 생성 == board는 null이 아님
				
				board.setBoardNo(		rs.getInt("BOARD_NO"));
				board.setBoardTitle(	rs.getString("BOARD_TITLE"));
				board.setBoardContent(	rs.getString("BOARD_CONTENT"));
				board.setMemberNo(		rs.getInt("MEMBER_NO"));
				board.setMemberName(	rs.getString("MEMBER_NM"));
				board.setReadCount(		rs.getInt("READ_COUNT"));
				board.setCreateDate(	rs.getString("CREATE_DT"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return board; // 조회 결과 반환
	}

	/** 조회 수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("increaseReadCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}