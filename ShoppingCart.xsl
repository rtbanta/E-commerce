<?xml version="1.0" encoding="UTF-8"?>

<!-- ShoppingCart.xsl : displays products in user's shopping cart. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
				<link rel="stylesheet" type="text/css" href="CSS/ProductsPages.css" />
				<link rel="stylesheet" type="text/css" href="CSS/UserHomepage.css" />
				
				<title>Banta's Bargains</title>
				
				<style type="text/css">
					h3
                	{
                		position:			relative;
                		left:			   -263px;
                		top:				68px;
                	}
                	
					#AccountOptions
					{
						position:			relative;
						top:				50px;
						background-color:   #CCFF99;
						border:      1px solid blue;
						width:				180px;
						height:				200px;
					}
					
					#DisplayLink
					{
						position:		relative;
						left:				15px;
						top:				25px;
					}
					
					#UpdateLink
					{
						position:		relative;
						left:				15px;
						top:				37px;
					}
					
					#SellLink
					{
						position:		relative;
						left:				15px;
						top:				50px;
					}
					
					table
					{
						position:         relative;
						left:             340px;
						top:              -120px;
						border:         2px solid black;
					}
					
					td
					{
						padding:		8px;
					}
					
					#Images
					{
						background-color:   #CCCCFF;
						padding-left:		35px;
						padding-right:		35px;
						border:			2px solid #666699;
					}
					
					#Header
					{
						font-weight:	bold;
					}
					
					#BuyButton
					{
						position:		relative;
						left:			340px;
						top:			-40px;
					}
					
					#CancelButton
					{
						position:		relative;
						left:			430px;
						top:			-62px;
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
			
				<h3>Account Options</h3>
				<div id="AccountOptions">
					<a id="DisplayLink" href="http://localhost:8080/Final/display_funds">Display fund total</a>
					<br />
					<a id="UpdateLink" href="http://localhost:8080/Final/edit_info">Update your information</a>
					<br />
					<a id="SellLink" href="http://localhost:8080/Final/sell_product">Sell a product</a>
				</div>
				
				<form id="BuyForm" action="commit_to_buy" method="post">	
					<table>
						<xsl:for-each select="Products/Product">
                			<tr>
	                			<td rowspan="2"><img src="{image}" width="80" height="80" /></td>   
	                			<td id="Header">Name: <xsl:value-of select="name" /></td>         	 			
	                    		<td rowspan="2">Total Price: $<xsl:value-of select="price" /></td>
	                	 	</tr>
                	 		<tr>
                	 			<td>Quantity Bought: <xsl:value-of select="quantity" /></td>
                	 		</tr>
	                 	</xsl:for-each>
	                </table>
	                
	                <div id="BuyButton">
	                	<input type="submit" name="buyButton" value="Buy" />
	                </div>
	                <div id="CancelButton">
	                	<input type="submit" name="cancelButton" value="Cancel" />
	                </div>
	            </form>
		    </body>
		</html>	
	</xsl:template>
</xsl:stylesheet>