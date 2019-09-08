/*****************************************************************
 * PopulateEditRegController.java - pre-populates the            *
 * 		re-registration form with the data for the person that   *
 *      is currently stored in the application.                  *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;
import model.EditPersonXML;
import org.jdom.Document;

public class PopulateEditRegController implements Callable <Document>
{
	private HttpSession m_session;
	
	/**
	 * PopulateEditRegController::PopulateEditRegController()
	 * 
	 * NAME:
	 * 
	 * 		PopulateEditRegController::PopulateEditRegController() - constructor to 
	 * 						store session object for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		PopulateEditRegController(HttpSession a_session)
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
	 * 		2/24/2013
	 * 
	 */
	public PopulateEditRegController(HttpSession a_session)
	{
		this.m_session = a_session;
	}

	/**
	 * PopulateEditRegController::call()
	 * 
	 * NAME:
	 * 
	 * 		PopulateEditRegController::call() - get user's current data to populate
	 * 					update-registration form.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Document call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Get user's current data from application. Use XML file 
	 *      to populate update-registration form page with data for better usability.
	 * 
	 * RETURNS:
	 * 
	 * 		XML Document holding current data of user.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		2/24/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//make new xml file to hold logged-in user's info temporarily
		EditPersonXML epsn = new EditPersonXML();
		Document doc = epsn.GetPerson(m_session);
		return doc;
	}
}
