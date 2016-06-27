package com.helpshift;

/**
 * Application entryPoint to execute contactService.
 * @author manojP
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.helpshift.domain.Contact;
import com.helpshift.service.ContactService;

public class Application {
	
	public static void main(String[] args) {

		ContactService contactService = new ContactService();

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String choose = "";
		while (true) {

			System.out.println("\n 1) Add contact 2) Search 3) Exit");
			try {
				choose = input.readLine();

				if (choose.equals("1")) {
					System.out.print("Enter name: ");
					String name = input.readLine();
					if(isValidName(name.trim()))
						contactService.addContact(name.trim());
					else
						System.out.println("Please enter valid name.");
					
				} else if (choose.equals("2")) {
					System.out.print("Enter name: ");
					String searchStr = input.readLine();

					List<Contact> result = contactService.searchContacts(searchStr.trim().toLowerCase());
					if (!result.isEmpty()) {
						for (Contact contact : result) {
							System.out.println(contact.getName());
						}
					} else
						System.out.println("No contact found!");

				} else if (choose.equals("3")) {
					System.out.println("Happy Searching...");
					System.exit(0);
				} else {
					System.out.println("Invalid input...");
				}
			} catch (Exception e) {
				System.err.println("Exception occured! " + e.getMessage());
			}
		}
	}
	
	/**
	 * API to validate contact name
	 * @param name
	 * @return true|false
	 */
	public static boolean isValidName(String name) {
		
		if (name.length() > 50 || (name.contains(" ") && name.split("\\s+").length > 2))
			return false;
		else
			return true;
	}
}
