package com.dxc.dao;

import java.util.List;

import com.dxc.pojos.Bill;
import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.pojos.Wallet;

public interface ICustomerDao 
{
	public boolean customerLogin(Customer c);
	public List<Customer> getAllProducts();
	public boolean addtocart(int i, int cId,Cart c,Product p);
	public List<Cart> getCart(int id);
	public void addAmount(Wallet w,double bal);
	public List<Bill> getBill(int id,Cart c);
	public double getBalance(int id);
	public boolean payBill(Bill b,int id);
	
}
