package com.helpshift.domain;

/**
 * java class for Contact
 * 
 * @author manojP
 *
 */
public class Contact implements Comparable<Contact> {

	private String firstName;
	private String lastName;
	private String name;

	public Contact(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName  = lastName != null ? lastName: "";
		this.name = this.firstName + (this.lastName.equals("") ? "" : " "+this.lastName);
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
		return this.name;
	}

	@Override
	public int compareTo(Contact contact) {
		return this.name.compareToIgnoreCase(contact.getName());
	}
	
	@Override
	public int hashCode() {
		return (firstName.hashCode() * (lastName != null ? lastName.hashCode() : 0));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
	    if(!(obj instanceof Contact)) 
	    	return false;

	    Contact other = (Contact) obj;
	    if(!this.name.equalsIgnoreCase(other.name)) 
	    	return false;
	    
	    return true;
	}
}