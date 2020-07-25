package com.dxc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public class AdminDaoImpl implements IAdminDao
{
	
	private static SessionFactory sessionFactory;
	Session session=sessionFactory.openSession();
	
	static
	{
		Configuration configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
		
		
	}
	public boolean adminLogin(Admin a)
	{
		 //  Session session=sessionFactory.openSession(); session.beginTransaction();
	       //   session.save(a); session.getTransaction().commit();
		
		 Query query1 = session.createQuery("from Admin where name=:name and password=:password"); 
		 
		 query1.setParameter("name",a.getName());
		 query1.setParameter("password",a.getPassword());
		 
		 List<Admin> list =query1.getResultList();
		  
		  for(Admin a1 : list) 
		  {
			  System.out.println("user-"+a1.getName());
		      System.out.println("pass-"+a1.getPassword());
		      
		      System.out.println("user-"+a.getName());
		      System.out.println("pass-"+a.getPassword());
		  
			  if(a.getName().equals(a1.getName()) && a.getPassword().equals(a1.getPassword())) 
			  {
				  return true;
			  }
		  }
	return false;
	}
	
		public boolean addProduct(Product p)
		{
			try
			{
				Session session=sessionFactory.openSession(); 
				
				Query query=session.createQuery("from Product");
				List<Product> list= query.getResultList();
				
				for(Product p1:list)
				{
					if((p.getProductNo()!=p1.getProductNo()))
					{
						session.beginTransaction();
						session.save(p);
						session.getTransaction().commit();
						return true;
					}
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return false;
		}
		
		public List<Product> getAllProducts()
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Product");
			return query.getResultList();	
		}
		
		public List<Product> getProduct(int i)
		{
			List<Product>list = new ArrayList<>();
			
			if(findProduct(i))
			{
				System.out.println("pnum-"+i);
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Query query = session.createQuery("from Product where productno=:i");
				query.setParameter("i", i);
				List<Product>list1 = query.getResultList();
				session.getTransaction().commit();
				return list1;
			}
			return list;
		}
		
		public boolean findProduct(int i)
		{
			Product p=null;
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Product where id=:i");
			query.setParameter("i", i);
			List<Product> p1=query.getResultList();
			try {
				p = p1.get(0);
				return true;
			}
			catch(Exception e)
			{	
				return false;
			}
		}

		
		public void addCustomer(Customer c) 
		{
			System.out.println(c.getId());
			System.out.println(c.getName());
			System.out.println(c.getPassword());
			
			Session session=sessionFactory.openSession(); 
			session.beginTransaction();
	        session.save(c); 
	        session.getTransaction().commit();
		}
		
		public boolean removeCustomer(int i) 
		{
			System.out.println("form i-"+i);
			if(findCustomer(i))
			{
				Session session=sessionFactory.openSession();
				session.beginTransaction();
				Query query=session.createQuery("delete from Customer where id=:i");
				query.setParameter("i", i);
				query.executeUpdate();
				session.getTransaction().commit();
				return true;
			}
		
		return false;
		}
		
		public boolean findCustomer(int i)
		{
			Customer c=null;
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Customer where id=:i");
			query.setParameter("i", i);
			List<Customer> c1=query.getResultList();
			try {
				c=c1.get(0);
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		
		public List<Customer>getAllCustomers()
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Customer");
			return query.getResultList();	
		}

}
