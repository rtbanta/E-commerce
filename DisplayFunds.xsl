<?xml version="1.0" encoding="UTF-8"?>

<!-- DisplayFunds.xsl : displays current funds balance of user. -->

<!DOCTYPE space_character [ <!ENTITY nbsp "&#160;"> ]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
                <link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
                <link rel="stylesheet" type="text/css" href="CSS/RegisterForm.css" />
                <link rel="stylesheet" type="text/css" href="CSS/UserHomepage.css" />
                
				<title>Banta's Bargains</title>  
				
				<style type="text/css">
                	#addFundsArea
					{
						position:         relative;
						left:             500px;
						top:              -90px;
						width:            230px;
						height:           150px;
						padding-left:     20px;
						background-color: #FFFFFF;
						border:           2px solid black;
						visibility:       hidden;
					}
					
                	h3
                	{
                		position:			relative;
                		left:				20px;
                		top:				-28px;
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
                		left:			800px;
                		top:			-100px;
                	}
                	
                	#AccountOptions
					{
						position:			relative;
						top:				-40px;
						background-color:   #CCFF99;
						border:      1px solid blue;
						width:				180px;
						height:				200px;
					}
					
					#FundsLink
					{
						position:		relative;
						left:				15px;
						top:				15px;
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
					
					#DeRegisterLink
					{
						position:		relative;
						left:				15px;
						top:				60px;
					}
					
					#FundsDisplay
					{
						position:		 relative;
						left:				450px;
						top:				-350px;
						padding:			10px;
						width:              270px;
						color:			   #400000;
					}
                </style>    
                
                <script type="text/javascript">  
                  <![CDATA[          
                 /***********************************************
                 *   AddFundsDisplay()
				 * 
				 * NAME:
				 * 
				 * 		AddFundsDisplay() - handles showing and hiding the add funds
				 *						popup box. 
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function AddFundsDisplay(showhide)
				 * 
				 * 			@param  showhide   --> string indicating if popup should be
				 *									shown or hidden.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Handles showing and hiding the add funds popup box. 
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
				 * 		3/16/2013
				 * 
                 ************************************************/    
                    function AddFundsDisplay(showhide)
		            {
  			            if(showhide == "show")
  			            {
    				        document.getElementById('addFundsArea').style.visibility="visible";
 					        document.getElementById('fundsVal').focus();
  			            }
  			            else if(showhide == "hide")
  			            {
     				        document.getElementById('addFundsArea').style.visibility="hidden";
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
				
				<h5 id="welcome">Welcome <xsl:value-of select="Persons/Person/username" /></h5>
				<h5 id="funds">You have
				<div id="balance">$<xsl:value-of select="Persons/Person/balance" /></div> available.<br />
				<a href="http://localhost:8080/Final/log_out">Log-out</a>
				</h5>
				
				<h3>Account Options</h3>
				<div id="AccountOptions">
					<a id="FundsLink" href="javascript:AddFundsDisplay('show');">Add funds</a>
					<br />
					<a id="DisplayLink" href="http://localhost:8080/Final/display_funds">Display fund total</a>
					<br />
					<a id="UpdateLink" href="http://localhost:8080/Final/edit_info">Update your information</a>
					<br />
					<a id="SellLink" href="http://localhost:8080/Final/sell_product">Sell a product</a>
					<br />
					<a id="DeRegisterLink" href="http://localhost:8080/Final/de_register" onclick="return VerifyAction();">De-Register</a>
				</div>
					
				<form id="AddFundsForm" action="add_funds" method="post" onsubmit=" return checkVal(this);">
		            <div id="addFundsArea">
			            <label for="userName">Input total for deposit: (&lt;= 1000)</label>
			            <input type="text" name="fundsVal" id="fundsVal" size="15" />
			            <br />
			            <input type="submit" value="Submit" /><br />
			            <a href="javascript:AddFundsDisplay('hide');">Close</a>
		            </div>
	            </form>
	            
	            <h4 id="FundsDisplay">You currently have <br />&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;$<xsl:value-of select="Persons/Person/balance" /><br />available in your account.</h4>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>