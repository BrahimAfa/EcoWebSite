package com.eco.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eco.DAL.dbConnection;
import com.eco.Models.User;



public class Userimpl implements IDAO<User> {
	private Statement stmt;
	private ResultSet rs;
	private Connection dbcnx;
	public Userimpl () {
		this.dbcnx = dbConnection.getInstance().getConnection();
	}

	@Override
	public List<User> getAll() throws SQLException {
		try {
			List<User> Users=new ArrayList<User>();
			stmt = dbcnx.createStatement();
			rs =stmt.executeQuery("select * from User");  

			while(rs.next()) { 
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
				User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
				Users.add(user);
			}
			return Users;
		} finally {
			dbcnx.close();
		}
	}

	@Override
	public User getOne(int id) throws SQLException {
		User user=null;
		PreparedStatement prestmt = dbcnx.prepareStatement("select * from user where id = ?");
		prestmt.setInt(1,id);
		rs = prestmt.executeQuery();
		rs.next();
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
		user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
		return user;
	}

	@Override
	public boolean Update(User obj) throws SQLException {
		try {
			String Query = "Update User set fname = ? ,lname=?,email=?,password=? where id = ?";
			PreparedStatement pstmt = dbcnx.prepareStatement(Query);
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2, obj.getLastName());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getPassword());
			pstmt.setInt(5, obj.getId());
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) return true;
			return false;
		} finally {
			dbcnx.close();
		}

	}

	@Override
	public boolean Insert(User obj) throws SQLException{
		try {
			String Query = "insert into User values (?,?,?,?)";
			PreparedStatement pstmt = dbcnx.prepareStatement(Query);
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2, obj.getLastName());
			pstmt.setString(3, obj.getPassword());
			pstmt.setString(4, obj.getEmail());
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) return true;
			return false;
		} finally {
			dbcnx.close();
		}


	}

	@Override
	public boolean Delete(int id) throws SQLException {
		try {
			String Query = "Delete from User where id = ?";
			PreparedStatement pstmt = dbcnx.prepareStatement(Query);
			pstmt.setInt(1, id);
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) return true;
			return false;
		} finally {
			dbcnx.close();
		}
	}
	public User getUser(String email,String password) throws SQLException {
		User user=null;
		PreparedStatement prestmt = dbcnx.prepareStatement("select * from user where email = ? and password = ?");
		prestmt.setString(1,email);
		prestmt.setString(2,password);
		rs = prestmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
			user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
			return user;
		}
		
		return null;
	}

}