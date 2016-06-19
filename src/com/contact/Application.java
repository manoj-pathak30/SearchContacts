package com.contact;

/**
 * Application entryPoint to execute contactService.
 * @author manojP
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
					contactService.addContact(name.trim());

				} else if (choose.equals("2")) {
					System.out.print("Enter name: ");
					String searchStr = input.readLine();
					contactService.searchContact(searchStr.trim().toLowerCase());

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
}
