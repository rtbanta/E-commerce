/*****************************************************************
 * XMLPages.java - get the XML file at the given path.           *
 *****************************************************************/
package view;
import java.io.File;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

public class XMLPages
{
	private Document m_xmlPage;
	private File m_xml;
	SAXBuilder m_builder = new SAXBuilder();
	
	/**
	 * XMLPages::LoadFile()
	 * 
	 * NAME:
	 * 
	 * 		XMLPages::LoadFile() - get the xml document at the passed-in location.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		LoadFile(String a_location)
	 * 
	 * 			@param  a_location  --> String representing location of xml document.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		 Returns the xml document at the passed-in location.
	 * 
	 * RETURNS:
	 * 
	 * 		xml file for transforming with xsl file.
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
	public synchronized Document LoadFile(String a_location)
	{
		m_xml = new File(a_location);
		try
		{
			m_xmlPage = (Document) m_builder.build(m_xml);
			return m_xmlPage;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

