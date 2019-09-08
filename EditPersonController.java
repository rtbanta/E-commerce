/*****************************************************************
 * EditPersonController.java - checks that information user       *
 * 		submitted in re-registraion form is valid. If yes, update *
 * 		person's data in application.                             *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jdom.Document;

import model.*;

public class EditPersonController implements Callable <Document>
{
	private HttpServletRequest m_request;
	private HttpSession m_session;
	
	/**
	 * EditPersonController::EditPersonController()
	 * 
	 * NAME:
	 * 
	 * 		EditPersonController::EditPersonController() - constructor to store http request
	 * 						and session objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		EditPersonController(HttpServletRequest a_request, HttpSession a_session)
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
	 * 		2/25/2013
	 * 
	 */
	public EditPersonController(HttpServletRequest a_request, HttpSession a_session)
	{
		this.m_request = a_request;
		this.m_session = a_session;
	}

	/**
	 * EditPersonController::ValidatePerson()
	 * 
	 * NAME:
	 * 
	 * 		EditPersonController::ValidatePerson() - determine if information user 
	 *                     entered in registration form is valid.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		boolean ValidatePerson()
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Ensure information user entered in registration form is valid. Proceed 
	 *      to next page if so, or re-open form if not.
	 * 
	 * RETURNS:
	 * 
	 * 		Boolean value determining if user entered valid information (true)
	 *      or invalid information (false).
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		4/28/2013
	 * 
	 */
	public boolean ValidatePerson() 
	{     
		boolean validate = true;
		
		try
		{
			//validate email address, zipcode, and phone number
			String email = m_request.getParameter("email");
			
			String [] nameDomain = email.split("@");
			String domain = nameDomain[1];
			String [] domainPeriodSplit = domain.split("\\.");
			
			if(nameDomain.length == 2 && !nameDomain[0].isEmpty() && !nameDomain[1].isEmpty() && domainPeriodSplit[1].length() == 3 && nameDomain[1].contains("."))
			{	
				String phoneNumber = m_request.getParameter("phone");
				String phonePattern = "(\\d-)?\\d{3}-?\\d{3}-?\\d{4}";
				boolean phonePatternMatch = phoneNumber.matches(phonePattern);
				
				String zipcode = m_request.getParameter("zip");
				String zipPattern = "(\\d{5})";
				boolean zipPatternMatch = zipcode.matches(zipPattern);
				
				String password = m_request.getParameter("password");
				String passwordPattern = "^(?=(.*[a-z]){2,}?)(?=(.*[A-Z]){2,}?)(?=(.*[0-9]){2,}?)(?=(.*[!@#$%^&*?<>]){2,}?).*$";
				boolean passwordPatternMatch = password.matches(passwordPattern);
				
				//make sure password doesn't have spaces
				boolean noSpaces = !password.contains(" ");
				
				//find out if username already exists in application
				boolean uniqueUserName = true;
				String userName = m_request.getParameter("username");
				
				PersonModel person = new PersonModel();
				ArrayList <PersonModel> persons = person.GetPersons();
				
				for(PersonModel psn : persons)
				{
					if(psn.GetUserName().equals(userName))
					{
						//if the username of the person is the same as it
						//was before, continue without a problem
						if(psn.GetID() == (Integer) m_session.getAttribute("UserID"))
						{
							continue;
						}
						else
						{
							uniqueUserName = false;
							break;
						}
					}
				}
				
				if(phonePatternMatch == true && zipPatternMatch == true && passwordPatternMatch == true && noSpaces == true && uniqueUserName == true)
				{
					return validate;
				}
				else
				{
					validate = false;
				}
			}
			else
			{
				validate = false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			validate = false;
		}
		return validate;
	}
	
	/**
	 * EditPersonController::call()
	 * 
	 * NAME:
	 * 
	 * 		EditPersonController::call() - edit the user's data in object and XML file.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Update the user's data in person object and XML file from the data they
	 *      submitted in the form.
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
	 * 		2/25/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//edit person in ArrayList and xml file
		PersonModel person = new PersonModel();
		
		try
		{
			person.EditPerson(m_request, m_session);
		
			EditPersonXML epsn = new EditPersonXML();
			epsn.EditPerson(m_session);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
