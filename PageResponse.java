/*****************************************************************
 * PageResponse.java - use xml document and xsl file to return   *
 * 		an xsl file with information from the xml file.          *
 *****************************************************************/
package view;
import java.io.File;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.transform.JDOMSource;

public class PageResponse
{
	/**
	 * PageResponse::Respond()
	 * 
	 * NAME:
	 * 
	 * 		PageResponse::Respond() - respond to user request.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		Respond(HttpServletResponse a_response, Document a_xmlPage, File a_xslPage)
	 * 			throws Exception
	 * 
	 * 			@param  a_response  --> response object to be returned to user.
	 *                
	 *          @param  a_xmlPage  --> XML document to be transformed.
	 *          
	 *          @param  a_xslPage  --> XSL document to be transformed.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		Respond to the user request with a new XSL page (with additional data 
	 *      from XML page if necessary). 
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
	 * 		2/1/2013
	 * 
	 */
	public void Respond(HttpServletResponse a_response, Document a_xmlPage, File a_xslPage) throws Exception
	{
		try 
		{
			a_response.setContentType("text/html");
			OutputStream a_outStream = a_response.getOutputStream();
			StreamResult result = new StreamResult(a_outStream);
	        JDOMSource xmlSource = new JDOMSource(a_xmlPage);
	        StreamSource xslSource =  new StreamSource(a_xslPage);
	        
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer transformer = tFactory.newTransformer(xslSource);
	        transformer.transform(xmlSource, result);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
