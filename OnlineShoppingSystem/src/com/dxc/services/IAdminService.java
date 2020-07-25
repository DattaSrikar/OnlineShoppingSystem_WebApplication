package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public interface IAdminService 
{	
	public boolean adminLogin(Admin a);
	public boolean addProduct(Product p);
	public List<Product> getAllProducts();
	public void addCustomer(Customer c);
	public boolean removeCustomer(int i);
	public List<Customer>getAllCustomers();
	public List<Product> getProduct(int i);

}
