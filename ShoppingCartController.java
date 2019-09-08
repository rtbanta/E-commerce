/*****************************************************************
 * ShoppingCartController.java - updates the user's shopping cart *
 * 		with the product purchased.                               *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import org.jdom.Document;
import model.*;

public class ShoppingCartController implements Callable <Document>
{
	private HttpServletRequest m_request; 
	private int m_personID; 
	private int m_productID;
	
	/**
	 * ShoppingCartController::ShoppingCartController()
	 * 
	 * NAME:
	 * 
	 * 		ShoppingCartController::ShoppingCartController() - constructor to store http request,
	 * 						person ID and product ID for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		ShoppingCartController(HttpServletRequest a_request, int a_personID, int a_productID)
	 *                
	 *          @param  a_request  --> request object, with data sent by user.
	 *          
	 * 			@param  a_personID  --> ID of person belonging to shopping cart.
	 * 
	 * 			@param  a_productID  --> ID of product bought by person.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store http request, person ID and product ID
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
	 * 		3/16/2013
	 * 
	 */
	public ShoppingCartController(HttpServletRequest a_request, int a_personID, int a_productID)
	{
		this.m_request = a_request;
		this.m_personID = a_personID;
		this.m_productID = a_productID;
	}

	/**
	 * ShoppingCartController::call()
	 * 
	 * NAME:
	 * 
	 * 		ShoppingCartController::call() - load shopping cart with quantity of
	 * 						product bought by user.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Document call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Load shopping cart with quantity and price of product bought by user. 
	 *      Create new cart if user doesn't have one, or load existing one. Return 
	 *      the cart as an XML file to display in webpage.
	 * 
	 * RETURNS:
	 * 
	 * 		XML file holding user's bought products.
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
	@Override
	public Document call() throws Exception 
	{
        int quantityRequested = Integer.parseInt(m_request.getParameter("quantityRequested" + m_productID));
		
		PersonModel person = new PersonModel();
		ProductModel product = new ProductModel();
		person = person.GetPerson(m_personID);
		product = product.GetProduct(m_productID);
		
		//make new xml file to hold user's purchase history
		Document doc = null;
		try
		{
			LoadCart cart = new LoadCart();
			doc = cart.CreateCart(person, product, quantityRequested);
			return doc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
