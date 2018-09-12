package model;

import java.io.Serializable;

/**
 * This is a domain class represent a Category
 * @author Mohammed
 *
 */
public class Category implements Serializable {
	/**
	 * It refers to a unique category Id. 
	 * Here is simply generated using current time. but in real time application it should be generated using some professional strategy or algoritham.
	 */
	private Long categoryId = System.currentTimeMillis();//Tempory Id generated 
	
	/**
	 * Name of expense category
	 */
	private String name;
	
	public Category(String name) {
		this.name = name;
	}

	public Category(Long categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public Category() {
		 
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
