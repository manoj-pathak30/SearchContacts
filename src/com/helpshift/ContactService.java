package com.helpshift;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;


public class ContactService {

	private HashMap<Character, Set<Contact>> contactLookup = new HashMap<>();

	/**
	 * API to add contact into contactList to make it searchable.
	 * @param name
	 */
	public void addContact(String name) {

		Contact contact = null;
		Character firtstChar = null;
		Character lnameChar = null;
		
		if (name.contains(" ")) {
			contact = new Contact(name.split("\\s+")[0], name.split("\\s+")[1]);
			firtstChar = contact.getFirstName().toLowerCase().charAt(0);
			lnameChar = contact.getLastName().toLowerCase().charAt(0);
		} else {
			contact = new Contact(name.trim(), "");
			firtstChar = contact.getFirstName().toLowerCase().charAt(0);
		}

		// to create firstName map
		Set<Contact> contacts = null;
		if (contactLookup.containsKey(firtstChar)) {
			contacts = contactLookup.get(firtstChar);
			contacts.add(contact);
		} else {
			contacts = new LinkedHashSet<>();
			contacts.add(contact);
		}
		contactLookup.put(firtstChar, contacts);

		// to create lastName map
		Set<Contact> lastContacts = null;
		if (lnameChar != null) {
			if (contactLookup.containsKey(lnameChar)) {

				lastContacts = contactLookup.get(lnameChar);
				lastContacts.add(contact);
			} else {
				lastContacts = new LinkedHashSet<>();
				lastContacts.add(contact);
			}
			contactLookup.put(lnameChar, lastContacts);
		}
	}

	/**
	 * API to search and print contacts based on search string.
	 * @param searchStr
	 */
	public Set<Contact> searchContact(String searchStr) {
		
		Set<Contact> searchResult = new TreeSet<>();
		
		Set<Contact> contacts = contactLookup.get(searchStr.charAt(0));
		for (Contact contact : contacts) {
			
			if (contact.getName().equalsIgnoreCase(searchStr)||(searchStr.contains(" ") && contact.getName().contains(searchStr))) {
				searchResult.add(contact);
			} else if (contact.getFirstName().toLowerCase().startsWith(searchStr) || contact.getLastName().toLowerCase().startsWith(searchStr)) {
				searchResult.add(contact);
			}
		}
		
		return searchResult;
	}

}

