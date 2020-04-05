package com.enigma.PulsaProject;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.enigma.PulsaProject.Controller.AccountController;
import com.enigma.PulsaProject.Repository.AccountRepository;
import com.enigma.PulsaProject.Service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class ControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private AccountService service;
	@MockBean
	private AccountRepository repo;
}
