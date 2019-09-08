/*****************************************************************
 * AddFundsController.java - adds funds to user's account sent   *
 * 		from form input. Must be <= $1,000 per request.          *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import model.*;

public class AddFundsController implements Callable <Document>
{
	private HttpServletRequest m_request;
	private HttpSession m_session;
	
	/**
	 * AddFundsController::AddFundsController()
	 * 
	 * NAME:
	 * 
	 * 		AddFundsController::AddFundsController() - constructor to store http request
	 * 						and session objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		AddFundsController(HttpServletRequest a_request, HttpSession a_session)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store http request and session objects for use with 
	 *      call() function.
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
	public AddFundsController(HttpServletRequest a_request, HttpSession a_session)
	{
		this.m_request = a_request;
		this.m_session = a_session;
	}

	/**
	 * AddFundsController::call()
	 * 
	 * NAME:
	 * 
	 * 		AddFundsController::call() - add funds to user's account.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Add funds to user's object and XML file.
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
	@Override
	public Document call() throws Exception 
	{
		//add funds to person object and xml file
		PersonModel person = new PersonModel();
		
		try
		{
			double total = Double.parseDouble(m_request.getParameter("fundsVal"));
			
			if(total > 0 && total <= 1000)
			{
				person.AddFunds(total, m_session);
				
				EditFundsXML efunds = new EditFundsXML();
				efunds.AddFunds(m_session);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
