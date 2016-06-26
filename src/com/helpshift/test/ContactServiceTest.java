package com.helpshift.test;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.helpshift.Contact;
import com.helpshift.ContactService;


public class ContactServiceTest {

	@Test
	public void add() throws Exception {
		ContactService contactService = new ContactService();
		contactService.addContact("Manoj");
		contactService.addContact("Manoj Pathak");
		contactService.addContact("Chris");
		contactService.addContact("Chris Harris");
		contactService.addContact("Ayansh Pathak");
		
		Assert.assertTrue(contactService.getContactLookup().size() == 5);
		Assert.assertTrue(((Set<Contact>) contactService.getContactLookup().get('m')).size() == 2);
		Assert.assertTrue(((Set<Contact>) contactService.getContactLookup().get('p')).size() == 2);
	}

	@Test
	public void searchContacts() throws Exception {
		ContactService contactService = new ContactService();
		contactService.addContact("Manoj");
		contactService.addContact("Manoj Pathak");
		contactService.addContact("Ayansh Pathak");


		Set<Contact> result = contactService.searchContact("ma");
		Assert.assertTrue(result.size() == 2);
		
		Iterator<Contact> it = result.iterator();
		Assert.assertTrue(it.next().toString().equals("Manoj"));
		
		Assert.assertTrue(it.next().toString().equals("Manoj Pathak"));
	}

}
