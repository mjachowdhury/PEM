import view.PersonalExpenseView;

/**
 * This class is an entry point of execution for Personal Expense Manager
 * Application
 * 
 * @author Mohammed
 */
public class MainApplication {
	/**
	 * This method is creating <code>PersonalExpenseView</code> object and show
	 * app menu by calling showMenu() method.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		PersonalExpenseView personExpView = new PersonalExpenseView();
		personExpView.showMenu();
	}

}
