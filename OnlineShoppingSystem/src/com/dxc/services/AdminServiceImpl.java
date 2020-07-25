package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public class AdminServiceImpl implements IAdminService
{
	IAdminDao dao = new AdminDaoImpl();
	
	public boolean adminLogin(Admin a)
	{
		 return dao.adminLogin(a);
	}
	public boolean addProduct(Product p)
	{
		return dao.addProduct(p);
	}
	public List<Product> getAllProducts()
	{
		return dao.getAllProducts();
	}
	public List<Product> getProduct(int i)
	{
		return dao.getProduct(i);
	}
	public void addCustomer(Customer c) 
	{
		dao.addCustomer(c);
	}
	public boolean removeCustomer(int i)
	{
		return dao.removeCustomer(i);
	}
	public List<Customer>getAllCustomers()
	{
		return dao.getAllCustomers();
	}
}
