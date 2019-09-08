/*****************************************************************
 * GetFundsController.java - creates an XML file holding person's *
 * 		funds and display it to them in webpage.                  *          
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpSession;
import model.*;
import org.jdom.Document;

public class GetFundsController implements Callable <Document>
{
	private HttpSession m_session;
	
	/**
	 * GetFundsController::GetFundsController()
	 * 
	 * NAME:
	 * 
	 * 		GetFundsController::GetFundsController() - constructor to store session 
	 *                    object for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		GetFundsController(HttpSession a_session)
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store session object for use with call() function.
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
	 * 		3/10/2013
	 * 
	 */
	public GetFundsController(HttpSession a_session)
	{
		this.m_session = a_session;
	}

	/**
	 * GetFundsController::call()
	 * 
	 * NAME:
	 * 
	 * 		GetFundsController::call() - get the user's current account balance.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Document call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Get the user's available funds. Return in an XML file to display to them
	 *      in the webpage.
	 * 
	 * RETURNS:
	 * 
	 * 		XML Document holding the user's current funds available in their account.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		3/10/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//make new xml file to hold logged-in user's balance temporarily
		EditFundsXML efunds = new EditFundsXML();
		Document doc = efunds.GetBalance(m_session);
		return doc;
	}
}
