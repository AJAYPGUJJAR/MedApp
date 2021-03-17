package com.saveo.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saveo.model.Product;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		session.setAttribute("uid", UUID.randomUUID().toString());
		
		String uid = req.getParameter("uid");
		String qty = req.getParameter("qty");
		String cname = req.getParameter("cname");
		
		try {
			Product p = new Product();
			p.setC_unique_code(uid);
			p.setC_name(cname);

			boolean b = p.placeOrder(qty);
			
			if(b==true) {
				resp.sendRedirect("order.jsp");
			}else {
				resp.sendRedirect("failure.html");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
