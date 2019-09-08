<?xml version="1.0" encoding="ISO-8859-1"?>

<!-- UserWelcomePage.xsl : welcome user to application after login and lets 
		user proceed to their account page. -->

<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
                
                <title>User Welcome Page</title>
                
                <style type="text/css">
						
                	#shelf
					{
						position:         relative;
						left:             450px;
						top:              -230px;
					}
					
                	h3
                	{
                		position:			relative;
                		left:				30px;
                		top:				-28px;
                	}
                	
                	#topright
                	{
                		position:		relative;
                		left:			1080px;
                		top:			-100px;
                	}
                	
                	#welcome
                	{
                		position:		relative;
                		left:			-285px;
                	}
                	
                	#AccountLinks
					{
						position:			relative;
						top:				 -40px;
						background-color:   #CCFF99;
						border:      1px solid blue;
						width:				180px;
						height:				200px;
					}
					
					#ActLink
					{
						position:		relative;
						left:				15px;
						top:				15px;
					}
					
                </style>
				  
			</head>
			<body>   			
				<div id="banner">
					<img src="IMG/banner.jpg" height="90" width="650" />
				</div>
														
				<ul id="navMenu">
					<li class="topLevel"><a href="http://localhost:8080/Final/home">Home</a></li>
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
				
				<div id="topright">
					<h5 id="welcome">Welcome <xsl:value-of select="Persons/Person/username" /></h5>
					<h5 id="funds">You have
					<div id="balance">$<xsl:value-of select="Persons/Person/balance" /></div> available.<br />
					<a href="http://localhost:8080/Final/log_out">Log-out</a>
					</h5>
				</div>
				
				<h3>Settings</h3>
				<div id="AccountLinks">
					<a id = "ActLink" href="http://localhost:8080/Final/user_account">Manage Account</a>
				</div>
				
				<div id="shelf">
					<img src="IMG/full_shelf.jpg" height="160" width="250" />
				</div>
				
			</body>
		</html>
	</xsl:template>
</xsl:transform>