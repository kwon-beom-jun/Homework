package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		request.setCharacterEncoding("UTF-8");
		
//		1. ���� �������� ���̵� ���.
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		
//		2. logicó�� �����ͺ��̽��� �ִ� ���̵� ����� ������ ���̵� ��� ��.
//		select name from member where id = ? and pass = ?
		
		String name = null;
		
		try {
		
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select name from member where id = ? and pass = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				if (rs != null) {
				rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		3. ������ ���� �ѷ��ְ� �ƴϸ�  ��ϵ��� ���� ���̵��̰ų�, ���̵� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.
//			3-1. name != null
					
//			3-2. name == null
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body align = \"center\">");
		if (name != null) {
			out.println("<strong>"+name+"</strong>�� �ȳ��ϼ���");
		}else {
			out.println("���̵� �Ǵ� ��й�ȣ�� Ȯ���ϼ���.<br>");
			out.println("���̵� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			out.println("<a href=\"/memberservlet/user/login.html\">�α���<a><br>");
		}
		out.println("	</body>");
		out.println("</html>");
		
		
		
	}

}
