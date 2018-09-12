package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import model.*;
/**
 * This class contain most of the operation related to Personal Expense Manager
 * <p>
 * This class prepare various method to handle the user action.
 * This class make use of <code> Repository</code> to store the data. 
 * Also its using <code>ReportService</code> to generate different required reports.
 * @author Mohammed
 */
public class PersonalExpenseController {
	
	/**
	 * Declare a reference of repository by calling a static method.
	 * Which return a singleton repository object.
	 */
	Repository repo = Repository.getRepository();
	 
	/**
	 * Declare a reference of reportService to call different method to calculate reports.
	 */
	ReportService reportService = new ReportService();
	
	/**
	 * Declare a Scanner object to take standard input from keyboard.
	 */
	private Scanner keyboard = new Scanner(System.in);
	
	/*
		public PersonalExpenseController() {	 
	}*/

	/**
	 * This method is taking expense category name as input to add new category
	 * in the system.
	 */
	public void onAddCategory(String name)
	{
		//in.nextLine();//new line char is read here which is already present in stream and its not in used 
		//System.out.print("Enter Category Name: ");
		//String catName = keyboard.nextLine();
		Category cat = new Category(name);
		repo.catList.add(cat);
		System.out.println("Success: Category Added.");
		 
	}
	
	/**
	 * call this method to print existing category
	 */
	public void onCategoryList()
	{
		System.out.println("Category List");
		List<Category> cList = repo.catList;
		for(int i=0; i<cList.size(); i++){
			Category c = cList.get(i);
			 
			System.out.println((i+1) + ". "+c.getName() + ", "+c.getCategoryId());
		}
		 
	}
	
	/**
	 * Call this method to enter expense details. The entered details will be added in repository.
	 */
	public void onExpenseEntry()
	{
		System.out.println("Enter Details Expense Entry...");
		onCategoryList();
		System.out.print("Choose Category: ");
		int catChoice = keyboard.nextInt();
		Category selectedCat = repo.catList.get(catChoice-1);
		
		System.out.print("Enter Amount: ");
		Float amount = keyboard.nextFloat();
		
		System.out.print("Enter Remark: ");
		keyboard.nextLine();
		String remark = keyboard.nextLine();
		
		System.out.print("Enter Date(DD/MM/YYYY): ");
		String dateAsString = keyboard.nextLine();
		Date date = DateUtil.stringToDate(dateAsString);
		
		//Add Expenses details in Expense object
		Expense exp = new Expense();
		
		 
		exp.setCategoryId(selectedCat.getCategoryId());
		exp.setAmount(amount);
		exp.setRemark(remark);
		exp.setDate(date);
		
		//Store expense object in repository
		
		repo.expList.add(exp);
		System.out.println("Success: Expense Added");
				 
	}

	/*public void onExpenseEntry(Float amount, Date date, String remark)
	{
		System.out.println("Enter Details Expense Entry...");
		onCategoryList();
		System.out.print("Choose Category: ");
		int catChoice = keyboard.nextInt();
		Category selectedCat = repo.catList.get(catChoice-1);
		
		System.out.print("Enter Amount: ");
		Float amount = keyboard.nextFloat();
		
		System.out.print("Enter Remark: ");
		keyboard.nextLine();
		String remark = keyboard.nextLine();
		
		System.out.print("Enter Date(DD/MM/YYYY): ");
		String dateAsString = keyboard.nextLine();
		Date date = DateUtil.stringToDate(dateAsString);
		
		//Add Expenses details in Expense object
		Expense exp = new Expense(amount,date,remark);
		
		//Category cat = new Category();
		//cat.getCategoryId();
		 exp.setCategoryId(selectedCat.getCategoryId());
		exp.setAmount(amount);
		exp.setRemark(remark);
		exp.setDate(date);
		
		//Store expense object in repository
		
		repo.expList.add(exp);
		System.out.println("Success: Expense Added");
			 
	}*/

	/**
	 * The method prints all entered expenses.
	 */
	public void onExpenseList() {
		System.out.println("Expense listing...");
		List<Expense> expList = repo.expList;
		for(int i=0; i<expList.size(); i++)
		{
			Expense exp = expList.get(i);
			String catName = reportService.getCategoryNameById(exp.getCategoryId());
			String dateString = DateUtil.dateToString(exp.getDate());
			System.out.println((i+1)+ ". "+ catName+", "+exp.getAmount()+", "+exp.getRemark()+", "+dateString);
		}
		
	}
	
	/*//To get the category name by ID
	String getCategoryNameById(Long categoryId)
	{
		for(Category c : repo.catList)
		{
			if(c.getCategoryId().equals(categoryId))
			{
				return c.getName();
			}	
		}
		return null;//no such category id present
	}*/

	
	//Report service 
	
	/**
	 * This method is called from menu to prepare monthly-wise-expense-total.
	 * Its using <code>ReportService</code> to calculate the report.
	 * The returned result is printed by this method.
	 * Means this method invoke a call to generate report then result is printed by the method.
	 */
	//Monthly report
	public void onMonthlyExpenseList() 
	{
		System.out.println("Monthly Expense listing...");
		Map<String,Float> resultMap = reportService.calculateMonthlyTotal();
		Set<String> keys = resultMap.keySet();
		for(String yearMonth : keys)
		{
			//2016,01 - [0] will be year and [1] will be monthNo
			String[] arr = yearMonth.split(",");
			String year = arr[0];
			Integer monthNo = new Integer(arr[1]);
			String monthName = DateUtil.getMonthName(monthNo);
			
			System.out.println(year+", "+monthName+" : "+resultMap.get(yearMonth));
		}
		
	}

	/**
	 * This method is called from menu to prepare yearly-wise-expense-total.
	 * Its using <code>ReportService</code> to calculate the report.
	 * The returned result is printed by this method.
	 * Means this method invoke a call to generate report then result is printed by the method.
	 */
	//Yearly report
	public void onYearlyExpenseList() {
		 
		System.out.println("Yearly Expense Total...");
		Map<Integer,Float> resultMap = reportService.calculateYearlyTotal();
		Set<Integer> years = resultMap.keySet();
		
		Float total = 0.0F;
		
		for(Integer year : years)
		{
			Float exp = resultMap.get(year);
			total = total+exp;
			System.out.println(year+" : "+exp);		
		}
		System.out.println("----------------------");
		System.out.println("Total Expenses (EUR): "+total);
		
	}

	/**
	 * This method is called from menu to prepare category-wise-expense-total.
	 * Its using <code>ReportService</code> to calculate the report.
	 * The returned result is printed by this method.
	 * Means this method invoke a call to generate report then result is printed by the method.
	 */
	public void onCategorizedExpenseList() {
		 
		System.out.println("Categorized Expense listing...");
		Map<String,Float> resultMap = reportService.calculateCategorizedTotal();
		Set<String> categories = resultMap.keySet();
		Float netTotal = 0.0F;
		
		for(String categoryName : categories)
		{
			Float catWiseTotal = resultMap.get(categoryName);
			netTotal = netTotal+catWiseTotal;
			System.out.println(categoryName+" : "+ catWiseTotal );
		}
		System.out.println("------------------------");
		System.out.println("Net Total : "+netTotal);
	}
	
	/**
	 * This method stop JVM before that it store data permanantly 
	 */
	
	public void onExit()
	{
		persistRepository();
		System.exit(0);
	}

	//
	/*public void persistRepository()
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("expenses.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(repo.expList);//Store expense list in the file
			
			//use finally block -TODO
			oos.close();
			fos.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}		
	}
	*/
	//Write file method reusable
	public void serialize(String file, Object obj)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);//Store expense list in the file
			
			//use finally block -TODO
			oos.close();
			fos.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}		
	}
	
	//Writing file 
	private void persistRepository() 
	{
		serialize("expenses.ser", repo.expList); 
		serialize("category.ser", repo.catList);
	}
	//Read file method
	public Object deser(String file)
	{
		try 
		{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();//dser
		return obj;
		}
		catch (Exception ex) 
		{			 
			//ex.printStackTrace();
			System.out.println("No existing file");
			return null;
		}
		
	}
	
	//Reading file
	public void restoreRepository() {
		 List<Expense> expList = (List<Expense>) deser("expenses.ser");//Object down casting
		 List<Category> catList = (List<Category>) deser("category.ser");
		 
		 if(expList !=null)
		 {
			 // set existing expenses in repository
			 repo.expList= expList;
		 }
		 if(catList !=null)
		 {
			 // set existing category in repository
			 repo.catList= catList;
		 }		
	}

}

	/**
	 * This method is preparing sample data for testing purpose.
	 * It should be report once app is tested ok.
	 */

	//For sample date 
	/*public void prepareSampleData()
	{
		Category catParty = new Category("Party");
		delay();
		Category catShopping = new Category("Shopping");
		delay();
		Category catGift = new Category("Gift");
		delay();
		
		repo.catList.add(catParty);
		repo.catList.add(catShopping);
		repo.catList.add(catGift);
		
		//2016 Jan
		Expense e1 = new Expense(catParty.getCategoryId(), 1000.F, DateUtil.stringToDate("05/01/2016"), "N/A");
		delay();
		Expense e2 = new Expense(catShopping.getCategoryId(), 2000.F, DateUtil.stringToDate("07/01/2016"), "N/A");
		delay();
		
		//2016 Fab
		Expense e3 = new Expense(catGift.getCategoryId(), 100.F, DateUtil.stringToDate("10/02/2016"), "N/A");
		delay();
		Expense e4 = new Expense(catParty.getCategoryId(), 2500.F, DateUtil.stringToDate("20/03/2016"), "N/A");
		delay();
		
		//2016 Dec
		Expense e5 = new Expense(catShopping.getCategoryId(), 1400.F, DateUtil.stringToDate("05/12/2016"), "N/A");
		delay();
		Expense e6 = new Expense(catParty.getCategoryId(), 2800.F, DateUtil.stringToDate("07/01/2017"), "N/A");
		delay();
		
		//2017 Jan
		Expense e7 = new Expense(catGift.getCategoryId(), 1000.F, DateUtil.stringToDate("05/02/2017"), "N/A");
		delay();
		Expense e8 = new Expense(catShopping.getCategoryId(), 2000.F, DateUtil.stringToDate("07/03/2017"), "N/A");
		
		//Adding to the Expense list
		repo.expList.add(e1);

		repo.expList.add(e2);

		repo.expList.add(e3);

		repo.expList.add(e4);

		repo.expList.add(e5);

		repo.expList.add(e6);

		repo.expList.add(e7);

		repo.expList.add(e8);
	}

	 
	//This method for to delay to create the catId from the system
	//if there's no delay then all the Id will be the same 
	//This method sleep a thread fpor 10ms
	 
	private void delay() {
		 try {
			Thread.sleep(10);
		} catch (InterruptedException ex) {
			 
			ex.printStackTrace();
		}
		
	}
*/

