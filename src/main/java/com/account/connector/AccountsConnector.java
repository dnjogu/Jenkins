package com.account.connector;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.account.model.Account;
import com.account.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class AccountsConnector {
	private JsonUtil jsUtil; 
	String deleteFile = "{\"Reason\":\"File Could not delete\"}";
	String addSuucess ="{\"Reason\":\"Successfully added account\"}";
	String removeSuucess ="{\"Reason\":\"Successfully removed account\"}";
	String removeFail ="{\"Reason\":\"Account Not found\"}";
	
	public AccountsConnector(String fileName) {
		// TODO Auto-generated constructor stub
		this.jsUtil= new JsonUtil(fileName);
	}

	public List<Account> getAllAccounts() throws IOException {
		String jsonData =jsUtil.getJsonString();

    	ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
		TypeReference<List<Account>> mapType = new TypeReference<List<Account>>() {};
		System.out.println("What is json data "+ jsonData );
    	List<Account> jsonToAccountList = objectMapper.readValue(jsonData, mapType);
    	
    	return jsonToAccountList;
	}
	
	public String addAccount(Account acct) throws IOException {
		String jsonData =jsUtil.getJsonString();

    	ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
		TypeReference<List<Account>> mapType = new TypeReference<List<Account>>() {};
		System.out.println("What is json data "+ jsonData );
    	List<Account> jsonToAccountList = objectMapper.readValue(jsonData, mapType);
    	jsonToAccountList.add(acct);
    	ObjectMapper mapper = new ObjectMapper();
    	//delete file now first
    	String filePath = jsUtil.deleteJsonFile();
    	String retValue = "";
    	if (filePath.equals("")) {
    		retValue = deleteFile;
    	} else {
    		mapper.writeValue(new File(filePath), jsonToAccountList);
    		retValue = addSuucess;
    	}    	
    	return retValue;
	}
	
	public String deleteAccount(int id) throws IOException {
		String jsonData =jsUtil.getJsonString();

    	ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
		TypeReference<List<Account>> mapType = new TypeReference<List<Account>>() {};
		System.out.println("What is json data "+ jsonData );
    	List<Account> jsonToAccountList = objectMapper.readValue(jsonData, mapType);
    	boolean isDeleted = false;
    	for (int i = jsonToAccountList.size()-1; i >= 0; i--) {
    		if (jsonToAccountList.get(i).getId()==id) {
    			isDeleted = true;
    			jsonToAccountList.remove(i);
    		}
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	//delete file now first
    	String filePath = jsUtil.deleteJsonFile();
    	String retValue = "";
    	if (filePath.equals("")) {
    		retValue = deleteFile;
    	} else {
    		mapper.writeValue(new File(filePath), jsonToAccountList);
    		retValue = (isDeleted)?removeSuucess:removeFail;
    	}    	
    	return retValue;

    	
	}

	public JsonUtil getJsUtil() {
		return jsUtil;
	}

	public void setJsUtil(JsonUtil jsUtil) {
		this.jsUtil = jsUtil;
	}

}
