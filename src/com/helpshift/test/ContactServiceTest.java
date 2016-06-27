package com.helpshift.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.helpshift.domain.Contact;
import com.helpshift.service.ContactService;


public class ContactServiceTest {

	@Test
	public void addContactTest() throws Exception {
		ContactService contactService = new ContactService();
		contactService.addContact("Ayansh Pathak");
		contactService.addContact("Manoj");
		contactService.addContact("Manoj Pathak");
		contactService.addContact("Chris Harris");
		contactService.addContact("Chris");
		
		Assert.assertTrue(contactService.getContactLookup().size() == 5);
		Assert.assertTrue(((List<Contact>) contactService.getContactLookup().get('m')).size() == 2);
		Assert.assertTrue(((List<Contact>) contactService.getContactLookup().get('p')).size() == 2);
	}
	
	@Test
	public void searchExactContactsTest() throws Exception {
		ContactService contactService = new ContactService();
		contactService.addContact("Ayansh Pathak");
		contactService.addContact("Manoj");
		contactService.addContact("Manoj Pathak");
		contactService.addContact("Chris Harris");
		contactService.addContact("Chris");
		

		List<Contact> result = contactService.searchContacts("chris");
		Assert.assertTrue(result.size() == 2);
		Assert.assertTrue(result.get(0).toString().equals("Chris"));	
		Assert.assertTrue(result.get(1).toString().equals("Chris Harris"));
	}

}

