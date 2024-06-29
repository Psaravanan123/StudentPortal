import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) {		
		String username=req.getParameter("E_id");
			String password=req.getParameter("password");
			boolean check=check(username,password);
			if(check) {
				try {
				String str1="select * from students where E_id=? and password=?";
				Connection cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/college","root","123456");
				PreparedStatement ps=cn.prepareStatement(str1);
				ps.setString(1, username);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					String name=rs.getString(3);
					String mark1=rs.getString(4);
					String  mark2=rs.getString(5);
					String status=rs.getString(6);
					
				HttpSession sc=req.getSession();
				sc.setAttribute("name", name);
				sc.setAttribute("mark1", mark1);
				sc.setAttribute("mark2", mark2);
				sc.setAttribute("status", status);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				RequestDispatcher dp=req.getRequestDispatcher("details");
				try {
					dp.forward(req, res);
					
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			else {
				RequestDispatcher dp=req.getRequestDispatcher("login.html");
				try {
					dp.forward(req, res);
					
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
			}
	
		
		
	}
	public boolean check(String E_id,String password) {
		try {
			String str1="select * from students where E_id=? and password=?";
			Connection cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/college","root","123456");
			PreparedStatement ps=cn.prepareStatement(str1);
			ps.setString(1, E_id);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String c_username=rs.getString(1);
				String c_password=rs.getString(2);
				if(E_id.equals(c_username)&&password.equals(c_password)) {
					return true;
				}
				
			}
		}
			catch(Exception e){
				e.printStackTrace();
			}
		return false;
	}

	}

