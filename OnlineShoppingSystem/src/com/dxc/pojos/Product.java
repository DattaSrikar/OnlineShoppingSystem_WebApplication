package com.dxc.pojos;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Product 
{
	@Id
	private int productNo;
	private String productName;
	private double productPrice;
	private int quantity;
	private double discount;
	
	public Product()  {}
	
	

	public Product(int productNo, String productName, double productPrice, int quantity, double discount) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.discount = discount;
	}



	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}



	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", quantity=" + quantity + ", discount=" + discount + "]";
	}
	
	
	
	
}
