package com.dxc.services;

import java.util.List;
import com.dxc.dao.CustomerDaoImpl;
import com.dxc.dao.ICustomerDao;
import com.dxc.pojos.Bill;
import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.pojos.Wallet;

public class CustomerServiceImpl implements ICustomerService
{
	ICustomerDao dao = new CustomerDaoImpl();
	
	public boolean customerLogin(Customer c)
	{
		return dao.customerLogin(c);
	}
	public List<Customer> getAllProducts()
	{
		return dao.getAllProducts();
	}
	public boolean addtocart(int i, int cId,Cart c,Product p)
	{
		return dao.addtocart(i,cId,c,p);
	}
	public List<Cart> getCart(int i)
	{
		return dao.getCart(i);
	}
	public void addAmount(Wallet w,double bal)
	{
	   dao.addAmount(w,bal);
	}
	public List<Bill> getBill(int id,Cart c)
	{
		return dao.getBill(id,c);
	}
	public double getBalance(int id)
	{
		return dao.getBalance(id);
	}
	public boolean payBill(Bill b,int id)
	{
		return dao.payBill(b, id);
	}
}
