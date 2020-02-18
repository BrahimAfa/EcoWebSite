package com.eco.Models;

public class Product {
	private int id;
	private int Price;
	private String Catergory;
	private String Name;
	
	public Product(int id, String name, int price,String catergory) {
		this.id = id;
		Price = price;
		Catergory = catergory;
		Name = name;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getCatergory() {
		return Catergory;
	}
	public void setCatergory(String catergory) {
		Catergory = catergory;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
}
