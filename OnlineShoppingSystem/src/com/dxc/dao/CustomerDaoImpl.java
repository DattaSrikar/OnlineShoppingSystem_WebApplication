package com.dxc.dao;

import java.util.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.cglib.transform.impl.AddPropertyTransformer;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.pojos.Bill;
import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.pojos.Wallet;

public class CustomerDaoImpl implements ICustomerDao
{
	private static SessionFactory sessionFactory;
	Session session=sessionFactory.openSession();
	
	static
	{
		Configuration configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
		
	}
	
			public boolean customerLogin(Customer c)
			{
				 Query query1 = session.createQuery("from Customer where id=:id and password=:password"); 
				 
				 query1.setParameter("id",c.getId());
				 query1.setParameter("password",c.getPassword());
				 
				 List<Customer> list =query1.getResultList();
				  
				  for(Customer c1 : list) 
				  {
					  System.out.println("id-"+c1.getId());
				      System.out.println("pass-"+c1.getPassword());
				      
				      System.out.println("id-"+c.getId());
				      System.out.println("pass-"+c.getPassword());
				  
					  if(c.getId()==c1.getId() && c.getPassword().equals(c1.getPassword())) 
					  {
						  return true;
					  }
				  }
			return false;
			}
			
			public List<Customer> getAllProducts()
			{
				Session session=sessionFactory.openSession();
				Query query=session.createQuery("from Product");
				return query.getResultList();
			}
			
			@Transactional
			public boolean addtocart(int i,int cId, Cart c,Product p)
			{
				Session session=sessionFactory.openSession();
				session.beginTransaction();
				
				Query query1 = session.createQuery("from Product where productno=:i");
				query1.setParameter("i",i);
				List<Product> plist = query1.getResultList();
				
				System.out.println(plist);
				
				for(Product p1:plist)
				{
					System.out.println("list pqu--"+p1.getQuantity());
					System.out.println("form quan--"+c.getQuantity());
					
					System.out.println("pname--> "+p1.getProductName());
					
					String name = p1.getProductName();
					
						if(c.getQuantity()<=p1.getQuantity())
						{
							System.out.println("Inside 2nd if");
							session.save(c);
							int q = p1.getQuantity() - c.getQuantity();
							Query query2 = session.createQuery("update Product set quantity=:quantity where productno=:productno");
							query2.setParameter("quantity",q);
							query2.setParameter("productno",c.getProductId());
							query2.executeUpdate();
							Query query3 = session.createQuery("update Cart set customerId=:cId where productId=:i");
							query3.setParameter("cId",cId);
							query3.setParameter("i",i);
							query3.executeUpdate();
							Query query4 = session.createQuery("update Cart set productName=:name where productId=:i");
							query4.setParameter("name",name);
							query4.setParameter("i",i);
							query4.executeUpdate();
							session.save(c);
							session.getTransaction().commit();
						}
						else
						{
							return false;
						}
					
				}
				return true;	
			}
	
			public boolean isAvaliable(int i)
			{
				System.out.println("Inside isavaliable");
				System.out.println("pro id-- "+i);
				Product p=null;
				Session session=sessionFactory.openSession();
				Query query=session.createQuery("from Product where productno=:i");
				query.setParameter("i", i);
				List<Product> p1=query.getResultList();
				try 
				{
					p = p1.get(0);
					return true;
				}
				catch(Exception e)
				{
					return false;
				}
			}
			
			public List<Cart> getCart(int i)
			{
				Session session=sessionFactory.openSession();
				Query query=session.createQuery("from Cart where customerid=:i");
				query.setParameter("i", i);
				return query.getResultList();
			}
			public void addAmount(Wallet w,double bal)
			{
				 int id = w.getCustomerId();
				 Query query =  session.createQuery("from Wallet where customerid=:id"); 
				 query.setParameter("id",id); 
		
				 List<Wallet> list = query.getResultList();
				 
				 System.out.println(list);
				 
				  if(list.isEmpty())
				  {
					  	System.out.println("cid-->"+id);
						
						w.setCustomerId(id);
						w.setBalance(bal);
						
						Session session = sessionFactory.openSession(); 
					    session.beginTransaction();
						session.save(w); 
						session.getTransaction().commit();	
						
						
				  }
				  else
				  {
					  double amount = w.getBalance();  // from form	
					  System.out.println("bal from form-->"+amount);
					  
					  Wallet w1 = list.get(0);
					  
					  double balance = w1.getBalance(); //from table
					  
					  System.out.println("bal from table-->"+balance);
					  
					  
					  balance = balance+amount;
					  
					  System.out.println("final bal-->"+balance);
					  
					 Query query1 = session.createQuery("update Wallet set balance=:balance where customerid=:id");
					 query1.setParameter("id",id); 
					 query1.setParameter("balance",balance);
					 session.beginTransaction();
					 query1.executeUpdate();
					 session.getTransaction().commit();
		
				  }
				  
			}
			
			public double getBalance(int i)
			{
				Session session=sessionFactory.openSession();
				Query query=session.createQuery("from Wallet where customerid=:i");
				query.setParameter("i", i);
				List<Wallet> list = query.getResultList();
				
				if(!(list.isEmpty()))
				{
					Wallet w1 = list.get(0);
					return w1.getBalance();
				}
				else
				{
					return 0.0;
				}
				
			}
				
			
			public List<Bill> getBill(int id,Cart c)
			{
				Bill b = new Bill();
				
				Session session=sessionFactory.openSession();
				session.beginTransaction();
				Query query=session.createQuery("from Cart where customerid=:id");
				query.setParameter("id",id);
				List<Cart>list = query.getResultList();
				
				for(Cart c1:list)
				{
					int pId = c1.getProductId();
					
					System.out.println("pro id from cart-->"+pId);
					
					int Cquantity = c1.getQuantity();
					
					Query query2=session.createQuery("from Product where productno=:pId");
					query2.setParameter("pId",pId);
					List<Product> list1 = query2.getResultList();
					
					Product p1 = list1.get(0);
					
					int pNo = p1.getProductNo();
					String pName = p1.getProductName();
					double pPrice = p1.getProductPrice();
					double pDiscount = p1.getDiscount(); 
			
					double finalPrice = (pPrice * Cquantity) - (pDiscount * Cquantity);
					
					System.out.println("pno-->"+pNo);
					System.out.println("pprice-->"+pPrice);
					System.out.println("pDis-->"+pDiscount);
					System.out.println("cQuan-->"+Cquantity);
					System.out.println("pname-->"+pName);
					System.out.println("Final Price in bill-->"+finalPrice);
				
					
					b.setCustomerId(id);
					b.setProductNo(pNo);
					b.setProductName(pName);
					b.setOriginalPrice(pPrice);
					b.setQuantity(Cquantity);
					b.setDiscount(pDiscount);
					b.setFinalPrice(finalPrice);
					
					session.merge(b);
				}
				
				
					session.getTransaction().commit();
					
					 Query query3=session.createQuery("from Bill where customerid=:id");
					 query3.setParameter("id",id);
					 return query3.getResultList();			
					
		}
			
			public boolean payBill(Bill b,int id) 
			{
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Query query = session.createQuery("from Bill where customerid=:id");
				query.setParameter("id",id);
				List<Bill> blist = query.getResultList();
				
				double bill=0.0;
				
				for(Bill b1:blist)
				{
					double finalprice = b1.getFinalPrice();
					
					bill = bill+finalprice;
					
					System.out.println("Final price from bill-->"+bill);
					
					Query query1 = session.createQuery("from Wallet where customerid=:id");
					query1.setParameter("id",id);
					List<Wallet> w = query1.getResultList();
					
					Wallet w1 = w.get(0);
					
					double amount = w1.getBalance();
					
					System.out.println("wallet amount-->"+amount);
					
					if(bill<=amount)
					{
						amount = amount - bill;
						
						System.out.println("final wallet amount-->"+amount);
						
						
					}
					else
					{
						return false;
					}
					
					Query query2  =session.createQuery("update Wallet set balance=:amount where customerid=:id");
					query2.setParameter("amount",amount);
					query2.setParameter("id",id);
					query2.executeUpdate();
					
					Query query3  =session.createQuery("delete from Cart where customerid=:id");
					query3.setParameter("id",id);
					query3.executeUpdate();
				}
				
				session.getTransaction().commit();
				return true;
			}
				
			
}
