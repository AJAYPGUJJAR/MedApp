package com.saveo.controller;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saveo.model.Product;

@WebServlet("/UploadCSV")
public class UploadCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Scanner sc;
	String csvpath; // C:\Users\Dell\Downloads\Product list - Sheet1.csv

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		csvpath = req.getParameter("path");
		resp.sendRedirect(ReadCSV());
	}

	public String ReadCSV() {
		Product p;
		boolean b = false;
		try {
			p = new Product();
			b = p.importDB(csvpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b == true ? "success.html" : "failure.html";
	}

}
