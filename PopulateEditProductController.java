/*****************************************************************
 * PopulateEditProductController.java - pre-populates the        *
 * 		edit-product form with the data for the product that is  *
 * 		currently stored in the application.                     *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EditProductXML;

import org.jdom.Document;

public class PopulateEditProductController implements Callable <Document>
{
	private HttpServletRequest m_request;
	private HttpSession m_session;
	
	/**
	 * ::PopulateEditProductController()
	 * 
	 * NAME:
	 * 
	 * 		PopulateEditProductController::PopulateEditProductController() - constructor to 
	 * 						store http request object for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		PopulateEditProductController(HttpServletRequest a_request)
	 * 
	 * 			@param  a_request   --> request object, with data sent by user.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store http request object for use with call() function.
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
	 * 		2/17/2013
	 * 
	 */
	public PopulateEditProductController(HttpServletRequest a_request, HttpSession a_session)
	{
		this.m_request = a_request;
		this.m_session = a_session;
	}

	/**
	 * PopulateEditProductController::call()
	 * 
	 * NAME:
	 * 
	 * 		PopulateEditProductController::call() - get data of product from application 
	 * 						and store in XML file to populate form.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Document call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Get data of product from application and store in XML file. Use XML file 
	 *      to populate form page with data for better usability.
	 * 
	 * RETURNS:
	 * 
	 * 		XML Document holding current data from requested product.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		2/17/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		//test if id is proper integer
		try
		{
			Integer.parseInt(m_request.getParameter("productID"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//make new xml file to hold product's info temporarily
		EditProductXML epdt = new EditProductXML();
		Document doc = epdt.GetProduct(m_request, m_session);
		return doc;
	}
}
