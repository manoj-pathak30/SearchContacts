package com.contact;
/**
 * java class for Contact
 * @author manojP
 *
 */
public class Contact implements Comparable<Contact>{

	private String firstName;
	private String lastName;
	private String name;

	public Contact(String name) {
		this.name = name;

		if (name.contains(" ")) {
			this.firstName = name.split("\\s+")[0];
			this.lastName = name.split("\\s+")[1];
		} else {
			this.firstName = name;
			this.lastName = "";
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Contact{" + name + '}';
	}

	public int compareTo(Contact c) {
		return name.compareToIgnoreCase(c.getName());
	}
}