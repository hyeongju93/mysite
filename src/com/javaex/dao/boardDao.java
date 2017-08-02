package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.boardVo;


public class boardDao {
	
	public List setting(List<boardVo> list) {
		int i=list.size();
		for(boardVo vo : list) {
			vo.setSet_num(i);
			i-=1;
		}
		return list;
	}
	
	
	public List search(String kwd) {
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		ResultSet rs=null;
		List<boardVo> list=new ArrayList<boardVo>();
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			
			String query = "select num,title,content,hit,reg_date,user_no, names " + 
							"from board b,userr r " + 
							"where b.user_no=r.no and names like ? order by num desc";
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setString(1,'%'+kwd+'%');
			rs=pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				boardVo vo=new boardVo(rs.getInt("num"),
										rs.getString("title"),
										rs.getString("content"),
										rs.getInt("hit"),
										rs.getString("reg_date"),
										rs.getInt("user_no"),
										rs.getString("names"));	
				list.add(vo);
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
	
	
	public void count(int hit,int num) {
		
		int count=0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("2번식 들어옴");
			// 3. SQL문 준비/바인딩/실행
			String query = "update board " + 
							"set hit=? " + 
							"where num=?";
		
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			hit=hit+1;
			pstmt.setInt(1, hit);
			pstmt.setInt(2, num);
				
			// 4.결과처리
			pstmt.executeUpdate();
			System.out.println("2번식 마무리");

			System.out.println("update 마침");

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
		
		
		
	}
	
	
	public int delete(int num)
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
			String query = "delete from board " + 
							"where num=?";
			System.out.println(1);
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setInt(1, num);
			
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
	
	
	
	public int insert(String title,String content,int user_no) {
		
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
			String query ="insert into board " + 
						  "values(seq_board_no.nextval,?,?,0,sysdate,?)";					
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setInt(3,user_no);
			
			
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
	
	
	
	public void update(String title,String content,int num) {
	
		int count=0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			if (title.equals("")) {
				System.out.println("null 진입");
				String query = "update board " + 
						"set content=? ,reg_date=sysdate " + 
						"where num=?";
				pstmt = conn.prepareStatement(query);// 쿼리 담당
				pstmt.setString(1, content);
				pstmt.setInt(2, num);

				// 4.결과처리
				count = pstmt.executeUpdate();
				System.out.println("1번식 마무리");
			} else {
				System.out.println("2번식 들어옴");
				// 3. SQL문 준비/바인딩/실행
				String query = "update board " + 
								"set title=?, content=? ,reg_date=sysdate " + 
								"where num=?";
				System.out.println("쿼리 들어옴");
				pstmt = conn.prepareStatement(query);// 쿼리 담당
				System.out.println("쿼리 집어넣고");
				pstmt.setString(1,title);
				System.out.println("1번 title");
				pstmt.setString(2, content);
				System.out.println("2번 content");
				pstmt.setInt(3, num);
				System.out.println("3번 번호");
				
				// 4.결과처리
				pstmt.executeUpdate();
				System.out.println("2번식 마무리");
			}
			System.out.println("update 마침");
			
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
		
		
		
	}
	
	
	public boardVo getUser(int nu) {

		int count = 0;
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		ResultSet rs = null;
		boardVo vo = null;

		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query = "select num,title,content,hit,reg_date,user_no,r.names " + 
							"from board b,userr r " + 
							"where b.user_no=r.no and num=? " + 
							"order by num";
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			pstmt.setInt(1, nu);

			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				int num=rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit=rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				String names=rs.getString("names");
				vo = new boardVo();
				vo.setNum(num);
				vo.setNames(names);
				vo.setHit(hit);
				vo.setTitle(title);
				vo.setReg_date(reg_date);
				vo.setContent(content);
				vo.setUser_no(user_no);
			}

			System.out.println("getuser");

			// 4.결과처리
			count = pstmt.executeUpdate();

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

	public List getlist() {
		Connection conn = null; // 연결
		PreparedStatement pstmt = null; // query 전달팩
		ResultSet rs=null;
		List<boardVo> list=new ArrayList<boardVo>();
		
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비/바인딩/실행
			String query = "select num,title,content,hit,reg_date,user_no,r.names from board b,userr r where b.user_no=r.no order by num desc";
			pstmt = conn.prepareStatement(query);// 쿼리 담당
			rs=pstmt.executeQuery();
			
			
			// 4.결과처리
			while(rs.next()) {
				boardVo vo=new boardVo(rs.getInt("num"),
										rs.getString("title"),
										rs.getString("content"),
										rs.getInt("hit"),
										rs.getString("reg_date"),
										rs.getInt("user_no"),
										rs.getString("names"));	
				list.add(vo);
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
