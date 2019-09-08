/*****************************************************************
 * ReduceProductQuantityController.java - reduces the amount of  *
 * 		the product available by how much of it the user bought. *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import model.EditProductXML;
import model.ProductModel;

public class ReduceProductQuantityController implements Callable <Integer>
{
	private HttpServletRequest m_request;
	
	/**
	 * ReduceProductQuantityController::ReduceProductQuantityController()
	 * 
	 * NAME:
	 * 
	 * 		ReduceProductQuantityController::ReduceProductQuantityController() - constructor to 
	 * 						store http request object for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		ReduceProductQuantityController(HttpServletRequest a_request)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
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
	 * 		3/25/2013
	 * 
	 */
	public ReduceProductQuantityController(HttpServletRequest a_request)
	{
		this.m_request = a_request;
	}

	/**
	 * ReduceProductQuantityController::call()
	 * 
	 * NAME:
	 * 
	 * 		ReduceProductQuantityController::call() - reduce the quantity of the bought
	 * 						product by the number requested by the user.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Integer call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Reduce the quantity of the bought product by the number requested by the 
	 *      user in the product object and XML file. ReturnID of affected product.
	 * 
	 * RETURNS:
	 * 
	 * 		ID of product bought by user.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		3/25/2013
	 * 
	 */
	@Override
	public Integer call() throws Exception 
	{
		//edit product in ArrayList and xml file
		ProductModel product = new ProductModel();
		
		try
		{
			int productID = product.ReduceProductQuantity(m_request);
			
			EditProductXML epdt = new EditProductXML();
			epdt.ReduceProductQuantityXML(m_request);
			
			return productID;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
