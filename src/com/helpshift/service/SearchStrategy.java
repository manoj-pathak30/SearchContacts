package com.helpshift.service;

import java.util.List;

import com.helpshift.domain.Contact;

public interface SearchStrategy {

	List<Contact> search(String searchStr);

}