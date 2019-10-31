package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Student;
import com.utils.GetConnection;

/**
 * Servlet implementation class ModStudent
 */
@WebServlet("/ModStudent")
public class ModStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String studentno = request.getParameter("studentno");
		String sname = request.getParameter("sname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String classno = request.getParameter("classno");
		String point_str = request.getParameter("point");
		double point = 0.00;
		if(point_str != null)
			point = Double.parseDouble(point_str);
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		System.out.println("studentno: " + studentno);
		System.out.println("sname: " + sname);
		System.out.println("sex: " + sex);
		System.out.println("birthday: " + birthday);
		System.out.println("classno: " + classno);
		System.out.println("point: " + point);
		System.out.println("phone: " + phone);
		System.out.println("email: " + email);
		String test = "select studentno from student where studentno = '" + studentno + "'";
		String modsname = "UPDATE student SET sname = '" + sname +"' WHERE studentno = '" + studentno + "' ";
		String modsex = "UPDATE student SET sex = '" + sex +"' WHERE studentno = '" + studentno + "' ";
		String modbirthday = "UPDATE student SET birthday = '" + birthday +"' WHERE studentno = '" + studentno + "' ";
		String modclassno = "UPDATE student SET classno = '" + classno +"' WHERE studentno = '" + studentno + "' ";
		String modpoint = "UPDATE student SET point = '" + point +"' WHERE studentno = '" + studentno + "' ";
		String modphone = "UPDATE student SET phone = '" + phone +"' WHERE studentno = '" + studentno + "' ";
		String modemail = "UPDATE student SET email = '" + email +"' WHERE studentno = '" + studentno + "' ";
		String sql = "select * from student";
		boolean canMod = false;
		Connection conn = GetConnection.getConn();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			ResultSet rs1 = sta.executeQuery(test);
			while(rs1.next()) {
				String id = rs1.getString(1);
				if(id.equals(studentno)) {
					canMod = true;
					break;
				}	
			}
			if(canMod == true) {
				if(sname != null)
					sta.executeUpdate(modsname);
				if(sex != null) 
					sta.executeUpdate(modsex);
				if(birthday != null)
					sta.executeUpdate(modbirthday);
				if(classno != null)
					sta.executeUpdate(modclassno);
				if(point != 0.00)
					sta.executeUpdate(modpoint);
				if(phone != null)
					sta.executeUpdate(modphone);
				if(email != null)
					sta.executeUpdate(modemail);
				ResultSet rs = sta.executeQuery(sql);
				List<Student> list = new ArrayList<>();
				while(rs.next()) {
					if(studentno.equals(studentno) && sname.equals(sname)) {
						Student stu = new Student();
						stu.setStudentno(rs.getString(1));
						stu.setSname(rs.getString(2));
						stu.setSex(rs.getString(3));
						stu.setBirthday(rs.getTimestamp(4));
						stu.setClassno(rs.getString(5));
						stu.setPoint(rs.getDouble(6));
						stu.setPhone(rs.getString(7));
						stu.setEmail(rs.getString(8));
						list.add(stu);
						break;
					}
				}
				request.setAttribute("list",list);
				request.getRequestDispatcher("WEB-INF/view/mod/modStudent_success.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("WEB-INF/view/mod/modStudent_failed.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
