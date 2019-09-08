/*****************************************************************
 * BuyProductController.java - if user has enough funds and      * 
 *      there is enough product, allows user to purchase the     *
 *      product.                                                 *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.*;

public class BuyProductController
{
	/**
	 * BuyProductController::IsQuantityEnough()
	 * 
	 * NAME:
	 * 
	 * 		BuyProductController::IsQuantityEnough() - determine if person has enough
	 * 						funds to buy the product they're attempting to purchase.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean IsQuantityEnough(HttpServletRequest a_request)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Determine if person has enough funds to buy the product they're attempting 
	 *      to purchase. Return true if yes, false if not.
	 * 
	 * RETURNS:
	 * 
	 * 		boolean value - true if user has enough funds, false if not.
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
	public static boolean IsQuantityEnough(HttpServletRequest a_request)
	{
		ProductModel product = new ProductModel();
		
		ArrayList <ProductModel> productsList = product.GetProducts();
		
		String buyButton = a_request.getParameter("buyButton");
		String [] buttonText = buyButton.split(" ");
		int idValue = Integer.parseInt(buttonText[3]);	
		int quantityRequested = Integer.parseInt(a_request.getParameter("quantityRequested" + idValue));
		
		for(ProductModel pm : productsList)
		{
			if(pm.GetID() == idValue)
			{
				if(pm.GetQuantity() >= quantityRequested)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * BuyProductController::IsFundsEnough()
	 * 
	 * NAME:
	 * 
	 * 		BuyProductController::IsFundsEnough() - determine if person has enough
	 * 						funds to buy the products they're attempting to purchase.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean IsFundsEnough(HttpServletRequest a_request, HttpSession a_session)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Determine if person has enough funds to buy the products in their
	 *      shopping cart. Return true if yes, false if not.
	 * 
	 * RETURNS:
	 * 
	 * 		boolean value - true if user has enough funds, false if not.
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
	public static boolean IsFundsEnough(HttpServletRequest a_request, HttpSession a_session)
	{
		ProductModel product = new ProductModel();
		PersonModel person = new PersonModel();
		ArrayList <ProductModel> productsList = product.GetProducts();
		ArrayList <PersonModel> personsList = person.GetPersons();
		
		String buyButton = a_request.getParameter("buyButton");
		String [] buttonText = buyButton.split(" ");
		int idValue = Integer.parseInt(buttonText[3]);	
		int quantityRequested = Integer.parseInt(a_request.getParameter("quantityRequested" + idValue));
		
		for(ProductModel pm : productsList)
		{
			if(pm.GetID() == idValue)
			{
				for(PersonModel prsn : personsList)
				{
					if(prsn.GetID() == (Integer) a_session.getAttribute("UserID"))
					{
						double totalPrice = pm.GetPrice() * quantityRequested;
				
						if(totalPrice <= prsn.GetBalance())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
