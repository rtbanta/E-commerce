/*****************************************************************
 * SendProductOwnerEmailController.java - sends owner of product *
 * 		that was bought an email informing them of who bought    *
 * 		the product, the quantity bought, and how many funds     *
 * 		were added to their account from the purchase.           *
 *****************************************************************/
package controller;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Callable;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jdom.Document;

import model.EditFundsXML;
import model.PersonModel;
import model.ProductModel;

public class SendProductOwnerEmailController implements Callable <Document>
{
	private int m_buyingUserID; 
	private ArrayList <ProductModel> m_products;
	
	/**
	 * SendProductOwnerEmailController::SendProductOwnerEmailController()
	 * 
	 * NAME:
	 * 
	 * 		SendProductOwnerEmailController::SendProductOwnerEmailController() - constructor to store 
	 * 						person ID and arraylist of product objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		SendProductOwnerEmailController(PersonModel a_person, ArrayList <ProductModel> a_products)
	 * 
	 * 			@param  a_buyingUserID  --> person ID of user that bought product.
	 *                
	 *          @param  a_products  --> arraylist of products the person bought.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store person ID and arraylist of product objects
	 *      for use with call() function.
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
	 * 		3/23/2013
	 * 
	 */
	public SendProductOwnerEmailController(int a_buyingUserID, ArrayList <ProductModel> a_products)
	{
		this.m_buyingUserID = a_buyingUserID;
		this.m_products = a_products;
	}

	/**
	 * SendProductOwnerEmailController::call()
	 * 
	 * NAME:
	 * 
	 * 		SendProductOwnerEmailController::call() - send email to owner of recently
	 * 						purchased product informing them of their updated balance.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Send email to owner of recently purchased product informing them of their 
	 *      updated balance, the product bought, the quantity bought and the username
	 *      of the person who bought it.
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
	 * 		3/23/2013
	 * 
	 */
	@Override
	public Document call() throws Exception 
	{
		PersonModel person = new PersonModel();
    	ArrayList <PersonModel> persons = person.GetPersons();
	   
    	//get username of person who bought the products
    	PersonModel buyingPerson = new PersonModel();
    	buyingPerson = buyingPerson.GetPerson(m_buyingUserID);
    	String buyerUserName = buyingPerson.GetUserName();
	   
    	//find the owners of the bought products,
    	//email each of them about the purchase,
    	//and update their funds
    	for(ProductModel pdt : m_products)
    	{
    		int userID = pdt.GetUserID();
		   
    		for(PersonModel psn : persons)
    		{
    			if(userID == psn.GetID())
    			{
    				double fundsToAdd = pdt.GetPrice();
    				psn.AddToBalance(fundsToAdd);
    				psn.SetPersons(persons);
    				
    				EditFundsXML efunds = new EditFundsXML();
					efunds.AddBoughtProductBalanceToFunds(psn);
				   
    				String to = psn.GetEmail();
    				String from = "roberttbanta@gmail.com";
    			      
    		    	final String username = "roberttbanta@gmail.com";
    		    	final String password = "SilverLugia!$&*";
    		    	
    		    	// Get system properties
    		    	Properties properties = new Properties();

    		    	// Setup mail server
    		    	properties.put("mail.smtp.auth", "true");
    		    	properties.put("mail.smtp.starttls.enable", "true");
    		    	properties.put("mail.smtp.host", "smtp.gmail.com");
    		    	properties.put("mail.smtp.port", "587");
    		      
    		    	// Get the default Session object.
    		    	Session session = Session.getInstance(properties, new javax.mail.Authenticator() 
    		    		{
    						protected PasswordAuthentication getPasswordAuthentication() 
    						{
    							return new PasswordAuthentication(username, password);
    						}
    		    		});

				   
    				try
    				{
    					MimeMessage message = new MimeMessage(session);
    					message.setFrom(new InternetAddress(from));
    					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    					message.setSubject("Your product has been bought!");
				      
    					String messageText = "Your product has been bought by: " + buyerUserName + ".\n";
    					messageText += "The quantity purchased was: " + pdt.GetQuantity() + ".\n";
    					messageText += "Your updated balance is: $" + psn.GetBalance() + ".\n\n\n";
    					messageText += "Regards,\n";
    					messageText += "Banta's Bargains Staff.";
    					message.setText(messageText);
				         
    					// Send message
    					Transport.send(message);
    					System.out.println("Sent message successfully.");
    				}
    				catch (Exception e) 
    				{
    					e.printStackTrace();
    				}
    			}
    		}
    	}
		return null;
	}
}
