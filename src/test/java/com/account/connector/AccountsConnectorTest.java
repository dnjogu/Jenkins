package com.account.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.account.model.Account;
import com.account.util.FileUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;





@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class AccountsConnectorTest {
	String deleteFile = "{\"Reason\":\"File Could not delete\"}";
	String addSuucess ="{\"Reason\":\"Successfully added account\"}";
	String removeSuucess ="{\"Reason\":\"Successfully removed account\"}";
	String removeFail ="{\"Reason\":\"Account Not found\"}";
	
	@Test
	public void getAllAccountsSuccess() throws IOException {
		FileUtil fileUtil = new FileUtil("accountTest.json");
		String jsonData = fileUtil.getJsonContentFromFile();
		AccountsConnector acctConnector = new AccountsConnector("accountTest.json");
		List<Account> result = acctConnector.getAllAccounts();
    	ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		TypeReference<List<Account>> mapType = new TypeReference<List<Account>>() {};
    	List<Account> jsonToAccountList = objectMapper.readValue(jsonData, mapType);
    	Account acct = jsonToAccountList.get(0);
    	assertEquals(jsonToAccountList.size(), result.size());
    	assertEquals(acct.getId(),1);
    	assertEquals(acct.getFirstName(),"John");
	}
	
	@Test
	public void getAllAccountsFailed() throws IOException {
		AccountsConnector acctConnector = new AccountsConnector("accountTest1.json");
		try {
			List<Account> result = acctConnector.getAllAccounts();
			fail("File Not Found Exception");
		} catch (FileNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Caught Other Exception");
		}
	}

	@Test
	public void getAddAccountsSuccess() throws IOException	{
		AccountsConnector acctConnector = new AccountsConnector("accountTest.json");
		Account acct = new Account();
		acct.setId(111);acct.setFirstName("FirstName");acct.setSecondName("LastName");acct.setAccountNumber("0000");
		
		String result = acctConnector.addAccount(acct);
		assertEquals(result,addSuucess);
	}
	@Test
	public void getAddAccountsFailed() throws IOException {
		AccountsConnector acctConnector = new AccountsConnector("accountTest1.json");
		try {
			Account acct = new Account();
			acct.setId(111);acct.setFirstName("FirstName");acct.setSecondName("LastName");acct.setAccountNumber("0000");
			String result = acctConnector.addAccount(acct);
			fail("File Not Found Exception");
		} catch (FileNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Caught Other Exception");
		}
	}
	
	@Test
	public void deleteAccountsSuccess() throws IOException{
		AccountsConnector acctConnector = new AccountsConnector("accountTest.json");
		Account acct = new Account();
		acct.setId(111);acct.setFirstName("FirstName");acct.setSecondName("LastName");acct.setAccountNumber("0000");
		String added = acctConnector.addAccount(acct);
		String result = acctConnector.deleteAccount(111);
		assertEquals(result,removeSuucess);
	}
	
	@Test
	public void deleteAccountsAccountNOTFound() throws IOException{
		AccountsConnector acctConnector = new AccountsConnector("accountTest.json");
		Account acct = new Account();
		acct.setId(111);acct.setFirstName("FirstName");acct.setSecondName("LastName");acct.setAccountNumber("0000");
		String added = acctConnector.addAccount(acct);
		String result = acctConnector.deleteAccount(1234);
		assertEquals(result,removeFail);
	}
	
	@Test
	public void deleteAccountsFailed() throws IOException{
		AccountsConnector acctConnector = new AccountsConnector("accountTest1.json");
		try {
			Account acct = new Account();
			acct.setId(111);acct.setFirstName("FirstName");acct.setSecondName("LastName");acct.setAccountNumber("0000");
			String added = acctConnector.addAccount(acct);
			String result = acctConnector.deleteAccount(111);
			fail("File Not Found Exception");
		} catch (FileNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Caught Other Exception");
		}			
	}


}
