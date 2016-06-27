package com.helpshift.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

/**
 * @author manojP
 */

public class ContainsStringSearchStrategy implements SearchStrategy {

	private List<Contact> result = new ArrayList<Contact>();

	private Map<Character, ContactList> contactLookup = null;

	public ContainsStringSearchStrategy(Map<Character, ContactList> contactLookup) {
		this.contactLookup = contactLookup;
	}

	@Override
	public List<Contact> search(String searchStr) {

		if (contactLookup.containsKey(searchStr.charAt(0))) {
			List<Contact> contacts = contactLookup.get(searchStr.charAt(0));
			if (contacts != null) {
				List<Contact> temp = new ArrayList<>(contacts);
				Collections.sort(temp);
				search(temp, searchStr);
				Collections.sort(result);
				temp.clear();
			}
		}

		return result;
	}

	private int match(String name, String searchStr) {
		return name.substring(0, Math.min(searchStr.length(), name.length())).compareToIgnoreCase(searchStr);
	}

	private void search(List<Contact> contacts, String searchStr) {
		if (contacts.isEmpty()) {
			return;
		}

		int length = contacts.size();
		int midIdx = length / 2;
		Contact contact = contacts.get(midIdx);
		String name = contact.getName();
		
		if(!name.toLowerCase().startsWith(searchStr)){
			name = contact.getLastName();
		}
		
		if (match(name, searchStr) > 0) {
			searchInLeftOfList(contacts.subList(0, midIdx), searchStr, midIdx);
		} else if (match(name, searchStr) < 0) {
			searchInRightOfList(contacts.subList(midIdx, length), searchStr, midIdx);
		} else if (match(name, searchStr) == 0) {
			result.add(contact);
			contacts.remove(contact);
			search(contacts, searchStr);
		}

	}

	private void searchInRightOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while (contacts.size() > 0) {
			search(contacts, searchStr);
		}
	}

	private void searchInLeftOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while (contacts.size() > 0) {
			search(contacts, searchStr);
		}
	}
}