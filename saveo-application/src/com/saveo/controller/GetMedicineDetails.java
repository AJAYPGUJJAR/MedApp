package com.saveo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saveo.model.Product;

@WebServlet("/GetMedicineDetails")
public class GetMedicineDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		session = req.getSession(true);
		
		try {
			Product p = new Product();
			p.setC_unique_code(id);
			
			boolean b = p.fetchDetails();
			
			if(b==true) {
				session.setAttribute("c_name",p.getC_name());
				session.setAttribute("c_batch_no",p.getC_batch_no());
				session.setAttribute("d_expiry_date",p.getD_expiry_date());
				session.setAttribute("n_balance_qty",p.getN_balance_qty());
				session.setAttribute("c_packaging",p.getC_packaging());
				session.setAttribute("c_unique_code",p.getC_unique_code());
				session.setAttribute("c_schemes",p.getC_schemes());
				session.setAttribute("n_mrp",p.getN_mrp());
				session.setAttribute("c_manufacturer",p.getC_manufacturer());
				session.setAttribute("hsn_code",p.getHsn_code());
				
				resp.sendRedirect("details.jsp");
			} else {
				resp.sendRedirect("failure.html");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
