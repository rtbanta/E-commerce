<?xml version="1.0" encoding="UTF-8"?>

<!-- UpdateInfo.xsl : allows user to update information they submitted in
		registration form in the xml file in application. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
                <link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
                <link rel="stylesheet" type="text/css" href="CSS/RegisterForm.css" />
                <link rel="stylesheet" type="text/css" href="CSS/UpdateForm.css" />
                
				<title>Banta's Bargains</title>       
                
                <style type="text/css">
                	#firstName
					{
						position:	relative;
						left:		   100px;
						top:		    40px;
					}
					
					#firstLabel
					{
						position:	relative;
						left:		    80px;
						top:		   -20px;
					}
                	
                	#funds
                	{
                		position:		relative;
                		left:			1080px;
                		top:			-100px;
                	}
                	
                	#welcome
                	{
                		position:		relative;
                		left:			798px;
                		top:			-100px;
                	}
                	
                	#password
					{
						position:	relative;
						left:		   335px;
						top:		    0px;
					}
					
					#passwordLabel
					{
						position:	relative;
						left:			495px;
						top:		   -21px;
					}
					
					#zip
					{
						position:	relative;
						left:		   50px;
						top:		   -40px;
					}
					
					#zipLabel
					{
						position:	relative;
						left:		   132px;
						top:		   -60px;
					}
					
					#email
					{
						position:	relative;
						left:		  333px;
						top:		   -80px;
					}
					
					#emailLabel
					{
						position:	relative;
						left:		  494px;
						top:		   -100px;
					}
					
					#submitButton
					{
						position:	relative;
						left:		  490px;
						top:		 -115px;
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
				
				<h5 id="welcome">Welcome <xsl:value-of select="Persons/Person/userName" /></h5>
				<h5 id="funds">You have
				<div id="balance">$<xsl:value-of select="Persons/Person/balance" /></div> available.<br />
				<a href="http://localhost:8080/Final/log_out">Log-out</a>
				</h5>
					
				<form id="RegisterForm" action="editperson" method="post" onsubmit="return storeVals(this);">
					<br />
					<div id="firstName">
						<label id="firstLabel" for="firstname">First Name: </label>
						<input type="text" name="firstname" id="firstname" size="20" value="{Persons/Person/firstName}" />
					</div>
					<br />
					<div id="lastName">
						<label id="lastLabel" for="lastname">Last Name: </label>
						<input type="text" name="lastname" id="lastname" size="20" value="{Persons/Person/lastName}" />
					</div>
					<br />
					<div id="userName">
						<label id="userLabel" for="username">User Name: </label>
						<input type="text" name="username" id="username" size="20" value="{Persons/Person/userName}" />
					</div>
					<br />
					<div id="password">
						<label id="passwordLabel" for="password">Password: (2 uppercase letters, <br />2 lowercase letters, 2 numbers, <br />and 2 special characters)</label>
						<input type="password" name="password" id="password" size="20" />
					</div>
					<br />
					<div id="confirmPassword">
						<label id="confirmLabel" for="confirmpassword">Confirm Password: </label>
						<input type="password" name="confirmpassword" id="confirmpassword" size="20" />
					</div>
					<br />
					<div id="street">
						<label id="streetLabel" for="street">Street: </label>
						<input type="text" name="street" id="street" size="40" value="{Persons/Person/street}" />
					</div>				
					<br />
					<div id="city">
						<label id="cityLabel" for="city">City: </label>
						<input type="text" name="city" id="city" size="20" value="{Persons/Person/city}" />
					</div>
					<br />
					<div id="country">
						<label id="countryLabel" for="country">Country: </label>
						<input type="text" name="country" id="country" size="20" value="{Persons/Person/country}" />
					</div>
					<br />
					<div id="state">
					<label id="stateLabel" for="state">State: </label>
						<select name="state">
							<option value="NJ" selected="selected">NJ</option>
							<option value="NY">NY</option>
							<option value="PA">PA</option>
							<option value="CT">CT</option>
							<option value="none">None</option>
						</select>
					</div>
					<br />
					<div id="zip">
						<label id="zipLabel" for="zip">Zip: (#####)</label>
						<input type="text" name="zip" id="zip" size="15" value="{Persons/Person/zip}" />
					</div>
					<br />
					<div id="phone">
						<label id="phoneLabel" for="phone">Phone: </label>
						<input type="text" name="phone" id="phone" size="20" value="{Persons/Person/phone}" />
					</div>
					<br />
					<div id="email">
						<label id="emailLabel" for="email">E-mail: (name@domain)</label>
						<input type="text" name="email" id="email" size="30" value="{Persons/Person/email}" />
					</div>
					<div id="submitButton">
						<input type="submit" value="Submit" />
					</div>
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>