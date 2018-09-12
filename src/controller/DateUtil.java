package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains static method to handle dates.
 * @author Mohammed
 *
 */
public class DateUtil {
	public static final String[] MONTHS = {"January","February","March","April","May","June","July","August","September","October","November","December"};   
	
	/**
	 * This method converts String-date into Date object 
	 * @param dateAsString string formatted date (ex. 11/06/2017 : DD/MM/YYYY)
	 * @return returns a Date object for input date-string
	 */
	//This method will take date as a string and will return date object
	public static Date stringToDate(String dateAsString)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return df.parse(dateAsString);
		} catch (ParseException ex) {		 
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method converts Date object to String
	 * @param date Dare object to be converted to String 
	 * @return String date n DD/MM/YYY format
	 */
	//This method will return date object as a string 
	public static String dateToString(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
	
	/**
	 * This method returns Year and Month for given Date in format :
	 * Ex. 2017,01 ; 2017,02 so on ...
	 * @param date year and month will be extracted for this date 
	 * @return return year and month for input date
	 */
	public static String getYearAndMonth(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy,MM");//Ex.2016,01, 2016,02
		return df.format(date);
	}
	
	/**
	 * Returns Year for input date
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return new Integer(df.format(date));
	}
	
	/**
	 * This method returns Months Name for given month number
	 * 01:Jan, 02:Feb...., 12:Dec
	 * @param monthNo month number between 1 to 12
	 * @return returns month names for month number
	 */
	public static String getMonthName(Integer monthNo)
	{
		return MONTHS[monthNo-1];
	}
	
	

}
