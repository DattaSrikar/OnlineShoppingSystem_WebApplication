<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

					<style>
					table, tr, td
					{
						 border: 2px solid black;
						 width : 40%;
						text-align: center; 
						 border-collapse: collapse;
					}
					table.center 
					{
						margin-left: auto;
  						margin-right: auto;
  						
					}
  
					
				</style>


<meta charset="ISO-8859-1">

<title>Items in Cart!!</title>
</head>
<body style="background-color: hsla(89, 43%, 51%, 0.3);">
	
	<center><i><h1> Cart items</h1></i></center>
	
	         <table class="center">
								
								<tr>
									<td>Product No</td>
									<td>Customer Id</td>
									<td>Product Name</td>
									<td>Quantity</td>
								
									
									</tr>
	
	<c:forEach var="s" items="${list}">
								
							<tr>
									<td><c:out value="${s.getProductId()}"></c:out></td>
									<td><c:out value="${s.getCustomerId()}"></c:out></td>									
									<td><c:out value="${s.getProductName()}"></c:out></td>
									<td><c:out value="${s.getQuantity()}"></c:out></td>

							</tr>
							
					</c:forEach>		
					</table>
		
	
</body>
</html>