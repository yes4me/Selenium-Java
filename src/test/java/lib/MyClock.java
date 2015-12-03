/* ===========================================================================
Created:	2015/06/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Generic time related methods
=========================================================================== */

package lib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyClock {
	/* ---------------------------------------------------------------------------
	Specific day format
	--------------------------------------------------------------------------- */

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public static String getDateYYYY() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public static String getDateMM() {
		DateFormat dateFormat = new SimpleDateFormat("MM");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public static String getDateDD() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/* ---------------------------------------------------------------------------
	Time
	--------------------------------------------------------------------------- */

	public static String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/* ---------------------------------------------------------------------------
	Used to get an unique token
	--------------------------------------------------------------------------- */

	public static String get_microSeconds() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long howMany = (c.getTimeInMillis()-System.currentTimeMillis());
		return Long.toString(howMany);
	}

	/* ---------------------------------------------------------------------------
	Others
	--------------------------------------------------------------------------- */

	public static String getFullDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
}