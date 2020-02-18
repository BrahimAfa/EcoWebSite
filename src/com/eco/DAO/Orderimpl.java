package com.eco.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eco.DAL.dbConnection;
import com.eco.Models.Order;
import com.eco.Models.Product;
import com.eco.Models.User;



public class Orderimpl implements IDAO<Order> {
	private Statement stmt;
	private ResultSet rs;
	private Connection dbcnx;
	private IDAO<Product> productDao;
	private IDAO<User> userDao;
	public Orderimpl () {
		this.dbcnx = dbConnection.getInstance().getConnection();
		productDao=new Productimpl();
		userDao= new Userimpl();
	}

	@Override
	public List<Order> getAll() throws SQLException {
		List<Order> Orders =new ArrayList<Order>();
		stmt = dbcnx.createStatement();
		rs =stmt.executeQuery("select * from Orders");  

		while(rs.next()) { 
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
			Order order = new Order(rs.getInt(1),userDao.getOne(rs.getInt(2)),productDao.getOne(rs.getInt(3)),rs.getDate(4));
			Orders.add(order);
		}
		return Orders;

	}

	@Override
	public Order getOne(int id) throws SQLException {
		return null;
	}

	@Override
	public boolean Insert(Order obj) throws SQLException{
		String Query = "insert into  Orders (User,Product)  values (?,?)";
		PreparedStatement pstmt = dbcnx.prepareStatement(Query);
		pstmt.setInt(1, obj.getUser().getId());
		pstmt.setInt(2, obj.getProduct().getId());
		int rowsInserted = pstmt.executeUpdate();
		if (rowsInserted > 0) return true;
		return false;
	}




}
