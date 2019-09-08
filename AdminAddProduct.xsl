<?xml version="1.0" encoding="UTF-8"?>

<!-- AdminAddProduct.xsl : allows administrator to add products to application. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
                <link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
                
				<title>Banta's Bargains</title>  
				
				<style type="text/css">
					#SellForm
					{
						position:		relative;
						left:			350px;
						top:			60px;
						background-color: #FFFFCC;
						width:			300px;
						height:			250px;
					}
					
					#welcome
					{
						position:		relative;
						left:			750px;
						top:			-30px;
					}
					
					#logoutLink
					{
						position:		relative;
						left:			1050px;
						top:			-45px;
					}
				</style>       
				                 
			</head>
			
			<body>
				<div id="banner">
					<img src="IMG/banner.jpg" height="90" width="650" />
				</div>
				
				<ul id="navMenu">
					<li class="topLevel"><a href="/Final/home">Home</a></li>
					<li class="topLevel"><a href="">Products</a>
						<ul>
							<li><a href="http://localhost:8080/Final/living_room_products">Living Room</a></li>
							<li><a href="http://localhost:8080/Final/kitchen_products">Kitchen</a></li>
							<li><a href="http://localhost:8080/Final/bedroom_products">Bedroom</a></li>
							<li><a href="http://localhost:8080/Final/bathroom_products">Bathroom</a></li>
							<li><a href="http://localhost:8080/Final/recreational_products">Recreational</a></li>
						</ul>
					</li>
					<li class="topLevel"><a href="">Deals</a>
						<ul>
							<li><a href="http://localhost:8080/Final/livingroom_deals">Living Room</a></li>
							<li><a href="http://localhost:8080/Final/kitchen_deals">Kitchen</a></li>
							<li><a href="http://localhost:8080/Final/bedroom_deals">Bedroom</a></li>
						</ul>
					</li>
				</ul>
					
				<h5 id="welcome">Welcome <xsl:value-of select="Persons/Person/username" /></h5>
				<a id="logoutLink" href="http://localhost:8080/Final/log_out">Log-out</a>
					
				<form id="SellForm" action="admin_sell_submit" method="post">
					<br />
					<div id="productType">
					<label id="productLabel" for="productType">Type: </label>
						<select id="productSelect" name="productType">
							<option value="livingRoom" selected="selected">Living Room</option>
							<option value="kitchen">Kitchen</option>
							<option value="bedRoom">Bedroom</option>
							<option value="bathRoom">Bathroom</option>
							<option value="recreational">Recreational</option>
						</select>
					</div>
					<br />
					<div id="productName">
						<label id="productNameLabel" for="productName">Name: </label>
						<input type="text" name="productName" id="productName" size="20" />
					</div>
					<br />
					<div id="productPrice">
						<label id="productPriceLabel" for="productPrice">Price: </label>
						<input type="text" name="productPrice" id="productPrice" size="20" />
					</div>
					<br />
					<div id="productQuantity">
						<label id="productQuantityLabel" for="productQuantity">Quantity: </label>
						<input type="text" name="productQuantity" id="productQuantity" size="20" />
					</div>
					<br />
					
					<input type="submit" value="Submit" />
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>