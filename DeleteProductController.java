/*****************************************************************
 * DeleteProductController.java - removes product from           *
 * 		application.                                             * 
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;

import model.*;

public class DeleteProductController implements Callable <Document>
{
	private HttpServletRequest m_request;
	
	/**
	 * DeleteProductController::DeleteProductController()
	 * 
	 * NAME:
	 * 
	 * 		DeleteProductController::DeleteProductController() - constructor to store
	 *                    http request object for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		DeleteProductController(HttpServletRequest a_request)
	 * 
	 * 			@param  a_request  --> request object, with data sent by user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store http request object for use with call() function.
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
	 * 		2/11/2013
	 * 
	 */
	public DeleteProductController(HttpServletRequest a_request)
	{
		this.m_request = a_request;
	}
	
	/**
	 * DeleteProductController::DeleteShoppingCart()
	 * 
	 * NAME:
	 * 
	 * 		DeleteProductController::DeleteShoppingCart() - remove user's
	 * 						shopping cart from application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		void DeleteShoppingCart(int a_userID)
	 * 
	 * 			@param  a_userID  --> unique ID of user used to delete their
	 * 								  shopping cart XML page.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Remove user's shopping cart XML file from application.
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
	 * 		3/24/2013
	 * 
	 */
	public static void DeleteShoppingCart(int a_userID) 
	{       
		//get product ID and use it to delete product from ArrayList and xml file
		try
		{    
			DeleteProductXML.DeleteShoppingCartXML(a_userID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * DeleteProductController::call()
	 * 
	 * NAME:
	 * 
	 * 		DeleteProductController::call() - remove product from application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Remove product object from application and product data from XML file.
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
	 * 		2/11/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//get product ID and use it to delete product from ArrayList and xml file
		try
		{
			int ID = Integer.parseInt(m_request.getParameter("deleteProductID"));
			
			ProductModel product = new ProductModel();
		    product.DeleteProduct(ID);
		       
		    DeleteProductXML dpdt = new DeleteProductXML();
		    dpdt.DeleteProduct(ID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
