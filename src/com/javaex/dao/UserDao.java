package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;



public class UserDao {
	
public UserVo getUser(int no) {
		
		int count=0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		ResultSet rs=null;
		UserVo vo=null;
		
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query = "select no,names,email,gender "+
				    "from userr "+
				    "where no=?"; 					
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setInt(1,no);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				no=rs.getInt("no");
				String name=rs.getString("names"); 
				String email=rs.getString("email");
				String gender=rs.getString("gender");
				vo=new UserVo();
				vo.setNo(no);
				vo.setNames(name);
				vo.setEmail(email);
				vo.setGender(gender);
			}	
			
			System.out.println("Dao");
			
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
		
		return vo;
}
	public UserVo getUser(String email,String password) {
		
		int count=0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		ResultSet rs=null;
		UserVo vo=null;
		
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query ="select no,names "+
				    "from userr "+
				    "where email=? and passwords=?"; 					
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setString(1,email);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int no=rs.getInt("no");
				String name=rs.getString("names"); 
				vo=new UserVo();
				vo.setNo(no);
				vo.setNames(name);
			}	
			
			
			
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
		
		return vo;
		
		
		
		
		
	}
	public int insert(UserVo vo) {
	
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
			String query ="insert into userr values(seq_usesrs_no.nextval,?,?,?,?)";					
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setString(1,vo.getNames());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPasswords());
			pstmt.setString(4, vo.getGender());
			
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



	

}
