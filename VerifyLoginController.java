/*****************************************************************
 * VerifyLoginController.java - checks if username/password pair *
 * 		entered by user is valid, check if user is logged-in and *
 * 		check if user attempted to login when they were already  *
 * 		logged-in in another page.                               *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.*;

public class VerifyLoginController
{
	/**
	 * VerifyLoginController::VerifyLogin()
	 * 
	 * NAME:
	 * 
	 * 		VerifyLoginController::VerifyLogin() - ensure username/password pair is
	 * 						valid for the user attempting to login.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean VerifyLogin(HttpServletRequest a_request)
	 * 
	 * 			@param  a_request  --> request object, with data sent by user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Ensure username/password pair is valid for the user attempting to login.
	 *      Return true if yes or false for no.
	 * 
	 * RETURNS:
	 * 
	 * 		true if username/password pair is valid. false if not.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		2/2/2013
	 * 
	 */
	public static boolean VerifyLogin(HttpServletRequest a_request)
	{
		PersonModel person = new PersonModel();
		
		//get list of all users that exist for application
		ArrayList <PersonModel> persons = person.GetPersons();

		//get user-entered information
		String userName = a_request.getParameter("userName");
		String password = a_request.getParameter("password");
		
		//check that user-entered information matches a person object in list
		for(PersonModel pm : persons)
		{
			if(pm.GetUserName().equals(userName) && pm.GetPassword().equals(password))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * VerifyLoginController::IsLoggedIn()
	 * 
	 * NAME:
	 * 
	 * 		VerifyLoginController::IsLoggedIn() - check if user is logged into the
	 * 							application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean IsLoggedIn(HttpSession a_session)
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Check if user is logged into the application. Return true if so or false
	 * 		if not.
	 * 
	 * RETURNS:
	 * 
	 * 		true if user is logged in, false if not.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		3/18/2013
	 * 
	 */
	public static boolean IsLoggedIn(HttpSession a_session)
	{
		if(a_session.getAttribute("UserID") == null)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * VerifyLoginController::IsAdminLoggedIn()
	 * 
	 * NAME:
	 * 
	 * 		VerifyLoginController::IsAdminLoggedIn() - check if admin is logged into the
	 * 							application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean IsAdminLoggedIn(HttpSession a_session)
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Check if admin is logged into the application. Return true if so or false
	 * 		if not.
	 * 
	 * RETURNS:
	 * 
	 * 		true if admin is logged in, false if not.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		5/5/2013
	 * 
	 */
	public static boolean IsAdminLoggedIn(HttpSession a_session)
	{
		if(a_session.getAttribute("AdminID") == null)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * VerifyLoginController::IsDoubleLogin()
	 * 
	 * NAME:
	 * 
	 * 		VerifyLoginController::IsDoubleLogin() - check if user is attempting to
	 * 						login a second time.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean IsDoubleLogin(HashMap<Integer, String> a_currentSessions, HttpSession a_session)
	 * 
	 * 			@param  a_currentSessions  --> holds all active sessions for application.
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Check if user is attempting to login a second time. This prevents multiple
	 *      logins from different browser tabs.
	 * 
	 * RETURNS:
	 * 
	 * 		true if user is logged in already, false if not.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		3/23/2013
	 * 
	 */
	public static boolean IsDoubleLogin(ArrayList <Integer> a_currentSessions, HttpSession a_session)
	{
		for(int i = 0; i < a_currentSessions.size(); i++)
		{
			int userID = (Integer) a_session.getAttribute("UserID");
			int sessionID = a_currentSessions.get(i);
			
			if(userID == sessionID)
			{
				return true;
			}
		}
		return false;
	}
}
