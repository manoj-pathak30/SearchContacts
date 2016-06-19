package com.contact;

/**
 * API to add/search contact from contactList.
 * @author manojP
 */

import java.util.Set;
import java.util.TreeSet;

public class ContactService {

	private Set<Contact> contacts;
	
	public ContactService() {
		this.contacts =  new TreeSet<>();
	}
	
	/**
	 * API to add contact into contactList to make it searchable.
	 * @param name
	 */
	public void addContact(String name) {

		if (name.length() > 50 || (name.contains(" ") && name.split("\\s").length > 2)) {
			System.out.println("Please enter valid name.");
			return;
		}

		Contact contact = new Contact(name);
		contacts.add(contact);
	}

	/**
	 * API to search and print contacts based on search string.
	 * @param searchStr
	 */
	public void searchContact(String searchStr) {
		
		for (Contact contact : contacts) {
			if (contact.getName().equalsIgnoreCase(searchStr)) {
				System.out.println(contact.getName());
			} else if (contact.getFirstName().toLowerCase().startsWith(searchStr) || contact.getLastName().toLowerCase().startsWith(searchStr)) {
				System.out.println(contact.getName());
			}
		}
	}
	
}
