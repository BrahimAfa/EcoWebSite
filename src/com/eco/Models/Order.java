package com.eco.Models;

import java.sql.Date;

public class Order {
	private int id;
	private User user;
	private Product product;
	private Date date;
	
	public Order(int id, User user,Product product, Date date) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.date = date;
	}
	public Order(int id, User user,Product product) {
		this.id = id;
		this.user = user;
		this.product = product;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
