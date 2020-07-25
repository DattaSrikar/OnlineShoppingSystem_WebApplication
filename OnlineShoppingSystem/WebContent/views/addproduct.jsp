<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
</head>
<body style="background-color: hsla(89, 43%, 51%, 0.3);">

	<center>
			<form action="add" method="post"><br><br>
		
		Enter product number: <input type="text" name="productNo"><br><br>
		Enter product name:  <input type="text" name="productName"><br><br>
		Enter product price: <input type="text" name="productPrice"><br><br>
		Enter Quantity: <input type="text" name="quantity"><br><br>
		Enter Discount: <input type="text" name="discount"><br><br>

		<input type="submit" name="btn" value="AddProduct">		
		
		</form>
		
	</center>
	
</body>
</html>