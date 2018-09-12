package controller;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import model.Category;
import model.Expense;
import model.Repository;

/**
 * This class contains various method to calculate Personal expense manager app
 * report
 * 
 * @author Mohammed
 *
 */
public class ReportService {
	/**
	 * Declare a reference of singleton repository
	 */
	// i can do static or without static
	// private static Repository repo = Repository.getRepository();
	private Repository repo = Repository.getRepository();

	/**
	 * The method calculates month-wise total and returns result in Map Its
	 * preparing date in proper order
	 * 
	 * @return
	 */
	// I can do static or without static this method
	public Map<String, Float> calculateMonthlyTotal() {
		Map<String, Float> m = new TreeMap();
		for (Expense exp : repo.expList) {
			Date expDate = exp.getDate();
			String yearMonth = DateUtil.getYearAndMonth(expDate);

			if (m.containsKey(yearMonth)) {
				// when expense is already present for a month
				Float total = m.get(yearMonth);
				total = total + exp.getAmount();
				m.put(yearMonth, total);// new total; replace old total
			} else {
				// this month is not yet added , so add here
				m.put(yearMonth, exp.getAmount());
			}
		}
		return m; // return final result as Map

	}

	// Yearly expenses report and total

	/**
	 * The method calculates year-wise total and returns result in Map Its
	 * preparing date in proper order
	 * 
	 * @return
	 */
	public Map<Integer, Float> calculateYearlyTotal() {
		Map<Integer, Float> m = new TreeMap();
		for (Expense exp : repo.expList) {
			Date expDate = exp.getDate();
			Integer year = DateUtil.getYear(expDate);

			if (m.containsKey(year)) {
				// when expense is already present for a year
				Float total = m.get(year);
				total = total + exp.getAmount();
				m.put(year, total);// new total; replace old total
			} else {
				// this year is not yet added , so add here
				m.put(year, exp.getAmount());
			}
		}
		return m; // return final result as Map

	}

	/**
	 * The method calculates category-wise total and returns result in Map Its
	 * preparing date in proper order
	 * 
	 * @return
	 */
	public Map<String, Float> calculateCategorizedTotal() {
		Map<String, Float> m = new TreeMap();
		for (Expense exp : repo.expList) {
			Long categoryId = exp.getCategoryId();
			String catName = this.getCategoryNameById(categoryId);

			if (m.containsKey(catName)) {
				// when expense is already present for a category
				Float total = m.get(catName);
				total = total + exp.getAmount();
				m.put(catName, total);// new total; replace old total
			} else {
				// this category is not yet added , so add here
				m.put(catName, exp.getAmount());
			}
		}
		return m; // return final result as Map

	}

	/**
	 * The method returns category name for given categoryId. Returns null when
	 * wrong categoryId is suppplied
	 * 
	 * @param categoryId
	 * @return
	 */
	// To get the category name by ID
	public String getCategoryNameById(Long categoryId) {
		for (Category c : repo.catList) {
			if (c.getCategoryId().equals(categoryId)) {
				return c.getName();
			}
		}
		return null;// no such category id present
	}

}
