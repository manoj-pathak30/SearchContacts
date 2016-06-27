package com.helpshift.domain;

import java.util.ArrayList;

public class ContactList extends ArrayList<Contact> {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(Contact contact) {
		return contains(contact) ? false : super.add(contact);
	}

}
