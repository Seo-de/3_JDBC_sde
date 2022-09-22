package edu.kh.jdbc.board.model.vo;

import java.util.List;

// 게시글 1개 정보를 저장하는 VO
public class Board {
	
	private int boardNo;		 // 게시글 번호
	private String boardTitle; 	 // 게시글 제목
	private String boardContent; // 게시글 내용
	private String createDate; 	 // 게시글 작성일
	private int readCount; 		 // 조회수
	private int memberNo; 		 // 작성자 회원 번호
	private String memberName; 	 // 작성자 회원 이름
	private int commentCount;	 // 댓글 수
	
	private List<Comment> commentList; // 댓글 목록
	
	public Board() { }

	public Board(int boardNo2, String boardTitle2, String memberName2, int readCount2) {
		// TODO Auto-generated constructor stub
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}
