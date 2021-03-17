package com.saveo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saveo.model.Product;

@WebServlet("/SearchMedicine")
public class SearchMedicine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String c_name = req.getParameter("c_name");
		session = req.getSession();

		try {
			Product p = new Product();
			p.setC_name(c_name);
			boolean b = p.searchMedicine();
			
			if(b==true) {
				session.setAttribute("al", p.al);
				resp.sendRedirect("searchedlist.jsp");
			}else {
				resp.sendRedirect("failure.html");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
