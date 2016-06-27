package com.helpshift.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

public class ContactService {

	private Map<Character, ContactList> contactLookup = new HashMap<Character, ContactList>();

	/**
	 * API to add contact into contactList to make it searchable.
	 * 
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
		ContactList contacts = new ContactList();
		if (contactLookup.containsKey(firtstChar)) {
			contacts = contactLookup.get(firtstChar);
		}
		contacts.add(contact);
		contactLookup.put(firtstChar, contacts);

		// to create lastName map
		ContactList lastContacts = new ContactList();
		if (lnameChar != null && !lnameChar.equals("")) {
			if (contactLookup.containsKey(lnameChar)) {
				lastContacts = contactLookup.get(lnameChar);
			}
			lastContacts.add(contact);
			contactLookup.put(lnameChar, lastContacts);
		}
	}

	public Map<Character, ContactList> getContactLookup() {
		return contactLookup;
	}
	
	public List<Contact> searchContacts(String searchStr) {
		LinkedList<SearchStrategy> strategies = new LinkedList<SearchStrategy>(
				Arrays.asList(new ExactStringSearchStrategy(this.contactLookup), new ContainsStringSearchStrategy(this.contactLookup)));
		
		List<Contact> result = new ArrayList<Contact>();
		for (SearchStrategy searchStrategy : strategies) {
			List<Contact> temp = searchStrategy.search(searchStr);
			if (temp.size() > 0) {
				result.addAll(temp);
				break;
			}
		}
		return result;
	}
	
}
