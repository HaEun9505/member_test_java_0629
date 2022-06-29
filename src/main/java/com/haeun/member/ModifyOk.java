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
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private Connection conn;
	//sql을 실행해주는 statement 객체
	private Statement stmt;
	//private ResultSet rs;
	
	private String id, pw, name, email, phone, gender;
	
	HttpSession session;
	
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
		//session 객체 선언(request객체로 session값 가져오기)
		session = request.getSession();
		
		//getParameter(form의"name");
		String mid = request.getParameter("memberId");
		String mpw = request.getParameter("memberPw");
		String mname = request.getParameter("memberName");
		String memail = request.getParameter("memberEmail");
		String mphone = request.getParameter("memberPhone");
		String mgender = request.getParameter("memberGender");
		
		String sessionPw = (String)session.getAttribute("pw");
		
		if(sessionPw.equals(mpw)){	//비밀번호가 맞으면 실행(수정)
			//id,pw는 수정x
			String sql = "UPDATE testmember SET name='"+mname+"',email='"+memail+"',phone='"+mphone+"',gender='"+mgender + "' WHERE id = '" + mid + "'";				
			
			//data source 설정
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
				
				if(resultCheck == 1){
					response.sendRedirect("modifySucess.jsp");
				}else{
					response.sendRedirect("modify.jsp");
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
		}else {	//다르면 다시 수정 페이지로 이동
			System.out.println("회원정보수정단계 실패");
			response.sendRedirect("modify.jsp");
		}
	}
}