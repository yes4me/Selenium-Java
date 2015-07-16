/* ===========================================================================
Created:	2015/07/12
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Minimum Page object requirement
=========================================================================== */

package pages_wordpress;


public interface BasicPageObject {
	public void visit();
	public boolean checkPage();
	public void waitForPage();
}