/*****************************************************************
 * DispatcherServlet.java - finds the root of the application,     *
 * 		initializes XML and XSL pages used by application,       *
 * 		manages sessions and accepts all incoming http requests  *
 * 		and controls the pages sent to the user.                 *
 *****************************************************************/
package controller;
import model.*;
import view.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;

public class DispatcherServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	//list of users and their session ID's to prevent multiple logins
	private static ArrayList<Integer> m_currentSessions = new ArrayList<Integer>();
	
	private String m_projectRoot;
	private HttpServlet m_servlet;
	private XMLPages m_productPage = new XMLPages();
	private XMLPages m_defaultPage = new XMLPages();
	private XSLPages m_homeXslPage = new XSLPages();
	private XSLPages m_homeDoubleLoginXslPage = new XSLPages();
	private XSLPages m_homeNotLoggedInErrorXslPage = new XSLPages();
	private XSLPages m_homeInvalidLoginXslPage = new XSLPages();
	private XSLPages m_adminWelcomeXslPage = new XSLPages();
	private XSLPages m_userWelcomeXslPage = new XSLPages();
	private XSLPages m_registerXslPage = new XSLPages();
	private XSLPages m_adminAddProductXslPage = new XSLPages();
	private XSLPages m_livingroomProductsXslPage = new XSLPages();
	private XSLPages m_kitchenProductsXslPage = new XSLPages();
	private XSLPages m_bedroomProductsXslPage = new XSLPages();
	private XSLPages m_bathroomProductsXslPage = new XSLPages();
	private XSLPages m_recreationalProductsXslPage = new XSLPages();
	private XSLPages m_livingroomDealsXslPage = new XSLPages();
	private XSLPages m_kitchenDealsXslPage = new XSLPages();
	private XSLPages m_bedroomDealsXslPage = new XSLPages();
	private XSLPages m_userAccountXslPage = new XSLPages();
	private XSLPages m_userFundsXslPage = new XSLPages();
	private XSLPages m_updateInfoXslPage = new XSLPages();
	private XSLPages m_sellProductXslPage = new XSLPages();
	private XSLPages m_editProductXslPage = new XSLPages();
	private XSLPages m_shoppingCartXslPage = new XSLPages();
	private XSLPages m_shoppingCartErrorXslPage = new XSLPages();
	private XSLPages m_afterPurchaseXslPage = new XSLPages();
	
	/**
	 * DispatcherServlet::CreateSession()
	 * 
	 * NAME:
	 * 
	 * 		DispatcherServlet::CreateSession() - adds new user session to HashMap.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		synchronized void CreateSession(int a_userID)
	 * 
	 * 			@param  a_userID   --> unique ID of the user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Adds key/value pair of user ID/session ID to HashMap to track user and
	 * 		prevent user from logging in multiple times.
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
	 */
	public static synchronized void CreateSession(int a_userID)
	{
		m_currentSessions.add(a_userID);
	}
	
	/**
	 * DispatcherServlet::DeleteSession()
	 * 
	 * NAME:
	 * 
	 * 		DispatcherServlet::DeleteSession() - removes user session to HashMap.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		synchronized void DeleteSession(int a_userID)
	 * 
	 * 			@param  a_userID   --> unique ID of the user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Removes key/value pair of user ID/session ID from HashMap, 
	 *      using passed-in ID of user, when user logs-out of application.
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
	 */
	public static synchronized void DeleteSession(int a_userID)
	{
		Iterator <Integer> it = m_currentSessions.iterator();
		
		while(it.hasNext())
		{
			if(it.next() == a_userID)
			{
				it.remove();
			}
		}
	}
	
	/**
	 * DispatcherServlet::SetProjectRoot()
	 * 
	 * NAME:
	 * 
	 * 		DispatcherServlet::SetProjectRoot() - sets the root of the application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		void SetProjectRoot(ServletContext a_servletContext)
	 * 
	 * 			@param  a_servletContext   --> ServletContext object holding current
	 * 											location of application root.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Sets the root of the application to the class to enable easier 
	 * 		porting and locating of files used by the application.
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
	 */
	public void SetProjectRoot(ServletContext a_servletContext)
	{
		m_projectRoot = a_servletContext.getRealPath("/");
	}
	
	/**
	 * DispatcherServlet::GetProjectRoot()
	 * 
	 * NAME:
	 * 
	 * 		DispatcherServlet::GetProjectRoot() - gets the root of the application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		String GetProjectRoot()
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Gets the root of the application stored in the class.
	 * 
	 * RETURNS:
	 * 
	 * 		String that represents path to current application root.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		3/16/2013
	 * 
	 */
	public String GetProjectRoot()
	{
		return m_projectRoot;
	}

	/**
	 * DispatcherServlet::doPost()
	 * 
	 * NAME:
	 * 
	 * 		DispatcherServlet::doPost() - handles all requests to the application.
	 * 						Parses request and returns new page. 
	 * 
	 * SYNOPSIS:
	 * 
	 * 		void doPost(HttpServletRequest a_request, HttpServletResponse a_response)
	 * 				throws ServletException, IOException
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * 			@param  a_response  --> response object, to be used when returning page
	 * 									to the user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Parses request, uses data from request based on what user sent, 
	 *      and returns new page.  Uses multithreading to handle more than one user.
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
	 * 		1/30/2013
	 * 
	 */
	protected void doPost(HttpServletRequest a_request, HttpServletResponse a_response) throws ServletException, IOException
	{
		//set the root directory of this application
		m_servlet = this;
		ServletContext sc = this.m_servlet.getServletContext();
		SetProjectRoot(sc);
		
		//load xml files
		String personFilePath = sc.getRealPath("XML/Person.xml");
		String productFilePath = sc.getRealPath("XML/Products.xml");
		String defaultFilePath = sc.getRealPath("XML/Default.xml");
		Document productXML = m_productPage.LoadFile(productFilePath);
		Document defaultXML = m_defaultPage.LoadFile(defaultFilePath);
		
		//load xsl files
		String homeFilePath = sc.getRealPath("XSL/Home.xsl");
		String homeDoubleLoginErrorFilePath = sc.getRealPath("XSL/Home_Double_Login_Error.xsl");
		String homeNotLoggedInErrorFilePath = sc.getRealPath("XSL/Home_Not_Logged_In_Error.xsl");
		String homeWrongCredentialsErrorFilePath = sc.getRealPath("XSL/Home_Wrong_Credentials.xsl");
		String adminWelcomeFilePath = sc.getRealPath("XSL/AdminWelcomePage.xsl");
		String userWelcomeFilePath = sc.getRealPath("XSL/UserWelcomePage.xsl");
		String registerFormFilePath = sc.getRealPath("XSL/Register.xsl");
		String adminAddProductFormFilePath = sc.getRealPath("XSL/AdminAddProduct.xsl");
		String livingRoomProductsFilePath = sc.getRealPath("XSL/Livingroom_Products.xsl");
		String kitchenProductsFilePath = sc.getRealPath("XSL/Kitchen_Products.xsl");
		String bedRoomProductsFilePath = sc.getRealPath("XSL/Bedroom_Products.xsl");
		String bathRoomProductsFilePath = sc.getRealPath("XSL/Bathroom_Products.xsl");
		String recreationalProductsFilePath = sc.getRealPath("XSL/Recreational_Products.xsl");
		String livingRoomDealsFilePath = sc.getRealPath("XSL/Livingroom_Deals.xsl");
		String kitchenDealsFilePath = sc.getRealPath("XSL/Kitchen_Deals.xsl");
		String bedRoomDealsFilePath = sc.getRealPath("XSL/Bedroom_Deals.xsl");
		String userAccountFilePath = sc.getRealPath("XSL/UserAccountPage.xsl");
		String userFundsFilePath = sc.getRealPath("XSL/DisplayFunds.xsl");
		String updateInfoFilePath = sc.getRealPath("XSL/UpdateInfo.xsl");
		String sellProductFilePath = sc.getRealPath("XSL/SellProduct.xsl");
		String editProductFilePath = sc.getRealPath("XSL/EditProduct.xsl");
		String shoppingCartFilePath = sc.getRealPath("XSL/ShoppingCart.xsl");
		String shoppingCartErrorFilePath = sc.getRealPath("XSL/UserAccountPage_Cart_Error.xsl");
		String afterPurchaseFilePath = sc.getRealPath("XSL/After_Purchase.xsl");
		File homeXSL = m_homeXslPage.LoadFile(homeFilePath);	
		File homeDoubleLoginErrorXSL = m_homeDoubleLoginXslPage.LoadFile(homeDoubleLoginErrorFilePath);
		File homeNotLoggedInErrorXSL = m_homeNotLoggedInErrorXslPage.LoadFile(homeNotLoggedInErrorFilePath);
		File homeWrongCredentialsXSL = m_homeInvalidLoginXslPage.LoadFile(homeWrongCredentialsErrorFilePath);
		File adminWelcomeXSL = m_adminWelcomeXslPage.LoadFile(adminWelcomeFilePath);
		File userWelcomeXSL = m_userWelcomeXslPage.LoadFile(userWelcomeFilePath);
		File registerXSL = m_registerXslPage.LoadFile(registerFormFilePath);
		File adminAddProductXSL = m_adminAddProductXslPage.LoadFile(adminAddProductFormFilePath);
		File livingRoomProductsXSL = m_livingroomProductsXslPage.LoadFile(livingRoomProductsFilePath);
		File kitchenProductsXSL = m_kitchenProductsXslPage.LoadFile(kitchenProductsFilePath);
		File bedRoomProductsXSL = m_bedroomProductsXslPage.LoadFile(bedRoomProductsFilePath);
		File bathRoomProductsXSL = m_bathroomProductsXslPage.LoadFile(bathRoomProductsFilePath);
		File recreationalProductsXSL = m_recreationalProductsXslPage.LoadFile(recreationalProductsFilePath);
		File livingRoomDealsXSL = m_livingroomDealsXslPage.LoadFile(livingRoomDealsFilePath);
		File kitchenDealsXSL = m_kitchenDealsXslPage.LoadFile(kitchenDealsFilePath);
		File bedRoomDealsXSL = m_bedroomDealsXslPage.LoadFile(bedRoomDealsFilePath);
		File userAccountXSL = m_userAccountXslPage.LoadFile(userAccountFilePath);
		File userFundsXSL = m_userFundsXslPage.LoadFile(userFundsFilePath);
		File updateInfoXSL = m_updateInfoXslPage.LoadFile(updateInfoFilePath);
		File sellProductXSL = m_sellProductXslPage.LoadFile(sellProductFilePath);
		File editProductXSL = m_editProductXslPage.LoadFile(editProductFilePath);
		File shoppingCartXSL = m_shoppingCartXslPage.LoadFile(shoppingCartFilePath);
		File shoppingCartErrorXSL = m_shoppingCartErrorXslPage.LoadFile(shoppingCartErrorFilePath);
		File afterPurchaseXSL = m_afterPurchaseXslPage.LoadFile(afterPurchaseFilePath);
		
		//create thread pool for multithreading
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		
		//session for user
		HttpSession session = a_request.getSession();

		if(session.isNew())
		{
			//set time until session expires
			session.setMaxInactiveInterval(1200);
		}
		
		//page to return to user based on a_request
		PageResponse pageResponse = new PageResponse();
		
		//load all existing person data into PersonModel class list
		LoadPersonXML loadPrsn = new LoadPersonXML();
		loadPrsn.ReadXML(personFilePath);
		
		//load all existing product data into ProductModel class list
		LoadProductXML loadPdt = new LoadProductXML();
		loadPdt.ReadXML(productFilePath);
				
		//get path to requested controller
		String url = a_request.getRequestURL().toString();
		String contextPath = a_request.getContextPath();
		String [] splitResult = url.split(contextPath);
		String path = splitResult[1];
		
		//Open home page
		if(path.equals("/home"))
		{	
			try 
			{
				pageResponse.Respond(a_response, defaultXML, homeXSL);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
		}
		
		//Send to proper controller
		try 
		{
			//check username/password are valid
			//yes - send user to welcome page
			//no - reload home page
			if(path.equals("/verifylogin"))
			{		
				boolean loginFlag = VerifyLoginController.VerifyLogin(a_request);

				if(loginFlag == true)
				{
					//determine if user is an administrator or regular user
					//and set session variables
					boolean adminFlag = AdminOrUserController.AdminOrUser(a_request, session);
					
					if(adminFlag == true)
					{
						//check if admin is already logged-in
						boolean isLoggedIn = VerifyLoginController.IsDoubleLogin(m_currentSessions, session);
						
						if(isLoggedIn == true)
						{
							//admin is logged in already, return to home page
							pageResponse.Respond(a_response, defaultXML, homeDoubleLoginErrorXSL);
						}
						else
						{
							//add admin/session to hashmap
							CreateSession((Integer) session.getAttribute("AdminID"));
							
							//create xml file with admin username and
							//open administrator-welcome page
							GetFundsController gfunds = new GetFundsController(session);
							Future <Document> future = threadExecutor.submit(gfunds);	
							Document personFundsXML = future.get();
							
							pageResponse.Respond(a_response, personFundsXML, adminWelcomeXSL);
						}
					}
					else
					{
						//check if user is already logged-in
						boolean isLoggedIn = VerifyLoginController.IsDoubleLogin(m_currentSessions, session);
						
						if(isLoggedIn == true)
						{
							//user is logged in already, return to home page
							pageResponse.Respond(a_response, defaultXML, homeDoubleLoginErrorXSL);
						}
						else
						{	
							//add user/session to hashmap
							CreateSession((Integer) session.getAttribute("UserID"));
							
							//get the user's fund total in xml file and display to them
							GetFundsController gfunds = new GetFundsController(session);
							Future <Document> future = threadExecutor.submit(gfunds);	
							Document personFundsXML = future.get();
							
							//open user-welcome page
							pageResponse.Respond(a_response, personFundsXML, userWelcomeXSL);
						}
					}
				}
				else
				{
					//invalid credentials, return to home page
					pageResponse.Respond(a_response, defaultXML, homeWrongCredentialsXSL);
				}
			}
			else if(path.equals("/Register"))
			{
				//open form page to register user info with the site
				pageResponse.Respond(a_response, defaultXML, registerXSL);
			}
			else if(path.equals("/de_register"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//remove person from object list and xml file
					int userID = (Integer) session.getAttribute("UserID");
					DeletePersonController dpsnctlr = new DeletePersonController(userID);
					Future <?> future1 = threadExecutor.submit(dpsnctlr);	
					future1.get();
					
					//delete all the products submitted by the user
					DeleteUserProductController dupdt = new DeleteUserProductController(userID);
					Future <?> future2 = threadExecutor.submit(dupdt);	
					future2.get();
					
					//end session
					DeleteSession((Integer) session.getAttribute("UserID"));
					
					session.invalidate();
					
					//return to home page
					pageResponse.Respond(a_response, defaultXML, homeXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/log_out"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//end session and return to homepage
					if((Integer) session.getAttribute("UserID") != null)
					{
						DeleteSession((Integer) session.getAttribute("UserID"));
						
						session.invalidate();
						
						pageResponse.Respond(a_response, defaultXML, homeXSL);
					}
					else if((Integer) session.getAttribute("AdminID") != null)
					{
						DeleteSession((Integer) session.getAttribute("AdminID"));
						
						session.invalidate();
						
						pageResponse.Respond(a_response, defaultXML, homeXSL);
					}
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/addperson"))
			{
				//A new person has registered, so create new person and assign unique ID value to them
				PersonModel person = new PersonModel();
				person.SetID();
				
				//create object for class. thread will run it if validated
				AddPersonController psnctlr1 = new AddPersonController(a_request, person);
				
				boolean success = psnctlr1.ValidatePerson();
				
				if(success == true)
				{
					//store person data in application and return to homepage
					Future <?> future = threadExecutor.submit(psnctlr1);	
					future.get();
					
					pageResponse.Respond(a_response, defaultXML, homeXSL);
				}
				else
				{
					//validation error, return to form page
					pageResponse.Respond(a_response, defaultXML, registerXSL);
				}
			}
			else if(path.equals("/editperson"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//create object for class. thread will run it if validated
					EditPersonController psnctlr1 = new EditPersonController(a_request, session);
					
					boolean success = psnctlr1.ValidatePerson();
					
					if(success == true)
					{
						//edit user's information in person object and xml page
						EditPersonController epsnctlr = new EditPersonController(a_request, session);
						Future <?> future1 = threadExecutor.submit(epsnctlr);	
						future1.get();
						
						//get the user's fund total in xml file and display to them
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future2 = threadExecutor.submit(gfunds);	
						Document personFundsXML = future2.get();
						
						pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
					}
					else
					{
						//validation error, return to form page
						PopulateEditRegController eregctlr = new PopulateEditRegController(session);
						Future <Document> future = threadExecutor.submit(eregctlr);	
						Document personInfoXML = future.get();
						
						pageResponse.Respond(a_response, personInfoXML, updateInfoXSL);
					}
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/living_room_products"))
			{
				//open living-room products page
				pageResponse.Respond(a_response, productXML, livingRoomProductsXSL);
			}
			else if(path.equals("/kitchen_products"))
			{
				//open kitchen products page
				pageResponse.Respond(a_response, productXML, kitchenProductsXSL);
			}
			else if(path.equals("/bedroom_products"))
			{
				//open bedroom products page
				pageResponse.Respond(a_response, productXML, bedRoomProductsXSL);
			}
			else if(path.equals("/bathroom_products"))
			{
				//open bathroom products page
				pageResponse.Respond(a_response, productXML, bathRoomProductsXSL);
			}
			else if(path.equals("/recreational_products"))
			{
				//open recreational products page
				pageResponse.Respond(a_response, productXML, recreationalProductsXSL);
			}
			else if(path.equals("/livingroom_deals"))
			{
				//open living-room deals page
				pageResponse.Respond(a_response, productXML, livingRoomDealsXSL);
			}
			else if(path.equals("/kitchen_deals"))
			{
				//open kitchen deals page
				pageResponse.Respond(a_response, productXML, kitchenDealsXSL);
			}
			else if(path.equals("/bedroom_deals"))
			{
				//open bedroom deals page
				pageResponse.Respond(a_response, productXML, bedRoomDealsXSL);
			}
			else if(path.equals("/user_account"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//open the user's account page
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future = threadExecutor.submit(gfunds);	
					Document personFundsXML = future.get();
					
					pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/add_funds"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//add the fund total in the request to the user object and xml file
					AddFundsController efunds = new AddFundsController(a_request, session);
					Future <?> future1 = threadExecutor.submit(efunds);
					future1.get();
					
					//get the user's fund total in xml file and display to them
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future2 = threadExecutor.submit(gfunds);	
					Document personFundsXML = future2.get();
					
					//return to account page
					pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/display_funds"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//get the user's fund total in xml file and display to them
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future = threadExecutor.submit(gfunds);	
					Document personFundsXML = future.get();
					
					pageResponse.Respond(a_response, personFundsXML, userFundsXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/edit_info"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//create new xml document with user's info
					PopulateEditRegController eregctlr = new PopulateEditRegController(session);
					Future <Document> future = threadExecutor.submit(eregctlr);	
					Document personInfoXML = future.get();
					
					//open form page with user's previous info populated
					pageResponse.Respond(a_response, personInfoXML, updateInfoXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/admin_add_product"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsAdminLoggedIn(session);
				
				if(loginFlag == true)
				{
					//open form page for admin to submit product info 
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future = threadExecutor.submit(gfunds);	
					Document personFundsXML = future.get();
					
					pageResponse.Respond(a_response, personFundsXML, adminAddProductXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/sell_product"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//open form page for user to submit product info 
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future = threadExecutor.submit(gfunds);	
					Document personFundsXML = future.get();
					
					pageResponse.Respond(a_response, personFundsXML, sellProductXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/admin_sell_submit"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsAdminLoggedIn(session);
				
				if(loginFlag == true)
				{
					//get user's product info and store in object and xml page 
					ProductModel product = new ProductModel();
					AddProductController addpdt = new AddProductController(session, a_request, product);
					Future <?> future1 = threadExecutor.submit(addpdt);	
					future1.get();
					
					//get the user's fund total in xml file and display to them
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future2 = threadExecutor.submit(gfunds);	
					Document personFundsXML = future2.get();
					
					pageResponse.Respond(a_response, personFundsXML, adminWelcomeXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/sell_submit"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//get user's product info and store in object and xml page 
					ProductModel product = new ProductModel();
					AddProductController addpdt = new AddProductController(session, a_request, product);
					Future <?> future1 = threadExecutor.submit(addpdt);	
					future1.get();
					
					//get the user's fund total in xml file and display to them
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future2 = threadExecutor.submit(gfunds);	
					Document personFundsXML = future2.get();
					
					pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
					
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/edit_product"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsAdminLoggedIn(session);
				
				if(loginFlag == true)
				{
					//create new xml document with product's info
					PopulateEditProductController editpdtctlr = new PopulateEditProductController(a_request, session);
					Future <Document> future2 = threadExecutor.submit(editpdtctlr);	
					Document productInfoXML = future2.get();
					
					if(productInfoXML == null)
					{
						//return to admin home page
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future = threadExecutor.submit(gfunds);	
						Document personFundsXML = future.get();
						
						pageResponse.Respond(a_response, personFundsXML, adminWelcomeXSL);
					}
					else
					{
						//open form page with product info populated
						pageResponse.Respond(a_response, productInfoXML, editProductXSL);
					}
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/edit_submit"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsAdminLoggedIn(session);
				
				if(loginFlag == true)
				{
					//update product info in object and xml file
					EditProductController epdt = new EditProductController(a_request);
					Future <?> future = threadExecutor.submit(epdt);	
					future.get();
					
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future2 = threadExecutor.submit(gfunds);	
					Document personFundsXML = future2.get();
					
					pageResponse.Respond(a_response, personFundsXML, adminWelcomeXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/delete_product"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsAdminLoggedIn(session);
				
				if(loginFlag == true)
				{
					//delete product object and delete product from xml file
					DeleteProductController dpdt = new DeleteProductController(a_request);
					Future <?> future = threadExecutor.submit(dpdt);	
					future.get();
					
					GetFundsController gfunds = new GetFundsController(session);
					Future <Document> future2 = threadExecutor.submit(gfunds);	
					Document personFundsXML = future2.get();
					
					pageResponse.Respond(a_response, personFundsXML, adminWelcomeXSL);
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/cart_page"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);

				if(loginFlag == true)
				{
					//get shopping cart page
					GetCartController cart = new GetCartController(session);
					Future <Document> future = threadExecutor.submit(cart);	
					Document productInfoXML = future.get();
					
					if(productInfoXML != null)
					{
						pageResponse.Respond(a_response, productInfoXML, shoppingCartXSL);
					}
					else
					{
						//cart is empty
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future2 = threadExecutor.submit(gfunds);	
						Document personFundsXML = future2.get();
						
						pageResponse.Respond(a_response, personFundsXML, shoppingCartErrorXSL);
					}
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}		
			}
			else if(path.equals("/buy_product"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);

				if(loginFlag == true)
				{
					//make sure user entered a valid number
					String buyButton = a_request.getParameter("buyButton");
					String [] buttonText = buyButton.split(" ");
					int idValue = Integer.parseInt(buttonText[3]);
					
					try
					{
						Integer.parseInt(a_request.getParameter("quantityRequested" + idValue));
					}
					catch(Exception e)
					{
						e.printStackTrace();
						
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future = threadExecutor.submit(gfunds);	
						Document personFundsXML = future.get();
						
						pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
					}
					
					//find out if there is enough of the product requested
					boolean quantityFlag = BuyProductController.IsQuantityEnough(a_request);
					
					if(quantityFlag == false)
					{
						//not enough product available, so return to account page
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future = threadExecutor.submit(gfunds);	
						Document personFundsXML = future.get();
						
						pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
					}
					else
					{
						//find out if there is enough funds in user's account
						boolean fundsFlag = BuyProductController.IsFundsEnough(a_request, session);
						
						if(fundsFlag == false)
						{
							//not enough funds, so return to account page
							GetFundsController gfunds = new GetFundsController(session);
							Future <Document> future = threadExecutor.submit(gfunds);	
							Document personFundsXML = future.get();
							
							pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
						}
						else
						{
							//reduce quantity of the bought product in product object and xml files
							ReduceProductQuantityController rpdtctlr = new ReduceProductQuantityController(a_request);
							Future <Integer> future1 = threadExecutor.submit(rpdtctlr);	
							int productID = future1.get();
					
							//reduce user's funds
							ReduceFundsController rfunds = new ReduceFundsController(productID, a_request, session);
							Future <Integer> future2 = threadExecutor.submit(rfunds);	
							int personID = future2.get();
						
							//create shopping cart page
							ShoppingCartController cart = new ShoppingCartController(a_request, personID, productID);
							Future <Document> future = threadExecutor.submit(cart);	
							Document productInfoXML = future.get();
							
							pageResponse.Respond(a_response, productInfoXML, shoppingCartXSL);
						}
					}
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else if(path.equals("/commit_to_buy"))
			{
				//check if user is logged in first
				boolean loginFlag = VerifyLoginController.IsLoggedIn(session);
				
				if(loginFlag == true)
				{
					//find out if the buy button or cancel button was clicked
					String buyButton = a_request.getParameter("buyButton");
					String cancelButton = a_request.getParameter("cancelButton");
					
					if(buyButton != null)
					{
						//user bought the products, so email the info
						//to the user's provided email address
						PersonModel person = new PersonModel();
						int userID = (Integer) session.getAttribute("UserID");
						person = person.GetPerson(userID);
						
						String relativePathToXML = System.getProperty("catalina.base");
						relativePathToXML += "\\wtpwebapps\\Final\\XML\\Cart" + userID + ".xml";
						
						GetProductsFromCartController pdts = new GetProductsFromCartController(relativePathToXML);
						Future <ArrayList <ProductModel>> future1 = threadExecutor.submit(pdts);	
						ArrayList <ProductModel> products = future1.get();
						
						//delete the user's shopping cart xml file
						DeleteProductController.DeleteShoppingCart(userID);
						
						//get the user's fund total in xml file and display to them
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future4 = threadExecutor.submit(gfunds);	
						Document personFundsXML = future4.get();
						
						//email users that products belong to and update 
						//their funds from the purchase
						SendProductOwnerEmailController sendpdtowner = new SendProductOwnerEmailController(userID, products);
						Future <?> future2 = threadExecutor.submit(sendpdtowner);	
						future2.get();
						
						//email user that bought products
						SendDetailsEmailController sendemail = new SendDetailsEmailController(person, products);
						Future <?> future3 = threadExecutor.submit(sendemail);	
						future3.get();
						
						//display thank you page with updated funds
						pageResponse.Respond(a_response, personFundsXML, afterPurchaseXSL);
					}
					else if(cancelButton != null)
					{
						//user cancelled their order, so reset the product quantity
						//and user funds to their previous values
						int userID = (Integer) session.getAttribute("UserID");
	
						String relativePathToXML = System.getProperty("catalina.base");
						relativePathToXML += "\\wtpwebapps\\Final\\XML\\Cart" + userID + ".xml";
						
						LoadShoppingCartXML loadCart = new LoadShoppingCartXML();
						loadCart.ReadXML(relativePathToXML);
						
						//delete the user's shopping cart xml file
						DeleteProductController.DeleteShoppingCart(userID);
						
						//get the user's fund total in xml file and display to them
						GetFundsController gfunds = new GetFundsController(session);
						Future <Document> future = threadExecutor.submit(gfunds);	
						Document personFundsXML = future.get();
						
						//return to account page
						pageResponse.Respond(a_response, personFundsXML, userAccountXSL);
					}
				}
				else
				{
					//user is not logged in, so open home page
					pageResponse.Respond(a_response, defaultXML, homeNotLoggedInErrorXSL);
				}
			}
			else
			{
				System.out.println("No controller found for request.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * DispatcherServlet::doGet()
	 * 
	 * NAME:
	 * 
	 * 		DispatcherServlet::doGet() - sends GET http requests to doPost().
	 * 
	 * SYNOPSIS:
	 * 
	 * 		void doGet(HttpServletRequest a_request, HttpServletResponse a_response)
	 * 				throws ServletException, IOException
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * 			@param  a_response  --> response object, to be used when returning page
	 * 									to the user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Sends GET http requests to doPost().
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
	 * 		1/30/2013
	 * 
	 */
	protected void doGet(HttpServletRequest a_request, HttpServletResponse a_response) throws ServletException, IOException 
	{
		doPost(a_request, a_response);
	}
}

