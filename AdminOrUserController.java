/*****************************************************************
 * AdminOrUserController.java - finds out if person is           *
 *      logging-in as a user or administrator.                   *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.PersonModel;

public class AdminOrUserController
{
	/**
	 * AdminOrUserController::AdminOrUser()
	 * 
	 * NAME:
	 * 
	 * 		AdminOrUserController::AdminOrUser() - determine if person logging in
	 * 						is a user or an administrator.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean AdminOrUser(HttpServletRequest a_request, HttpSession a_session)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * 			@param  a_session  --> session object holding ID of the 
	 * 									current session.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Determine if person attempting to log in is a user or an administrator.
	 *      Return true if admin or false if user.
	 * 
	 * RETURNS:
	 * 
	 * 		boolean value - true if admin or false if user.
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
	public static boolean AdminOrUser(HttpServletRequest a_request, HttpSession a_session)
	{
		PersonModel person = new PersonModel();
		
		//get list of all users that exist for application
		ArrayList <PersonModel> persons = person.GetPersons();

		//get user-entered information
		String userName = a_request.getParameter("userName");
		
		//check that user-entered information matches a person object in list
		for(PersonModel pm : persons)
		{
			if(pm.GetUserName().equals(userName) && (pm.GetID() == 1 || pm.GetID() == 2 || pm.GetID() == 3 || pm.GetID() == 4))
			{
				//set the admin's ID value in the session
				a_session.setAttribute("AdminID", pm.GetID());
				
				return true;
			}
			else if(pm.GetUserName().equals(userName) && (pm.GetID() >= 5))
			{
				//set the user's ID value in the session
				a_session.setAttribute("UserID", pm.GetID());

				return false;
			}
		}
		return false;
	}
}
