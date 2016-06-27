package com.helpshift.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

public class ExactStringSearchStrategy implements SearchStrategy {

	List<Contact> result = new ArrayList<Contact>();
	Map<Character, ContactList> contactLookup = null;

	public ExactStringSearchStrategy(Map<Character, ContactList> contactLookup) {
		this.contactLookup = contactLookup;
	}

	@Override
	public List<Contact> search(String searchStr) {

		if (contactLookup.containsKey(searchStr.charAt(0))) {
			List<Contact> contacts = contactLookup.get(searchStr.charAt(0));
			if (contacts != null) {
				List<Contact> temp = new ArrayList<>(contacts);
				System.out.println(temp);
				Collections.sort(temp);
				System.out.println("after " + temp);
				search(temp, searchStr);
				Collections.sort(result);
				temp.clear();
			}
		}

		return result;
	}

	private int match(String name, String searchStr) {
		return name.compareToIgnoreCase(searchStr);
	}

	private void search(List<Contact> contacts, String searchStr) {
		if (contacts.isEmpty()) {
			return;
		}
		int length = contacts.size();
		int midIdx = length / 2;
		Contact contact = contacts.get(midIdx);
		if (match(contact.getFirstName(), searchStr) > 0) {
			searchInLeftOfList(contacts.subList(0, midIdx), searchStr, midIdx);
		} else if (match(contact.getFirstName(), searchStr) < 0) {
			searchInRightOfList(contacts.subList(midIdx, length), searchStr, midIdx);
		} else if (match(contact.getFirstName(), searchStr) == 0) {
			result.add(contact);
			contacts.remove(contact);
			search(contacts, searchStr);
		}
	}

	private void searchInRightOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while (contacts.size() > 2) {
			search(contacts, searchStr);
		}
	}

	private void searchInLeftOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while (contacts.size() > 2) {
			search(contacts, searchStr);
		}
	}

}