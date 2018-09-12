package model;
import java.util.ArrayList;
/**
 * The class is used as Database/Repository and its a singleton
 * Repository should not get multiple
 */
import java.util.List;

/**
 * The class is used as Database/Repository and its a singleton.
 * @author Mohammed
 *
 */
public class Repository {
	/**
	 * The list holds all expense added by user 
	 */
	public List<Expense> expList = new ArrayList();
	
	/**
	 * The list holds all expense-categories added by user 
	 */
	public List<Category> catList = new ArrayList();
	
	/**
	 * A singleton reference of repository.
	 */
	private static Repository repository;//creating object
	/**
	 * Private constructor to restrict object creation form outside.
	 */
	private Repository(){
		
	}
	
	//when we call getRepository will get one Repository object
	/**
	 * This method provides a singleton objectof Repository. 
	 * @return
	 */
	public static Repository getRepository(){
		if(repository == null){//if repository null it will give new repository
			repository = new Repository();
		}
		return repository;
	}

}
