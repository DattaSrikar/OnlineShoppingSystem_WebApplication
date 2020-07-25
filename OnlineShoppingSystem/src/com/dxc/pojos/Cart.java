package com.dxc.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart 
{
	
	private int customerId;
	private String productName;
	@Id
	private int productId;
	private int quantity;
	
	public Cart()  {}

	public Cart(int customerId,String productName,int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.customerId = customerId;
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	
	
	
}
