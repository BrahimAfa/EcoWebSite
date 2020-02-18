package com.eco.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eco.DAL.dbConnection;
import com.eco.Models.Product;



public class Productimpl implements IDAO<Product> {
	private Statement stmt;
	private ResultSet rs;
	private Connection dbcnx;
	public Productimpl () {
		this.dbcnx = dbConnection.getInstance().getConnection();
	}

	@Override
	public List<Product> getAll() throws SQLException {
		List<Product> Products =new ArrayList<Product>();

		stmt = dbcnx.createStatement();
		rs =stmt.executeQuery("select * from Product");  

		while(rs.next()) { 
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
			Product product = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			Products.add(product);
		}
		return Products;

	}

	@Override
	public Product getOne(int id) throws SQLException {
		PreparedStatement prestmt = dbcnx.prepareStatement("select * from Product where id = ?");
		prestmt.setInt(1,id);
		rs = prestmt.executeQuery();
		rs.next();
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
		Product product = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
		return product;
	}

	@Override
	public boolean Update(Product obj) throws SQLException {
		String Query = "Update Product set name = ? ,price=?,category=? where id = ?";
		PreparedStatement pstmt = dbcnx.prepareStatement(Query);
		pstmt.setString(1, obj.getName());
		pstmt.setInt(2, obj.getPrice());
		pstmt.setString(3, obj.getCatergory());
		pstmt.setInt(4, obj.getId());
		int rowsUpdated = pstmt.executeUpdate();
		if (rowsUpdated > 0) return true;
		return false;


	}

	@Override
	public boolean Insert(Product obj) throws SQLException{
		String Query = "insert into Product values (?,?,?)";
		PreparedStatement pstmt = dbcnx.prepareStatement(Query);
		pstmt.setString(1, obj.getName());
		pstmt.setInt(2, obj.getPrice());
		pstmt.setString(3, obj.getCatergory());
		int rowsInserted = pstmt.executeUpdate();
		if (rowsInserted > 0) return true;
		return false;
	}

	@Override
	public boolean Delete(int id) throws SQLException {
		String Query = "Delete from Product where id = ?";
		PreparedStatement pstmt = dbcnx.prepareStatement(Query);
		pstmt.setInt(1, id);
		int rowsDeleted = pstmt.executeUpdate();
		if (rowsDeleted > 0) return true;
		return false;
	}

}
