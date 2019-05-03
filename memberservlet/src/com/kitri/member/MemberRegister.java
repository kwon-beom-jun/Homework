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

@WebServlet("/register")
public class MemberRegister extends HttpServlet {
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
		System.out.println("ȸ�������Ϸ� �Դ�..");

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
//		1. data get(�̸�, ���̵�, ���, �̸���1, �̸���2, ��ȭ 1, ��ȭ2, ��ȭ3, �����ȣ, �ּ�, ���ּ�)
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("address_detail");
		
		
//		2. Logicó�� : 1.�� �����͸� �μ�Ʈ��.
		
		int cnt = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			System.out.println("����̹� ����");
			
			StringBuffer sql = new StringBuffer(); // sql�� ���� ���� ������ preparedstatement�� sql������ �������ߵ�. 
			sql.append("insert all  \n");
			sql.append("		into member(id,name,pass,emailid,emaildomain,joindate) \n");
			sql.append("		values(?, ?, ?, ?, ?, sysdate) \n");
			sql.append("		into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3) \n");
			sql.append("		values(?, ?, ?, ?, ?, ?, ?) \n");
			sql.append("select * from dual \n");
			
			pstmt = conn.prepareStatement(sql.toString()); // ���۴� String�� �ƴ�.
			
			int idx = 0;
			
			pstmt.setString(++idx, id);
			pstmt.setString(++idx, name);
			pstmt.setString(++idx, pass);
			pstmt.setString(++idx, emailid);
			pstmt.setString(++idx, emaildomain);
			pstmt.setString(++idx, id);
			pstmt.setString(++idx, zipcode);
			pstmt.setString(++idx, address);
			pstmt.setString(++idx, addressdetail);
			pstmt.setString(++idx, tel1);
			pstmt.setString(++idx, tel2);
			pstmt.setString(++idx, tel3);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
//		insert all - �����̺� �ֱ�
		
//		insert all 
//		into member(id,name,pass,emailid,emaildomain,joindate)
//		values(?, ?, ?, ?, ?, sysdate)
//		into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3)
//		values(?, ?, ?, ?, ?, ?, ?)
//		select * from dual
//		2���� �����Ͱ� ���� 2�� ����.
		
//		3. response page�� ������. 2�� ����� ����.
//			3-1 !0 : ȫ�浿�� ȸ�������� ȯ���մϴ�.
//			3-2 0  : ���� ������ ȸ�� ������ �����Ͽ����ϴ�. ������ �ٽ� ������ �ּ���.
		
		response.setContentType("text/html;charset=UTF-8"); // ù����� �ȱ����� �����
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		if (cnt != 0) {
			out.println(name + "�� ȸ�������� ȯ���մϴ�.<br>");
			out.println("�α��� �� ��� ���񽺸� �̿��� �� �ֽ��ϴ�.<br>");
			out.println("<a href=\"/memberservlet/user/login.html\">�α���<a><br>");
		}else {
			out.println("");
			out.println("");
			out.println("������ �ٽ� ������ �ּ���");
		}
		out.println("	</body>");
		out.println("</html>");
		
	}

}


















