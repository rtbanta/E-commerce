/*****************************************************************
 * DeleteUserProductController.java - removes products owned by  *
 * 		 person removed from application.                        * 
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import org.jdom.Document;

import model.DeleteProductXML;
import model.ProductModel;

public class DeleteUserProductController implements Callable <Document>
{
	private int m_userID;
	
	/**
	 * DeleteUserProductController::DeleteUserProductController()
	 * 
	 * NAME:
	 * 
	 * 		DeleteUserProductController::DeleteUserProductController() - constructor to store
	 *                    user's unique ID for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		DeleteUserProductController(int a_userID)
	 * 
	 * 			@param  a_userID  --> integer that is a unique ID assigned to the person.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store user's unique ID for use with call() function.
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
	 * 		3/30/2013
	 * 
	 */
	public DeleteUserProductController(int a_userID)
	{
		this.m_userID = a_userID;
	}

	/**
	 * DeleteUserProductController::call()
	 * 
	 * NAME:
	 * 
	 * 		DeleteUserProductController::call() - remove products from application
	 * 						after user is removed from application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Remove products from application associated with user after that 
	 *      user has been removed from the application.
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
	 * 		3/30/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//get user ID and use it to delete products from ArrayList and xml file
		try
		{	
			ProductModel product = new ProductModel();
		    product.DeleteProducts(m_userID);
		        
		    DeleteProductXML dpdt = new DeleteProductXML();
		    dpdt.DeleteProducts(m_userID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
