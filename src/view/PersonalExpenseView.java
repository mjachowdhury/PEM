package view;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import controller.DateUtil;
import controller.PersonalExpenseController;
import controller.ReportService;
import model.Category;
import model.Repository;

public class PersonalExpenseView {
	private PersonalExpenseController thePersonalExpenseController;
	private Scanner keyboard = new Scanner(System.in);

	/**
	 * This variable store the value of menu-choice
	 */
	private int choice;

	Repository repo = Repository.getRepository();

	/**
	 * Call this constructor to create PersonalExpenseView object with default
	 * details.
	 */
	// Creating instance of Personal Expense Controller
	public PersonalExpenseView() {
		this.thePersonalExpenseController = new PersonalExpenseController();
		// thePersonalExpenseController.prepareSampleData();// TODO: Delete this
		// method call after testing is completed.

		thePersonalExpenseController.restoreRepository();

	}
	
	/**
	 * This method prepare a Personal Expense Manger menu using switch-case and
	 * infinite loop. Also ask for user choice.
	 * 
	 */
	public void showMenu() {
		while (true) {
			printMenu();
			switch (choice) {
			case 1:
				String name;
				keyboard.nextLine();// new line char is read here which is
									// already present in stream and its not in
									// used
				System.out.print("ENTER THE CATEGORY NAME: ");
				name = keyboard.nextLine();
				thePersonalExpenseController.onAddCategory(name);

				// thePersonalExpenseController.onAddCategory();
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
	 * This method prints a menu(CUI(Command User Interface)/CLI (Command Line
	 * Inerface)menu)
	 */
	public void printMenu() {
		System.out.println("\n\t\t\t-----PERSONAL EXPENSE MENU-----");
		System.out.println("\t\t\t===============================\n");
		System.out.println("\t\t\t1. ADD CATEGORY");
		System.out.println("\t\t\t2. CATEGORY LIST");
		System.out.println("\t\t\t3. EXPENSE ENTRY");
		System.out.println("\t\t\t4. EXPENSE LIST");
		System.out.println("\t\t\t5. MONTHLY EXPENSE LIST");
		System.out.println("\t\t\t6. YEARLY EXPENSE LIST");
		System.out.println("\t\t\t7. CATEGORIZED EXPENSE LIST");
		System.out.println("\t\t\t0. EXIT FORM THE SYSTEM\n");
		System.out.println("\t\t\t------------------------------");
		System.out.print("\t\t\tENTER YOUR CHOICE: ");
		choice = keyboard.nextInt();
	}

	/**
	 * This method is called to hold a output screen after processing the
	 * requested task and wait for a char input to continue to the menu.
	 */
	public void pressAnyKeyToContinue() {
		System.out.println("PRESS ANY KEY TO CONTINUE...");
		try {
			System.in.read();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
