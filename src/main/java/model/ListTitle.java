/**
 * @author Evan Bunnell - ebunnell
 * CIS175 12737 - Fall 2023
 * Sep 13, 2023
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class ListTitle {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="TITLE")
	private String title;
	@Column(name="MEDIUM")
	private String medium;
	@Column(name="LOCATION")
	private String location;

	public ListTitle() {
		super();
	}

	public ListTitle(String title, String medium, String location) {
		super();
		this.title = title;
		this.medium = medium;
		this.location = location;
	}

	/**
	 * Getters
	 * @return id
	 * @return store
	 * @return item
	 */
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMedium() {
		return medium;
	}

	public String getLocation() {
		return location;
	}

	/**
	 * Setters
	 * @param id - identification number
	 * @param title - location purchased
	 * @param medium - item purchased
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String returnItemDetails() {
		return this.title + ": " + this.medium + " @" + this.location;
	}

}
