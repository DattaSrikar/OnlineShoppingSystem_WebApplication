package com.dxc.pojos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bill 
{	
	private int customerId;
	private int productNo;
	private String productName;
	private double OriginalPrice;
	private int quantity;
	@Id
	private double Discount;
	private double finalPrice;
	
	public Bill()  {}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public double getOriginalPrice() {
		return OriginalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		OriginalPrice = originalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "Bill [customerId=" + customerId + ", productNo=" + productNo + ", productName=" + productName
				+ ", OriginalPrice=" + OriginalPrice + ", quantity=" + quantity + ", Discount=" + Discount
				+ ", finalPrice=" + finalPrice + "]";
	}
	
	
	
}
