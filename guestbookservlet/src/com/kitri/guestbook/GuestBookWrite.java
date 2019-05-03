package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gbwrite")
public class GuestBookWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		request.setCharacterEncoding("UTF-8");
		
//		1. get(작성자, 제목, 내용)
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
//		2. Logic처리 : DB에 데이터 저장하기.
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			System.out.println("드라이버 성공!!!");
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("insert into guestbook(seq, name, subject, content, logtime)\n");
			sql.append("values(guestbook_seq.nextval ,? , ?, ?, sysdate)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 0;
			
			pstmt.setString(++idx, name);
			pstmt.setString(++idx, subject);
			pstmt.setString(++idx, content);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		3. 실패시 다시 작성해주세요 성공시 index링크 띄우고 보내기.
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body align = \"center\"><br><br><br>");
		if (cnt != 0) {
			out.println("<h3>로그인 성공!!!</h3>");
		}else {
			out.println("<h3>로그인 실패!!!</h3>");
		}
		out.println("	<a href = \"/guestbookservlet/index.html\">main으로!!</a>");
		out.println("	</body>");
		out.println("</html>");
	}

}
