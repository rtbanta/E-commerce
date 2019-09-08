/*****************************************************************
 * DeletePersonController.java - removes person from application. *                          *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import org.jdom.Document;

import model.DeletePersonXML;
import model.PersonModel;

public class DeletePersonController implements Callable <Document>
{
	private int m_userID;
	
	/**
	 * DeletePersonController::DeletePersonController()
	 * 
	 * NAME:
	 * 
	 * 		DeletePersonController::DeletePersonController() - constructor to store
	 *                    user's unique ID for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		DeletePersonController(int a_userID)
	 * 
	 * 			@param  a_userID  --> integer that is a unique ID assigned to the person.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store user's unique ID for use with call() function.
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
	 * 		2/11/2013
	 * 
	 */
	public DeletePersonController(int a_userID) 
	{       
		this.m_userID = a_userID;
	}

	/**
	 * DeletePersonController::call()
	 * 
	 * NAME:
	 * 
	 * 		DeletePersonController::call() - remove person from application.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Remove person object from application and person data from XML file.
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
	 * 		2/11/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//delete person from application
		try
		{
			PersonModel person = new PersonModel();
			person.DeletePerson(m_userID);
			
			DeletePersonXML dpsn = new DeletePersonXML();
			dpsn.DeletePerson(m_userID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
