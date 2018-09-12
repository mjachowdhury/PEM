package model;

import java.io.Serializable;
import java.util.Date;

/**
 * This is a domain class represent Expense
 * @author Mohammed
 *
 */
public class Expense implements Serializable {
	/**
	 * A unique expense id , here its auto-generated as current milli sec..
	 * but should use some standard algo for primary key generation
	 */
	private Long expenseId = System.currentTimeMillis();
	/**
	 * Represent a category of this expense.
	 */
	private Long categoryId;//FK
	private Float amount;
	private Date date;
	private String remark;
	
	
	public Expense() {		 
	}


	public Expense(Long categoryId, Float amount, Date date, String remark) {
		this.categoryId = categoryId;
		this.amount = amount;
		this.date = date;
		this.remark = remark;
	}
	
	public Expense( Float amount, Date date, String remark) {
		 
		this.amount = amount;
		this.date = date;
		this.remark = remark;
	}


	public Long getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
