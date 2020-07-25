package com.dxc.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.pojos.Bill;
import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.pojos.Wallet;
import com.dxc.services.CustomerServiceImpl;
import com.dxc.services.ICustomerService;


@RequestMapping("/views")
@Controller
public class CustomerController 
{
	ICustomerService customerService = new CustomerServiceImpl();
	
String status = " ";
	
	@RequestMapping("/cuslogin")
	public String adminLogin(@ModelAttribute Customer c, Model m,HttpSession session)
	{	
		int id = c.getId();
		
		session.setAttribute("cId", id);
		
		boolean b = customerService.customerLogin(c);
				
		if(b)
		{
			return "customermenu";
		}
		else
		{
			status = "Incorrect login credentials";
			m.addAttribute("status",status);
			return "message";
			
		}	
	}
	
	@RequestMapping("/clogout")
	public String logout()
	{
		return "home";
	}
	
	@RequestMapping("/displayall")
	public String getAllProducts(Model model)
	{
		List<Customer> list=customerService.getAllProducts();
		model.addAttribute("list", list);
		return "show";
	}
	@RequestMapping("/addtocart")
	public String addtocart(@RequestParam("productId") int i,Cart c,Product p,HttpSession session,Model m)
	{
		int cId = (int) session.getAttribute("cId");
		
		boolean b = customerService.addtocart(i,cId,c,p);
		
		if(b)
		{
			status = "Added to cart!!";
			m.addAttribute("status",status);
			return "message";
		}
		else
		{
			status ="Product/Quantity not found to add to cart";
			m.addAttribute("status",status);
			return "message";
			
		}	
	}
	@RequestMapping("/getcart")
	public String getCart(Model model,HttpSession session)
	{
		int cId = (int) session.getAttribute("cId");

		List<Cart> list=customerService.getCart(cId);
		
		if(!(list.isEmpty()))
		{
			model.addAttribute("list", list);
			return "showcart";
		}
		else
		{
			status ="your cart is empty!!";
			model.addAttribute("status",status);
			return "message";
		}
	}
	@RequestMapping("/addbalance")
	public String addBalance(@ModelAttribute Wallet w,HttpSession session, Model m)
	{
		int id = (int) session.getAttribute("cId");
		w.setCustomerId(id);
		double bal = w.getBalance();
		customerService.addAmount(w,bal);
		
		status ="Amount added to wallet!!";
		m.addAttribute("status",status);
		return "message";
	}
	
	@RequestMapping("/showbalance")
	public String getBal(HttpSession session,Model m)
	{
		int id = (int) session.getAttribute("cId");
		
		double balance = customerService.getBalance(id);
		
		m.addAttribute("balance",balance);
		return "showbalance";
	}
	
	
	
	@RequestMapping("/displaybill")
	public String dispBill(@ModelAttribute Cart c, HttpSession session,Model m)
	{
		int cId = (int) session.getAttribute("cId");
		
		List<Bill> list = customerService.getBill(cId,c);
		
		if(!list.isEmpty())
		{
			m.addAttribute("list", list);
			return "showbill";
		}
		else
		{
			status ="you doesn't have anybill!!";
			m.addAttribute("status",status);
			return "message";
		}
		
	}
	
	@RequestMapping("/paybill")
	public String payBill(Bill b, HttpSession session,Model m)
	{
		int cId = (int) session.getAttribute("cId");
		
		boolean payment = customerService.payBill(b,cId);
		
		if(payment)
		{
			status ="Payment successfull!!";
			m.addAttribute("status",status);
			return "message";
		}
		else
		{
			status ="Insufficent balance to purchase";
			m.addAttribute("status",status);
			return "message";
		}
		
	}
	
}

