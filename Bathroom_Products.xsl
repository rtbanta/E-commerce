<?xml version="1.0" encoding="UTF-8"?>

<!-- Bathroom_Products.xsl : displays bathroom products from xml file in application. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
				<link rel="stylesheet" type="text/css" href="CSS/ProductsPages.css" />
				<title>Banta's Bargains</title>
				
				<style type="text/css">
					table
					{
						position:         relative;
						left:             40px;
						top:              90px;
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
				
				<form id="BuyForm" action="buy_product" method="post">
					<table>
						<xsl:for-each select="Products/Product">
							<xsl:if test="@id &gt;= 400 and @id &lt; 500">
	                			<tr>
	                				<td rowspan="4"><img src="{image}" width="80" height="80" /></td>   
	                				<td><xsl:value-of select="name" /></td>         	 			
	                    			<td rowspan="4"><input type="submit" name="buyButton" value="Buy product ID {@id}" /></td>
	                	 		</tr>
	                	 		<tr>
	                	 			<td>Quantity Available: <xsl:value-of select="quantity" /></td>
	                	 		</tr>
	                	 		<tr>
	                	 			<td>Quantity Requested: <input type="text" name="quantityRequested{@id}" size="2" value="1" /></td>
	                	 		</tr>
	                	 		<tr>
	                	 			<td>Price per unit: $<xsl:value-of select="price" /><hr /></td>
	                	 		</tr>
	                	 	</xsl:if>
	                 	</xsl:for-each>
	                 </table>
                 </form>
			</body>
		</html>	
	</xsl:template>
</xsl:stylesheet>