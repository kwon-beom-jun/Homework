package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gblist")
public class GuestBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("안녕하세요");
		
		
//		1~2~3. parameter을 가져올 것이 없음 DB에서 불러오기만 하면 됨. 불러온것을 바로 뿌려주기.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		List<GuestBookDto> list = new ArrayList<GuestBookDto>(); // 전역으로 두면 초기화를 시켜줘야뎀.

		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			System.out.println("driver succes!!!");
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select * from guestbook");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				
				GuestBookDto guestBookDto = new GuestBookDto();

				guestBookDto.setSeq(rs.getString("seq"));
				guestBookDto.setName(rs.getString("name"));
				guestBookDto.setSubject(rs.getString("subject"));
				guestBookDto.setContent(rs.getString("content"));
				guestBookDto.setLogtime(rs.getString("logtime"));
				
				list.add(guestBookDto);
				
				
			}
			
			System.out.println(list);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				e.printStackTrace();
			}
		}
		
//		3. html
		
		int num = list.size();
		
		for (int i = 0; i < num; i++) {
			
			out.print("<!DOCTYPE html>\r\n" + 
					"<html lang=\"ko\">\r\n" + 
					"<head>\r\n" + 
					"<title>글목록</title>\r\n" + 
					"<meta charset=\"utf-8\">\r\n" + 
					"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\">\r\n" + 
					"<script src=\"https:/ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
					"<script src=\"https:/cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\"></script>\r\n" + 
					"<script src=\"https:/maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\"></script>\r\n" + 
					"<script type=\"text/javascript\">\r\n" + 
					"\r\n" + 
					"</script>\r\n" + 
					"</head>\r\n" + 
					"<body>");
			out.print("<div class=\"container\" align=\"center\">\r\n" + 
					"  <div class=\"col-lg-8\" align=\"center\">\r\n" + 
					"  <h2>글목록</h2>\r\n" + 
					"  <table class=\"table table-borderless\">\r\n" + 
					"  	<tr>\r\n" + 
					"  		<td align=\"right\"><button type=\"button\" class=\"btn btn-link\">글쓰기</button></td>\r\n" + 
					"  	</tr>\r\n" + 
					"  </table>\r\n" + 
					"  <table class=\"table table-active\">\r\n" + 
					"    <tbody>\r\n" + 
					"      <tr>\r\n" + 
					"        <td>작성자 : ");
//					"홍길동"
			out.print(list.get(i).getName());
			out.print("</td>\r\n" + 
					"        <td style=\"text-align: right;\">작성일 : ");
//					"2019.05.05"
			out.print(list.get(i).getLogtime());
			out.print("</td>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <td colspan=\"2\"><strong>");
//					"글번호. 제목"
			out.print(list.get(i).getSeq());
			out.print(". ");
			out.print(list.get(i).getSubject());
			out.print("</strong></td>\r\n" + 
					"      </tr>\r\n" + 
					"      <tr>\r\n" + 
					"        <td colspan=\"2\">");
//					"내용"
			out.print(list.get(i).getContent());
			out.print("</td>\r\n" + 
					"      </tr>\r\n" + 
					"    </tbody>\r\n" + 
					"  </table>\r\n" + 
					"  </div>\r\n" + 
					"</div>");
			out.print("</body>\r\n" + 
					"</html>");
			System.out.println(list.get(i).getSeq());
			System.out.println(list.get(i).getName());
			System.out.println(list.get(i).getSubject());
			System.out.println(list.get(i).getContent());
		}
		
		
	}

}













