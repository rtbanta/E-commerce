/*****************************************************************
 * AddProductController.java - checks that information user or    *
 * 		admin submitted in add-product form is valid. If yes,    *
 *      add product's data to application.                       *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jdom.Document;

import model.*;

public class AddProductController implements Callable <Document>
{
	private HttpSession m_session;
	private HttpServletRequest m_request;
	private ProductModel m_product;
	
	/**
	 * AddProductController::AddProductController()
	 * 
	 * NAME:
	 * 
	 * 		AddProductController::AddProductController() - constructor to store session, 
	 *                      http request and product objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		AddProductController(HttpSession a_session, HttpServletRequest a_request, ProductModel a_product)
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * 			@param  a_request  --> request object, with data sent by user.
	 * 
	 * 			@param  a_product  --> product object, holding data that will be
	 *                                 associated with new product.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store  for use with session, http request and product objects
	 *      for use with call() function.
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
	 * 		2/16/2013
	 * 
	 */
	public AddProductController(HttpSession a_session, HttpServletRequest a_request, ProductModel a_product)
	{
		this.m_session = a_session;
		this.m_request = a_request;
		this.m_product = a_product;
	}

	/**
	 * AddProductController::call()
	 * 
	 * NAME:
	 * 
	 * 		AddProductController::call() - add product to application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Add new product to new product object and XML file. Add ID to it based on
	 *      if it's submitted by a user or an administrator.
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
	 * 		2/9/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//add product in ArrayList and xml file
		try
		{
			//find out if admin or user is adding product,
			//get data from request and store in ProductModel ArrayList
			if((Integer) m_session.getAttribute("AdminID") != null)
			{
				int ID = (Integer) m_session.getAttribute("AdminID");
				
				//check fields for valid numbers
				try
				{
					Integer.parseInt(m_request.getParameter("productQuantity"));
					Double.parseDouble(m_request.getParameter("productPrice"));
					
					m_product.AddProduct(ID, m_request, m_product);
					
					//store product data in xml file
					AddProductXML addpdt = new AddProductXML();
					addpdt.AddProduct(ID, m_product);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if((Integer) m_session.getAttribute("UserID") != null)
			{
				int ID = (Integer) m_session.getAttribute("UserID");
				
				//check fields for valid numbers
				try
				{
					Integer.parseInt(m_request.getParameter("productQuantity"));
					Double.parseDouble(m_request.getParameter("productPrice"));
					
					m_product.AddProduct(ID, m_request, m_product);
					
					//store product data in xml file
					AddProductXML addpdt = new AddProductXML();
					addpdt.AddProduct(ID, m_product);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
