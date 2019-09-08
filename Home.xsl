<?xml version="1.0" encoding="UTF-8"?>

<!-- Home.xsl : default home page for application accessible to all users. -->

<!DOCTYPE space_character [ <!ENTITY nbsp "&#160;"> ]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>				
                <link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
			    <title>Banta's Bargains</title> 
                 
                <style type="text/css">
                	#loginArea
					{
						position:         relative;
						left:             500px;
						top:              0px;
						width:            180px;
						height:           150px;
						padding-left:     20px;
						background-color: #FFFFFF;
						border:           2px solid black;
						visibility:       hidden;
					}
                </style>     
                
                <script type="text/javascript">  
                  <![CDATA[
                 /***********************************************
                 *   LoginDisplay()
				 * 
				 * NAME:
				 * 
				 * 		LoginDisplay() - handles showing and hiding the username 
				 *                      and password popup box. 
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function LoginDisplay(showhide)
				 * 
				 * 			@param  showhide   --> string indicating if popup should be
				 *									shown or hidden.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Handles showing and hiding the username and password popup box. 
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
		            function LoginDisplay(showhide)
		            {
  			            if(showhide == "show")
  			            {
    				        document.getElementById('loginArea').style.visibility="visible";
 					        document.getElementById('userName').focus();
  			            }
  			            else if(showhide == "hide")
  			            {
     				        document.getElementById('loginArea').style.visibility="hidden";
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
				
				<div id="loginLink">
			        <a href="javascript:LoginDisplay('show');">Login</a><br />
			        <a href="http://localhost:8080/Final/Register">Registeration</a>
		        </div>
				
				<div id="shelf">
				<img src="IMG/full_shelf.jpg" height="160" width="250" />
				</div>
				
	            <form id="LoginForm" action="verifylogin" method="post" onsubmit="return storeVals(this);">
		            <div id="loginArea">
			            <label for="userName">Username: </label>
			            <input type="text" name="userName" id="userName" size="15" />
			            <br />
			            <label for="password">Password: </label>
			            <input type="password" name="password" id="password" size="15" />
			            <br /> 
			            <input type="submit" value="Submit" /><br />
			            <a href="javascript:LoginDisplay('hide');">Close</a>
		            </div>
	            </form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>