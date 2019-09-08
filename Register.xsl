<?xml version="1.0" encoding="UTF-8"?>

<!-- Register.xsl : allows user to add themselves to the application. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
                <link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
                <link rel="stylesheet" type="text/css" href="CSS/RegisterForm.css" />
                
				<title>Banta's Bargains</title>  
				<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" type="text/javascript"></script>       
                
                <style type="text/css">
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
                </style>
                <script type="text/javascript">  
                  <![CDATA[
                 /***********************************************
                 *   (document).ready()
				 * 
				 * NAME:
				 * 
				 * 		(document).ready() - populates form fields with values saved
				 *							in javascript cookie.
				 * 
				 * SYNOPSIS:
				 * 
				 * 		(document).ready()
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Populates form fields with values saved in javascript cookie. 
				 * 
				 * RETURNS:
				 * 
				 * 		None
				 * 
				 * AUTHOR:
				 * 
				 * 		Robert Banta
				 * 
				 * DATE:
				 * 
				 * 		2/2/2013
				 * 
                 ************************************************/
                    $(document).ready( function() {
                      if(firstname = GetCookie("first"))
                      {
                         document.getElementById("RegisterForm").firstname.value = firstname;
                      }
                      if(lastname = GetCookie("last"))
                      {
                         document.getElementById("RegisterForm").lastname.value = lastname;
                      }
                      if(username = GetCookie("user"))
                      {
                         document.getElementById("RegisterForm").username.value = username;
                      }
                      if(street = GetCookie("street"))
                      {
                         document.getElementById("RegisterForm").street.value = street;
                      }
                      if(city = GetCookie("city"))
                      {
                         document.getElementById("RegisterForm").city.value = city;
                      }
                      if(country = GetCookie("country"))
                      {
                         document.getElementById("RegisterForm").country.value = country;
                      }
                      if(zip = GetCookie("zip"))
                      {
                         document.getElementById("RegisterForm").zip.value = zip;
                      }
                      if(phone = GetCookie("phone"))
                      {
                         document.getElementById("RegisterForm").phone.value = phone;
                      }
                      if(email = GetCookie("email"))
                      {
                         document.getElementById("RegisterForm").email.value = email;
                      }
                  
                    });
                  
                    var today = new Date();
                    var expireDate = new Date(today.getTime() + 30 * 24 * 3600 * 1000); // 30 days
                    
                 /***********************************************
                 *   StoreVals()
				 * 
				 * NAME:
				 * 
				 * 		StoreVals() - populates form fields with values saved
				 *							in javascript cookie if values exist.
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function StoreVals(form)
				 *
				 *			@param  form   --> form with values of fields.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Populates form fields with values saved in javascript cookie
				 *      if values exist. 
				 * 
				 * RETURNS:
				 * 
				 * 		boolean value - true if values entered are valid, false if not.
				 * 
				 * AUTHOR:
				 * 
				 * 		Robert Banta
				 * 
				 * DATE:
				 * 
				 * 		2/2/2013
				 * 
                 ************************************************/
                    function StoreVals(form)
                    {
                       if(document.getElementById("RegisterForm").firstname.value == "")
                       {
                          alert("Please enter your first name.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").lastname.value == "")
                       {
                          alert("Please enter your last name.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").username.value == "")
                       {
                          alert("Please enter your user name.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").password.value == "")
                       {
                          alert("Please enter a password.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").confirmpassword.value != document.getElementById("RegisterForm").password.value)
                       {
                          alert("Please confirm your password from the password field.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").street.value == "")
                       {
                          alert("Please enter a street.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").city.value == "")
                       {
                          alert("Please enter your home town or city.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").country.value == "")
                       {
                          alert("Please enter your home country.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").zip.value == "")
                       {
                          alert("Please enter your zip code.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").phone.value == "")
                       {
                          alert("Please enter your phone number.");
                          return false;
                       }
                       else if(document.getElementById("RegisterForm").email.value == "")
                       {
                          alert("Please enter your email address.");
                          return false;
                       }
                       else
                       {
                          SetCookie("first", "last", "user", "street", "city", "country", "zip", "phone", "email", form.firstname.value, form.lastname.value, form.username.value, form.street.value, form.city.value, form.country.value, form.zip.value, form.phone.value, form.email.value);
                          return true;
                       }
                    }
               
                 /***********************************************
                 *   SetCookie()
				 * 
				 * NAME:
				 * 
				 * 		SetCookie() - store form values in cookie.
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function SetCookie(first, last, user, street, city, country, zip, phone, email, firstValue, lastValue, userValue, streetValue, cityValue, countryValue, zipValue, phoneValue, emailValue)
				 *
				 *			@param  first,last,user,street,city,country,state,zip,phone,email   --> strings representing type of data.
				 *
				 *			@param  firstValue,lastValue,userValue,streetValue,cityValue,countryValue,zipValue,phoneValue,emailValue   --> values of corresponding string data.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Store form values in cookie.
				 * 
				 * RETURNS:
				 * 
				 * 		None
				 * 
				 * AUTHOR:
				 * 
				 * 		Robert Banta
				 * 
				 * DATE:
				 * 
				 * 		2/2/2013
				 * 
                 ************************************************/
                    function SetCookie(first, last, user, street, city, country, zip, phone, email, firstValue, lastValue, userValue, streetValue, cityValue, countryValue, zipValue, phoneValue, emailValue)
                    {
                       document.cookie = first + "=" + escape(firstValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = last + "=" + escape(lastValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = user + "=" + escape(userValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = street + "=" + escape(streetValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = city + "=" + escape(cityValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = country + "=" + escape(countryValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = zip + "=" + escape(zipValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = phone + "=" + escape(phoneValue) + "; path=/; expires=" + expireDate.toGMTString();
                       document.cookie = email + "=" + escape(emailValue) + "; path=/; expires=" + expireDate.toGMTString();
                    }
               
                  /***********************************************
                 *   GetCookie()
				 * 
				 * NAME:
				 * 
				 * 		GetCookie() - get value from cookie.
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function GetCookie(name)
				 *
				 *			@param  name  -->  value to retrieve from cookie.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Get value from cookie.
				 * 
				 * RETURNS:
				 * 
				 * 		Value stored in cookie, or null if no value found.
				 * 
				 * AUTHOR:
				 * 
				 * 		Robert Banta
				 * 
				 * DATE:
				 * 
				 * 		2/2/2013
				 * 
                 ************************************************/
                    function GetCookie(name)
                    {
                       var exp = new RegExp(name + "=([^;]+)");
                       var value = exp.exec(document.cookie);
                       
                       if(value != null)
                       {
                          return unescape(value[1]);
                       }
                       else
                       {
                          return "";
                       }
                    }
		          ]]>
	            </script>
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
					
				<form id="RegisterForm" action="addperson" method="post" onsubmit="return StoreVals(this);">
					<div id="firstName">
						<label id="firstLabel" for="firstname">First Name: </label>
						<input type="text" name="firstname" id="firstname" size="20" />
					</div>
					<br />
					<div id="lastName">
						<label id="lastLabel" for="lastname">Last Name: </label>
						<input type="text" name="lastname" id="lastname" size="20" />
					</div>
					<br />
					<div id="userName">
						<label id="userLabel" for="username">User Name: </label>
						<input type="text" name="username" id="username" size="20" />
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
						<input type="text" name="street" id="street" size="35" />
					</div>				
					<br />
					<div id="city">
						<label id="cityLabel" for="city">City: </label>
						<input type="text" name="city" id="city" size="20" />
					</div>
					<br />
					<div id="country">
						<label id="countryLabel" for="country">Country: </label>
						<input type="text" name="country" id="country" size="20" />
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
						<input type="text" name="zip" id="zip" size="15" />
					</div>
					<br />
					<div id="phone">
						<label id="phoneLabel" for="phone">Phone: </label>
						<input type="text" name="phone" id="phone" size="20" />
					</div>
					<br />
					<div id="email">
						<label id="emailLabel" for="email">E-mail: (name@domain)</label>
						<input type="text" name="email" id="email" size="30" />
					</div>
					<div id="submitButton">
						<input type="submit" value="Submit" />
					</div>
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>