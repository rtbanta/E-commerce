/*****************************************************************
 * SendDetailsEmailController.java - sends user the details of   *
 *      the products they bought and their current funds         *
 *      available.                                               *
 *****************************************************************/
package controller;
import java.util.*;
import java.util.concurrent.Callable;

import javax.mail.*;
import javax.mail.internet.*;

import org.jdom.Document;

import model.*;

public class SendDetailsEmailController implements Callable <Document>
{
	private PersonModel m_person; 
	private ArrayList <ProductModel> m_products;
	
	/**
	 * SendDetailsEmailController::SendDetailsEmailController()
	 * 
	 * NAME:
	 * 
	 * 		SendDetailsEmailController::SendDetailsEmailController() - constructor to store 
	 * 						person object and arraylist of product objects for use with class.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		SendDetailsEmailController(PersonModel a_person, ArrayList <ProductModel> a_products)
	 * 
	 * 			@param  a_person  --> person object, holding data that will be 
	 *                                associated with person.
	 *                
	 *          @param  a_products  --> arraylist of products the person bought.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Constructor to store person object and arraylist of product objects
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
    public SendDetailsEmailController(PersonModel a_person, ArrayList <ProductModel> a_products)
    {
    	this.m_person = a_person;
    	this.m_products = a_products;
    }

    /**
	 * SendDetailsEmailController::call()
	 * 
	 * NAME:
	 * 
	 * 		SendDetailsEmailController::call() - send email with details of purchase
	 * 						to user who bought the products.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		call() throws Exception
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Send email with details of purchase to user who bought the products.
	 *      Also inform them of their current fund total.
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
    	String to = m_person.GetEmail();
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
    		message.setSubject("Thanks from Banta's Bargains!");

    		String messageText = "";
    		for(ProductModel pdt : m_products)
    		{
    			messageText += "------------------------------------\n";
    			messageText += pdt.GetName() + "\n";
    			messageText += "Quantity bought: " + pdt.GetQuantity() + "\n";
    			messageText += "Total price: " + pdt.GetPrice() + "\n";
    		}

    		messageText += "\n\nYou have " + m_person.GetBalance() + " available in your account.\n\n";
    		messageText += "Thank you for doing business with us.\n";
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
		return null;
	}
}
