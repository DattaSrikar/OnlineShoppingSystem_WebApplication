package com.dxc.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.pojos.*;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IAdminService;

@RequestMapping("/views")
@Controller
public class AdminController 
{
	IAdminService adminService = new AdminServiceImpl();
	
	String status = " ";
	
	@RequestMapping("/login")
	public String adminLogin(@ModelAttribute Admin a, Model m)
	{	
		boolean b = adminService.adminLogin(a);
				
		if(b)
		{
			return "adminmenu";
		}
		else
		{
			status = "Incorrect login credentials";
			m.addAttribute("status",status);
			return "message";
			
		}	
	}
	
	@RequestMapping("/add")
	public String addProduct(@ModelAttribute Product p ,Model m)
	{
		boolean b = adminService.addProduct(p);
		
		System.out.println("b-- "+b);
		
		if(b)
		{
			status = "Product Added!!";
			m.addAttribute("status",status);
			return "message";
		}
		else
		{
			status = "Unable to add product (is already exists)";
			m.addAttribute("status",status);
			return "message";
		}
	}
	
	@RequestMapping("/getall")
	public String getAllProducts(Model model)
	{
		List<Product> list=adminService.getAllProducts();
		model.addAttribute("list", list);
		return "show";
	}
	
	@RequestMapping("/getproduct")
	public String getProduct(@RequestParam ("productNo") int id, Model m)
	{
		status = "Product not found !!";
		m.addAttribute("status",status);
		
		List<Product> list = adminService.getProduct(id);
		
		for(Product p:list)
		{
			if(p.getProductNo()==id)
			{
				m.addAttribute("list",list);
				return "showproduct";
			}
		}
		
		return "message";
	}
	@RequestMapping("/addcustomer")
	public String addCustomer(Customer c, Model m)
	{
		adminService.addCustomer(c);
		
		status = "Customer Added!!";
		m.addAttribute("status",status);
		return "message";
	}
	
	@RequestMapping("/removecustomer")
	public String removeCustomer(@RequestParam int id, Model m)
	{
		boolean b = adminService.removeCustomer(id);
		
		System.out.println("remove-"+b);
		
		if(b)
		{
			status = "Customer removed!!";
			m.addAttribute("status",status);
			return "message";
		}
		else
		{
			status = "Customer not found to remove!!";
			m.addAttribute("status",status);
			return "message";
		}
		
	}
	
	@RequestMapping("/showcustomer")
	public String getAllCustomers(Model m)
	{
		List<Customer> list = adminService.getAllCustomers();
		m.addAttribute("list",list);
		
		return "showcustomers";
	}
	
	@RequestMapping("/logout")
	public String logout()
	{
		return "home";
	}
	
}
