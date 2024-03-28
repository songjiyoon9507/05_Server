package edu.kh.todoList.todo.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.todoList.common.JDBCTemplate.*;
import edu.kh.todoList.todo.model.dto.Todo;

public class TodoDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public TodoDAO() {
		try {
			prop = new Properties();
			
			String filePath
				= TodoDAO.class.getResource("/edu/kh/todoList/sql/todo-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/** 로그인한 회원이 등록한 todoList 전체 조회 SQL 실행 DAO
	 * @param conn
	 * @param memberNo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectAll(Connection conn, int memberNo) throws Exception {
		List<Todo> todoList = new ArrayList<Todo>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Todo todo = new Todo();
				
				todo.setTodoNo(rs.getInt(1));
				todo.setTodoTitle(rs.getString(2));
				todo.setTodoMemo(rs.getString(3));
				todo.setTodoDate(rs.getString(4));
				
				todoList.add(todo);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return todoList;
	}

	/** Todo 등록 SQL 수행 DAO
	 * @param conn
	 * @param title
	 * @param memo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int insert(Connection conn, String title, String memo, int memberNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insert");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, memo);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** Todo 삭제 SQL 수행 DAO
	 * @param conn
	 * @param todoNo
	 * @return result
	 * @throws Exception
	 */
	public int delete(Connection conn, String todoNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, todoNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** Todo 조회 SQL 수행 DAO
	 * @param conn
	 * @param todoNo
	 * @param memberNo
	 * @return todo
	 * @throws Exception
	 */
	public Todo selectOne(Connection conn, String todoNo, int memberNo) throws Exception {
		Todo todo = null;
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, todoNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todo = new Todo();
				todo.setTodoNo(rs.getInt(1));
				todo.setTodoTitle(rs.getString(2));
				todo.setTodoMemo(rs.getString(3));
				todo.setTodoDate(rs.getString(4));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return todo;
	}

	public int update(Connection conn, String title, String memo, int memberNo, String todoNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, memo);
			pstmt.setString(3, todoNo);
			pstmt.setInt(4, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}
