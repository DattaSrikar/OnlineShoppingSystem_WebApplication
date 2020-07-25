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
						 width : 60%;
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

<title>Bill!!</title>
</head>
<body style="background-color: hsla(89, 43%, 51%, 0.3);">
	
	<center><i><h1>Bill</h1></i></center>
	
	         <table class="center">
								
								<tr>
									<td>Customer Id</td>
									<td>Product No</td>
									<td>Product Name</td>
									<td>OriginalPrice</td>
									<td>QuantityOrdered</td>
									<td>Discount</td>
									<td>FinalPrice</td>	
									</tr>
	
	<c:forEach var="s" items="${list}">
								
							<tr>
									
									<td><c:out value="${s.getCustomerId()}"></c:out></td>	
									<td><c:out value="${s.getProductNo()}"></c:out></td>								
									<td><c:out value="${s.getProductName()}"></c:out></td>
									<td><c:out value="${s.getOriginalPrice()}"></c:out></td>
									<td><c:out value="${s.getQuantity()}"></c:out></td>
									<td><c:out value="${s.getDiscount()}"></c:out></td>
									<td><c:out value="${s.getFinalPrice()}"></c:out></td>

							</tr>
							
					</c:forEach>		
					</table><br><br>
					
					<form action="paybill">
						<center><input type="submit" value="Make payment"></center>
					</form>
	
</body>
</html>