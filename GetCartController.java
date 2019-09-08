/*****************************************************************
 * GetCartController.java - get XML file holding user's          *
 * 		bought products.                                         *
 *****************************************************************/
package controller;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpSession;
import model.LoadCart;
import org.jdom.Document;

public class GetCartController implements Callable <Document>
{	
	private HttpSession m_session;
	
	/**
	 * GetCartController::GetCartController()
	 * 
	 * NAME:
	 * 
	 * 		GetCartController::GetCartController() - constructor to store session
	 * 						 for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		GetCartController(HttpSession a_session)
	 *                
	 *          @param  a_session  --> session object holding ID of the 
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
	 * 		5/4/2013
	 * 
	 */
	public GetCartController(HttpSession a_session)
	{
		this.m_session = a_session;
	}
	
	/**
	 * GetCartController::call()
	 * 
	 * NAME:
	 * 
	 * 		GetCartController::call() - returns xml file with products bought by user.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Document call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Returns xml file with products bought by user.
	 * 
	 * RETURNS:
	 * 
	 * 		XML file holding user's bought products.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		5/4/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{	
		//get xml file holding user's purchase history
		Document doc = null;
		try
		{
			LoadCart cart = new LoadCart();
			doc = cart.GetCart(m_session);
			return doc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
