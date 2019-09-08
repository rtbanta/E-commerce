/*****************************************************************
 * GetProductsFromCartController.java - gets the products from   *
 * 		the user's shopping cart.                                *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import model.LoadShoppingCartXML;
import model.ProductModel;

public class GetProductsFromCartController implements Callable <ArrayList <ProductModel>>
{
	private String m_xmlPath;
	
	/**
	 * GetProductsFromCartController::GetProductsFromCartController()
	 * 
	 * NAME:
	 * 
	 * 		GetProductsFromCartController::GetProductsFromCartController() - constructor
	 *                       to store path to xml file for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		GetProductsFromCartController(String a_xmlPath)
	 * 
	 * 			@param  a_xmlPath  --> String holding path to shopping cart xml file.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store path to xml file for use with class.
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
	 * 		4/1/2013
	 * 
	 */
	public GetProductsFromCartController(String a_xmlPath)
	{
		this.m_xmlPath = a_xmlPath;
	}

	/**
	 * GetProductsFromCartController::call()
	 * 
	 * NAME:
	 * 
	 * 		GetProductsFromCartController::call() - get all the user's products from their
	 * 						shopping cart.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		ArrayList <ProductModel> call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Get all the user's products they purchased from their shopping cart 
	 *      XML file and return them in an ArrayList.
	 * 
	 * RETURNS:
	 * 
	 * 		ArrayList holding the products (as objects) from the user's shopping cart.
	 * 
	 * AUTHOR:
	 * 
	 * 		Robert Banta
	 * 
	 * DATE:
	 * 
	 * 		4/1/2013
	 * 
	 */
	@Override
	public ArrayList <ProductModel> call() throws Exception 
	{
		LoadShoppingCartXML loadCart = new LoadShoppingCartXML();
		ArrayList <ProductModel> products = loadCart.LoadProductsXML(m_xmlPath);
		return products;
	}
}
