/*****************************************************************
 * EditProductController.java - checks that information admin    *
 * 		submitted in edit-product form is valid. If yes, update  *
 * 		product's data in application.                           *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletRequest;
import org.jdom.Document;
import model.*;

public class EditProductController implements Callable <Document>
{	
	private HttpServletRequest m_request;
	
	/**
	 * EditProductController::EditProductController()
	 * 
	 * NAME:
	 * 
	 * 		EditProductController::EditProductController() - constructor to store http 
	 * 						request object for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		EditProductController(HttpServletRequest a_request)
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
	public EditProductController(HttpServletRequest a_request)
	{
		this.m_request = a_request;
	}

	/**
	 * EditProductController::call()
	 * 
	 * NAME:
	 * 
	 * 		EditProductController::call() - edit the product data in object and XML file.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Update the product data in product object and XML file from the data the
	 *      administrator submitted in the form.
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
	@Override
	public Document call() throws Exception 
	{
		//edit product in ArrayList and xml file
		ProductModel product = new ProductModel();
		
		try
		{
			Integer.parseInt(m_request.getParameter("productQuantity"));
			Double.parseDouble(m_request.getParameter("productPrice"));
			
			product.EditProduct(m_request);
		
			EditProductXML epdt = new EditProductXML();
			epdt.EditProduct(m_request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
