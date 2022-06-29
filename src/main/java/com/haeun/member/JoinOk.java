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

//요청이 오면(join페이지에서 submit버튼 누르면) 실행하게 만듦
@WebServlet("/JoinOk")
public class JoinOk extends HttpServlet {
	private Connection conn;
	private Statement stmt;
	//private ResultSet rs;
	
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
		
		String mid = request.getParameter("memberId");
		String mpw = request.getParameter("memberPw");
		String mname = request.getParameter("memberName");
		String memail = request.getParameter("memberEmail");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String mgender = request.getParameter("memberGender");
		
		//전화번호
		String mphone = phone1 + "-" + phone2 + "-" + phone3;
		
		String sql = "INSERT INTO testmember(id, pw, name, email, phone, gender) VALUES ('"+ mid +"','" + mpw + "','" + mname + "','" + memail + "','" + mphone + "','" + mgender + "')";
		
		String driverName="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/odbo";
		String username = "root";
		String password = "12345";
		
		try{
			Class.forName(driverName);	//드라이버 로딩
			//데이터베이스 연동
			conn = DriverManager.getConnection(url, username, password);
			
			//sql을 실행해주는 Statement 객체 생성
			stmt = conn.createStatement();
			
			//SQL 실행 -> 1이 반환되면 성공, 아니면 실패
			int resultCheck = stmt.executeUpdate(sql);
			
			if(resultCheck == 1){	//1이 반환되면
				//회원가입 성공 -> joinSucess 페이지로 이동
				response.sendRedirect("joinSucess.jsp");	
			}else{
				//실패 -> join 페이지로 이동
				response.sendRedirect("join.jsp");
			}
			
		}catch(Exception e) {	//Exception: 모든 에러를 찾는 상위 클래스
			e.printStackTrace();
		}finally{	//에러가 있든 없든 무조건 실행
			try{
				if(stmt != null){	//null값이 아니면
					stmt.close();//데이터베이스 닫기
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