package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.guestbookVo;

public class guestbookDao {
	
	public int sub(guestbookVo vo)
	{
		int count=0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query = "delete from guestbook " + 
					"where no=? and password=?";
			System.out.println(1);
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			System.out.println(2);
			// 4.결과처리
			count=pstmt.executeUpdate();	//여기서 문제 발생 쿼리가 이상한듯
			System.out.println(3);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패" + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		} finally {

			try {
				// 5. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
		
		
	}
	public int add(guestbookVo vo)
	{
		int count=0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query = "insert into guestbook(no,name,password,content) " + 
					"values(seq_guestbook_no.nextval,?,?,?)";
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setString(1,vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			// 4.결과처리
			count=pstmt.executeUpdate();
			
	
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패" + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		} finally {

			try {
				// 5. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		
		return count;
	}
	public List getlist() {
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		ResultSet rs=null;
		List<guestbookVo> list=new ArrayList<guestbookVo>();
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query = "select no, name, content, leg_date from guestbook order by no";
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			rs=pstmt.executeQuery();
			// 4.결과처리
			while(rs.next()) {
				guestbookVo vo1=new guestbookVo(rs.getInt("no"),
												rs.getString("name"),
												rs.getString("content"),
												rs.getString("leg_date"));	
				list.add(vo1);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패" + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		} finally {

			try {
				// 5. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		
		
		
		return list;
	}

}
