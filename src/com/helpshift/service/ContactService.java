package com.helpshift.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

public class ContactService {

	private Map<Character, ContactList> contactLookup = new HashMap<Character, ContactList>();
	private List<Contact> result = null;
	
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
		 
		result = new ArrayList<>();

		if (contactLookup.containsKey(searchStr.charAt(0))) {
			List<Contact> contacts = contactLookup.get(searchStr.charAt(0));
			if (contacts != null) {
				List<Contact> temp = new ArrayList<>(contacts);
				Collections.sort(temp, new Sorter());
				search(temp, searchStr);
				Collections.sort(result, new Sorter());
				temp.clear();
			}
		}

		return result;
	}

	private void search(List<Contact> contacts, String searchStr) {
		
		if (contacts.isEmpty()) {
			return;
		}

		int length = contacts.size();
		int midIdx = length / 2;
		Contact contact = contacts.get(midIdx);
		String name = contact.getName();

		if (!name.toLowerCase().startsWith(searchStr)) {
			name = contact.getLastName();
		}

		if (match(name, searchStr) > 0) {
			searchInLeftList(contacts.subList(0, midIdx), searchStr, midIdx);
		} else if (match(name, searchStr) < 0) {
			searchInRightList(contacts.subList(midIdx, length), searchStr, midIdx);
		} else if (match(name, searchStr) == 0) {
			result.add(contact);
			contacts.remove(contact);
			search(contacts, searchStr);
		}
	}

	private int match(String name, String searchStr) {
		return name.substring(0, Math.min(searchStr.length(), name.length())).compareToIgnoreCase(searchStr);
	}

	private void searchInRightList(List<Contact> contacts, String searchStr, int midIdx) {
		while (contacts.size() > 1) {
			search(contacts, searchStr);
		}
	}

	private void searchInLeftList(List<Contact> contacts, String searchStr, int midIdx) {
		while (contacts.size() > 1) {
			search(contacts, searchStr);
		}
	}

	private class Sorter implements Comparator<Contact> {

		@Override
		public int compare(Contact o1, Contact o2) {
			if (o1.getFirstName().equalsIgnoreCase(o2.getFirstName())) {
				if (o1.getLastName() == null && o2.getLastName() != null) {
					return -1;
				} else if (o1.getLastName() != null && o2.getLastName() == null) {
					return 1;
				} else if (o1.getLastName() != null && o2.getLastName() != null) {
					return o1.getLastName().compareTo(o2.getLastName());
				} else {
					return 0;
				}
			} else {
				return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
			}
		}
	}
}
