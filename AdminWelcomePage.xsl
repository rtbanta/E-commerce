<?xml version="1.0" encoding="UTF-8"?>

<!-- AdminWelcomePage.xsl : provides administrator choice of adding, editing,
		or deleting products. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="CSS/Navbar.css" />
                <link rel="stylesheet" type="text/css" href="CSS/Homepage.css" />
                
				<title>User Welcome Page</title>  
				
				<style type="text/css">
                	#editArea
					{
						position:         relative;
						left:             500px;
						top:              -150px;
						width:            180px;
						height:           150px;
						padding-left:     20px;
						background-color: #FFFFFF;
						border:           2px solid black;
						visibility:       hidden;
					}
					#deleteArea
					{
						position:         relative;
						left:             500px;
						top:              -280px;
						width:            180px;
						height:           150px;
						padding-left:     20px;
						background-color: #FFFFFF;
						border:           2px solid black;
						visibility:       hidden;
					}
					
					h3
                	{
                		position:			relative;
                		left:			    -250px;
                		top:				10px;
                	}
                	
                	#welcome
                	{
                		position:			relative;
                		left:				750px;
                		top:				-30px;
                	}
                	
                	#logoutLink
					{
						position:		relative;
						left:			1050px;
						top:			-45px;
					}
                	
					#AccountOptions
					{
						position:			relative;
						top:				 0px;
						background-color:   #CCFF99;
						border:      1px solid blue;
						width:				180px;
						height:				200px;
					}
					
					#AddLink
					{
						position:		relative;
						left:				15px;
						top:				15px;
					}
					
					#EditLink
					{
						position:		relative;
						left:				15px;
						top:				25px;
					}
					
					#DeleteLink
					{
						position:		relative;
						left:				15px;
						top:				37px;
					}
                </style>
				
				<script type="text/javascript">  
                  <![CDATA[
                 /***********************************************
                 *   EditDisplay()
				 * 
				 * NAME:
				 * 
				 * 		EditDisplay() - handles showing and hiding the edit product
				 *						popup box. 
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function EditDisplay(showhide)
				 * 
				 * 			@param  showhide   --> string indicating if popup should be
				 *									shown or hidden.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Handles showing and hiding the edit product popup box. 
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
				 * 		2/17/2013
				 * 
                 ************************************************/
                    function EditDisplay(showhide)
		            {
  			            if(showhide == "show")
  			            {
    				        document.getElementById('editArea').style.visibility="visible";
 					        document.getElementById('productID').focus();
  			            }
  			            else if(showhide == "hide")
  			            {
     				        document.getElementById('editArea').style.visibility="hidden";
  			            }
		            }
		            
		         /***********************************************
                 *   DeleteDisplay()
				 * 
				 * NAME:
				 * 
				 * 		DeleteDisplay() - handles showing and hiding the delete product
				 *						popup box. 
				 * 
				 * SYNOPSIS:
				 * 
				 * 		function DeleteDisplay(showhide)
				 * 
				 * 			@param  showhide   --> string indicating if popup should be
				 *									shown or hidden.
				 * 
				 * DESCRIPTION:
				 * 
				 * 		Handles showing and hiding the delete product popup box. 
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
				 * 		2/17/2013
				 * 
                 ************************************************/
		            function DeleteDisplay(showhide)
		            {
  			            if(showhide == "show")
  			            {
    				        document.getElementById('deleteArea').style.visibility="visible";
 					        document.getElementById('deleteProductID').focus();
  			            }
  			            else if(showhide == "hide")
  			            {
     				        document.getElementById('deleteArea').style.visibility="hidden";
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
				<a id="logoutLink" href="http://localhost:8080/Final/log_out">Log-out</a>
				
				<h3>Account Options</h3>
				<div id="AccountOptions">
					<a id="AddLink" href="http://localhost:8080/Final/admin_add_product">Add a product</a>
					<br />
					<a id="EditLink" href="javascript:EditDisplay('show');">Edit a product</a>
					<br />
					<a id="DeleteLink" href="javascript:DeleteDisplay('show');">Delete a product</a>
				</div>
				
				<form id="EditForm" action="edit_product" method="post">
		            <div id="editArea">
			            <label for="pdtID">Product ID to edit: </label>
			            <input type="text" name="productID" id="productID" size="15" />
			            <br />
			            <input type="submit" value="Submit" /><br />
			            <a href="javascript:EditDisplay('hide');">Close</a>
		            </div>
	            </form>
	            
	            <form id="DeleteForm" action="delete_product" method="post">
		            <div id="deleteArea">
			            <label for="deletePdtID">Product ID to delete: </label>
			            <input type="text" name="deleteProductID" id="deleteProductID" size="15" />
			            <br />
			            <input type="submit" value="Submit" /><br />
			            <a href="javascript:DeleteDisplay('hide');">Close</a>
		            </div>
	            </form>
	            
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>