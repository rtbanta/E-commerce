/*****************************************************************
 * XSLPages.java - get the XSL file at the given path.           *
 *****************************************************************/
package view;
import java.io.File;

public class XSLPages 
{
	private File m_xslFile;

	/**
	 * XSLPages::LoadFile()
	 * 
	 * NAME:
	 * 
	 * 		XSLPages::LoadFile() - get the xsl file at the passed-in location.
	 * 
	 * SYNOPSIS:
	 * 
	 * 		LoadFile(String a_location)
	 * 
	 * 			@param  a_location  --> String representing location of xsl file.
	 * 
	 * DESCRIPTION:
	 * 
	 * 		 Returns the xsl file at the passed-in location.
	 * 
	 * RETURNS:
	 * 
	 * 		xsl file for display to the user.
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
	public synchronized File LoadFile(String a_location) 
	{
		m_xslFile = new File(a_location);
		return m_xslFile;
	}
}
