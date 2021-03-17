package com.saveo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product {
	private String c_name;
	private String c_batch_no;
	private String d_expiry_date;
	private String n_balance_qty;
	private String c_packaging;
	private String c_unique_code;
	private String c_schemes;
	private String n_mrp;
	private String c_manufacturer;
	private String hsn_code;

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	public ArrayList<String> al;
	// Getters and Setters

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_batch_no() {
		return c_batch_no;
	}

	public void setC_batch_no(String c_batch_no) {
		this.c_batch_no = c_batch_no;
	}

	public String getD_expiry_date() {
		return d_expiry_date;
	}

	public void setD_expiry_date(String d_expiry_date) {
		this.d_expiry_date = d_expiry_date;
	}

	public String getN_balance_qty() {
		return n_balance_qty;
	}

	public void setN_balance_qty(String n_balance_qty) {
		this.n_balance_qty = n_balance_qty;
	}

	public String getC_packaging() {
		return c_packaging;
	}

	public void setC_packaging(String c_packaging) {
		this.c_packaging = c_packaging;
	}

	public String getC_unique_code() {
		return c_unique_code;
	}

	public void setC_unique_code(String c_unique_code) {
		this.c_unique_code = c_unique_code;
	}

	public String getC_schemes() {
		return c_schemes;
	}

	public void setC_schemes(String c_schemes) {
		this.c_schemes = c_schemes;
	}

	public String getN_mrp() {
		return n_mrp;
	}

	public void setN_mrp(String n_mrp) {
		this.n_mrp = n_mrp;
	}

	public String getC_manufacturer() {
		return c_manufacturer;
	}

	public void setC_manufacturer(String c_manufacturer) {
		this.c_manufacturer = c_manufacturer;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	// Loading the driver
	public Product() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saveodb", "root", "root");
	}

	// 1. Import Medicines Data(CSV) into DataBase
	public boolean importDB(String csvpath) throws SQLException {
		// SQL query
		String s = "LOAD DATA INFILE ? INTO TABLE productlist FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n' IGNORE 1 ROWS (c_batch_no,@d_expiry_date,n_balance_qty) SET -Date = STR_TO_DATE(@d_expiry_date,'%m/%d/%Y')";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, csvpath);
		int x = pstmt.executeUpdate();
		return x > 0 ? true : false;
	}

	// 2. Search medicines
	public boolean searchMedicine() throws SQLException {
		al = new ArrayList<String>();
		String s = "Select * from product where c_name LIKE ?";
		pstmt = con.prepareStatement(s);
		String name = c_name + "%";
		pstmt.setString(1, name);
		res = pstmt.executeQuery();
		if (res.next() == true) {
			while (res.next()) {
				al.add(res.getString("c_name"));
			}
			return true;
		}
		return false;
	}

	// 3. Fetch Details of Medicines
	public boolean fetchDetails() throws SQLException {
		String s = "Select * from product where c_unique_code = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, c_unique_code);
		res = pstmt.executeQuery();
		while (res.next()) {
			c_name = res.getString("c_name");
			c_batch_no = res.getString("c_batch_no");
			d_expiry_date = res.getString("d_expiry_date");
			n_balance_qty = res.getString("n_balance_qty");
			c_packaging = res.getString("c_packaging");
			c_unique_code = res.getString("c_unique_code");
			c_schemes = res.getString("c_schemes");
			n_mrp = res.getString("n_mrp");
			c_manufacturer = res.getString("c_manufacturer");
			hsn_code = res.getString("hsn_code");
			return true;
		}
		return false;
	}

	// 4. Place Order
	public boolean placeOrder(String qty) throws SQLException {
		String s = "Select * from product where c_name = ? OR c_unique_code = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, c_name);
		pstmt.setString(2, c_unique_code);
		res = pstmt.executeQuery();
		String cqty = null;
		while (res.next()) {
			cqty = res.getString("n_balance_qty");
		}

		if( (Integer.parseInt(cqty)-Integer.parseInt(qty)) > 0) {
			String s1 = "Update product set n_balance_qty = ? where c_name = ? OR c_unique_code = ?";
			pstmt = con.prepareStatement(s1);
			String newqty = ""+(Integer.parseInt(cqty)-Integer.parseInt(qty));
			pstmt.setString(1, newqty);
			pstmt.setString(2, c_name);
			pstmt.setString(3, c_unique_code);
			int x = pstmt.executeUpdate();
			return x>0?true:false;
		}else {
			return false;
		}
	}

}
