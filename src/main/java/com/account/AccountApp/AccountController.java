package com.account.AccountApp;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.account.connector.AccountsConnector;
import com.account.model.Account;



@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class AccountController {
	public AccountsConnector ac = new AccountsConnector("account.json"); 
	public AccountsConnector getAc() {
		return ac;
	}

	public void setAc(AccountsConnector ac) {
		this.ac = ac;
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	List<Account> getAllAccount() throws IOException {
		//ac.setJsUtil(new JsonUtil("account.json"));
		return ac.getAllAccounts();
	}
	
	@RequestMapping(value = "/add-account", method = RequestMethod.POST)
	String addAccount(@Valid @RequestBody Account account) throws IOException {

		//ac.setJsUtil(new JsonUtil("account.json"));
		return ac.addAccount(account);
	}
	
	@RequestMapping(value = "/delete-account/{id}", method = RequestMethod.GET)
	String removeAccount(@PathVariable(value = "id") int id) throws IOException {

		//ac.setJsUtil(new JsonUtil("account.json"));
		return ac.deleteAccount(id);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AccountController.class, args);
	}
}
