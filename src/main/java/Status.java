

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Status")
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		HttpSession sd = req.getSession();
		String name =(String) sd.getAttribute("name");
		String mark1 =(String) sd.getAttribute("mark1");
		String mark2 =(String) sd.getAttribute("mark2");
		String status =(String) sd.getAttribute("status");
		pw.println("Name: "+name);
		pw.println("Mark1: "+mark1);
		pw.println("Mark2: "+mark2);
		pw.println("Status: "+status);
	}

}
