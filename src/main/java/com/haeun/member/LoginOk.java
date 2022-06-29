package com.haeun.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//요청이 오면(login페이지에서 submit버튼 누르면) 실행하게 만듦
@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private String id, pw, name, email, phone, gender;
	
	//method가 get일때 이 메소드가 실행
	//HttpServletRequest req(request객체) - 파라미터값(id,pw)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		actionDo(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		actionDo(req, resp);
	}
	
	//get,post 둘 다 실행
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//한글깨짐 방지
		
		String mid = request.getParameter("userId");
		String mpw = request.getParameter("userPw");
		
		String driverName="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/odbo";
		String username = "root";
		String password = "12345";
		
		String sql = "SELECT * FROM testmember WHERE id = '" + mid + "' AND pw = '" + mpw + "'";
		
		try{
			
			Class.forName(driverName);	//드라이버 로딩
			//데이터베이스 연동
			conn = DriverManager.getConnection(url, username, password);
			//sql을 실행해주는 statement 객체 생성
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);	//SELECT(rs를 통해 결과값을 리턴)
			
			//System.out.println("rs:" + rs);
			
			//로그인 했는지 체크
			int loginFlag = 0;
			
			//session 객체 선언(request객체의 session 가져오기)
			//(java에는 내장객체가 없기때문)
			HttpSession session = request.getSession();
			
			
			while(rs.next()){	//next : 처음 위치 필드명에서 다음 포지션(레코드)으로 이동(없으면 false)
			 	id = rs.getString("id");
			 	pw = rs.getString("pw");
			 	email = rs.getString("email");
			 	name = rs.getString("name");
			 	phone = rs.getString("phone");
			 	gender = rs.getString("gender");
			 	System.out.println("아이디: " + id);
			 	
			 	
			 	//세션에 저장(세션 생성)
			 	session.setAttribute("name", name);
			 	session.setAttribute("id", id);
				session.setAttribute("pw", pw);
				loginFlag++;
			}
			if(loginFlag == 0){	//값이 없으면
				response.sendRedirect("login.jsp");	//다시 로그인 페이지로 이동
			}else{
				response.sendRedirect("loginSucess.jsp");	
			}
			
		}catch(Exception e) {	//Exception: 모든 에러를 찾는 상위 클래스
			e.printStackTrace();
		}finally{	//에러가 있든 없든 무조건 실행
			try{
				if(rs != null){	//null값이 아니면
					stmt.close();//데이터베이스 닫기
				}
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
