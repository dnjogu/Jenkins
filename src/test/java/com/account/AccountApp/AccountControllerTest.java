package com.account.AccountApp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.account.connector.AccountsConnector;
import com.account.model.Account;
import com.account.util.FileUtil;
import com.account.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(AccountControllerTest.class)
public class AccountControllerTest {
    @Autowired
    private WebApplicationContext context;
    protected MockMvc mockMvc;
    @InjectMocks
    AccountsConnector mock = org.mockito.Mockito.mock(AccountsConnector.class);
    
    @Before
    public void setUp() throws Exception {
    	//AccountConnector mock = org.mockito.Mockito.mock(AccountConnector.class);
    	mock.setJsUtil(new JsonUtil("accountTest.json"));
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
		FileUtil fileUtil = new FileUtil("accountTest.json");
		String jsonData = fileUtil.getJsonContentFromFile();
		AccountsConnector acctConnector = new AccountsConnector("accountTest.json");
		List<Account> result = acctConnector.getAllAccounts();
		TypeReference<List<Account>> mapType = new TypeReference<List<Account>>() {};
		ObjectMapper objectMapper = new ObjectMapper();
    	List<Account> jsonToAccountList = objectMapper.readValue(jsonData, mapType);
    	when(mock.getAllAccounts()).thenReturn(jsonToAccountList);
    }

    
    @Test
    public void testgoToAllAccount() throws Exception{
    	this.mockMvc.perform(get("/accounts")
           .contentType(MediaType.APPLICATION_JSON))		
           .andExpect(status().isOk())
           .andExpect(content().string("[{\"id\":1,\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"},{\"id\":2,\"firstName\":\"Jane\",\"secondName\":\"Doe\",\"accountNumber\":\"1235\"},{\"id\":3,\"firstName\":\"Jim\",\"secondName\":\"Taylor\",\"accountNumber\":\"1236\"},{\"id\":5,\"firstName\":\"Jhonna\",\"secondName\":\"Doe\",\"accountNumber\":\"234567\"}]"));

    }
}
