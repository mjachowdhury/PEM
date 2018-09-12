package view;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import controller.DateUtil;
import controller.PersonalExpenseController;
import controller.ReportService;
import model.Category;
import model.Repository;

public class PersonalExpenseView 
{
	private PersonalExpenseController thePersonalExpenseController;
	private Scanner keyboard = new Scanner(System.in);
	
	/**
	 * This variable store the value of menu-choice
	 */
	private int choice;
	
	Repository repo = Repository.getRepository();
	
	/**
	 * Call this constructor to create PersonalExpenseView object with default details.
	 */
	//Creating instance of Personal Expense Controller
	public PersonalExpenseView() {
		this.thePersonalExpenseController = new PersonalExpenseController();
		//thePersonalExpenseController.prepareSampleData();// TODO: Delete this method call after testing is completed.
		
		thePersonalExpenseController.restoreRepository();
		
	}
	
/**
 * This method prepare a Personal Expense Manger menu using switch-case and infinite loop.
 * Also ask for user choice. 
 * 
 */
	public void showMenu()
	{
		while(true)
		{
			printMenu();
			switch (choice) {
				case 1:
					String name;
					keyboard.nextLine();//new line char is read here which is already present in stream and its not in used 
					System.out.print("Enter the category name: ");
					name = keyboard.nextLine();
					thePersonalExpenseController.onAddCategory(name);
					
					//thePersonalExpenseController.onAddCategory();
					pressAnyKeyToContinue();
					break;
					
				case 2:	
					
					thePersonalExpenseController.onCategoryList();
					pressAnyKeyToContinue();
					break;
					
				case 3:
					 
					/*System.out.println("Enter Details Expense Entry...");
					thePersonalExpenseController.onCategoryList();
					System.out.print("Choose Category: ");
					int catChoice = keyboard.nextInt();
					Category selectedCat = repo.catList.get(catChoice-1);
					
					selectedCat.setCategoryId(selectedCat.getCategoryId());
					//thePersonalExpenseController.onCategoryList();
					
					System.out.print("Enter Amount: ");
					Float amount = keyboard.nextFloat();
					
					System.out.print("Enter Remark: ");
					keyboard.nextLine();
					String remark = keyboard.nextLine();
					
					System.out.print("Enter Date(DD/MM/YYYY): ");
					String dateAsString = keyboard.nextLine();
					Date date = DateUtil.stringToDate(dateAsString);
					
					 
					thePersonalExpenseController.onExpenseEntry();
					pressAnyKeyToContinue();
					break;*/
					
					
					/*System.out.println("Enter Details Expense Entry...");
					thePersonalExpenseController.onCategoryList();
					System.out.print("Choose Category: ");
					int catChoice = keyboard.nextInt();
					Category selectedCat = repo.catList.get(catChoice-1);
					
					selectedCat.setCategoryId(selectedCat.getCategoryId());
					//thePersonalExpenseController.onCategoryList();*/
					
					/*System.out.print("Enter Amount: ");
					Float amount = keyboard.nextFloat();
					
					System.out.print("Enter Remark: ");
					keyboard.nextLine();
					String remark = keyboard.nextLine();
					
					System.out.print("Enter Date(DD/MM/YYYY): ");
					String dateAsString = keyboard.nextLine();
					Date date = DateUtil.stringToDate(dateAsString);*/
					
					 
					thePersonalExpenseController.onExpenseEntry();
					pressAnyKeyToContinue();
					break;
					
				case 4:
					 
					thePersonalExpenseController.onExpenseList();
					pressAnyKeyToContinue();
					break;
				case 5:
					 
					thePersonalExpenseController.onMonthlyExpenseList();
					pressAnyKeyToContinue();
					break;
				case 6:
					 
					thePersonalExpenseController.onYearlyExpenseList();
					pressAnyKeyToContinue();
					break;
				case 7:
					 
					thePersonalExpenseController.onCategorizedExpenseList();
					pressAnyKeyToContinue();
					break;
				case 0:
					thePersonalExpenseController.onExit();
					break;
			}
		}
	}
	/**
	 * This method prints a menu(CUI(Command User Interface)/CLI (Command Line Inerface)menu)
	 */
	public void printMenu() 
	{
		System.out.println("-----Personal Expense Menu-----");
		System.out.println("1. Add Category");
		System.out.println("2. Category List");
		System.out.println("3. Expense Entry");
		System.out.println("4. Expense List");
		System.out.println("5. Monthly Expense List");
		System.out.println("6. Yearly Expense List");
		System.out.println("7. Categorized Expense List");
		System.out.println("0. Exit");
		System.out.println("------------------------------");
		System.out.print("Enter your Choice: ");
		choice = keyboard.nextInt();
	}
	/**
	 * This method is called to hold a output screen after processing the requested task and wait for a char input to continue to the menu.
	 */
	public void pressAnyKeyToContinue()
	{
		System.out.println("Press any key to continue...");
		try {
			System.in.read();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
}
