/*****************************************************************
 * AddPersonController.java - checks that information user       *
 * 		submitted in registraion form is valid. If yes, add      *
 * 		person's data to application.                            *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;

import model.*;

public class AddPersonController implements Callable <Document>
{
	private HttpServletRequest m_request;
	private PersonModel m_person;
	
	/**
	 * AddPersonController::AddPersonController()
	 * 
	 * NAME:
	 * 
	 * 		AddPersonController::AddPersonController() - constructor to store http request
	 * 						and person objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		AddPersonController(HttpServletRequest a_request, PersonModel a_person)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * 			@param  a_person  --> person object, holding data that will be 
	 *                                associated with new person.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store http request and person objects for use with 
	 *      ValidatePerson() and call() functions.
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
	 * 		2/9/2013
	 * 
	 */
	public AddPersonController(HttpServletRequest a_request, PersonModel a_person)
	{
		this.m_request = a_request;
		this.m_person = a_person;
	}
	
	/**
	 * AddPersonController::ValidatePerson()
	 * 
	 * NAME:
	 * 
	 * 		AddPersonController::ValidatePerson() - determine if information user 
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
	 * 		2/9/2013
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
						uniqueUserName = false;
						break;
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
	 * AddFundsController::call()
	 * 
	 * NAME:
	 * 
	 * 		AddFundsController::call() - add registered person to application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Add new registered person to new person object in application
	 *      and XML file.
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
	 * 		2/9/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//get data from request and store in PersonModel ArrayList
		m_person.RegisterPerson(m_request, m_person);

		//store person data in xml file
		try 
		{
			AddPersonXML addpsn = new AddPersonXML();
			addpsn.AddPerson(m_person);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
