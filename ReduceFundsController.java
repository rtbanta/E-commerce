/*****************************************************************
 * ReduceFundsController.java - reduces the total funds for      *
 * 		the user based on the product bought.                    *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.EditFundsXML;
import model.PersonModel;

public class ReduceFundsController implements Callable <Integer>
{
	private int m_productID;
	private HttpServletRequest m_request;
	private HttpSession m_session;
	
	/**
	 * ReduceFundsController::ReduceFundsController()
	 * 
	 * NAME:
	 * 
	 * 		ReduceFundsController::ReduceFundsController() - constructor to store product ID, 
	 *                      http request and session objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		ReduceFundsController(ProductModel a_productID, HttpServletRequest a_request, HttpSession a_session)
	 * 
	 * 			@param  a_productID  --> integer holding ID that will 
	 *                                   represent product.
	 *                
	 *          @param  a_request  --> request object, with data sent by user.
	 *          
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store product ID, http request and session objects
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
	public ReduceFundsController(int a_productID, HttpServletRequest a_request, HttpSession a_session)
	{
		this.m_productID = a_productID;
		this.m_request = a_request;
		this.m_session = a_session;
	}

	/**
	 * ReduceFundsController::call()
	 * 
	 * NAME:
	 * 
	 * 		ReduceFundsController::call() - reduce the person's funds equivalent to
	 * 					the price of the product(s) they purchased.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Integer call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Reduce the person's funds equivalent to the price of the product(s) 
	 *      they purchased. Set the new balance and return ID of person affected.
	 * 
	 * RETURNS:
	 * 
	 * 		ID of person whose funds were reduced.
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
	public Integer call() throws Exception 
	{
		//add funds to person object and xml file
		PersonModel person = new PersonModel();
				
		try
		{
			int personID = person.ReducePersonFunds(m_productID, m_request, m_session);
				
			EditFundsXML efunds = new EditFundsXML();
			efunds.AddFunds(m_session);
			
			return personID;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
