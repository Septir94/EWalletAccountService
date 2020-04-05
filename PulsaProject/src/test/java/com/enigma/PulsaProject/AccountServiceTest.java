package com.enigma.PulsaProject;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.enigma.PulsaProject.Dto.AccountDto;
import com.enigma.PulsaProject.Dto.AccountPostDto;
import com.enigma.PulsaProject.Dto.AccountPutDto;
import com.enigma.PulsaProject.Dto.AccountPutStatusDto;
import com.enigma.PulsaProject.Dto.PointDto;
import com.enigma.PulsaProject.Dto.TransferDto;
import com.enigma.PulsaProject.Entity.Account;
import com.enigma.PulsaProject.Exception.BadRequestException;
import com.enigma.PulsaProject.Exception.NotFoundException;
import com.enigma.PulsaProject.Repository.AccountRepository;
import com.enigma.PulsaProject.Service.AccountService;
import com.enigma.PulsaProject.Validation.Validation;

@RunWith(SpringRunner.class)
public class AccountServiceTest {
	@TestConfiguration
	static class TodoServiceContextConfiguration{
		@Bean
		public AccountService accountService() {
			return new AccountService();
		}
	}
	
	@Autowired
	private AccountService accountService;
	
	@MockBean
	private AccountRepository repo;
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	public void setUp() {
		Date date = new Date();
		Account account=new Account(1, "232344", "ujang", new BigDecimal(1000000), 0.0, Short.valueOf("1"), date);
		
		List<Account>list=new ArrayList<Account>();
		list.add(account);
		Mockito.when(repo.findByAccount("232344")).thenReturn(account);
		Mockito.when(repo.findByName("ujang")).thenReturn(list);
		Mockito.when(repo.findAll()).thenReturn(list);
		
	}
	
	@Test
	public void whenValidAccount_returnListOfAccountByAccountNumber() {
		AccountDto dto=accountService.findByAccount("232344");
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(dto);
		assertEquals(list.size(), 1);
	}
	@Test
	public void whenValidAccount_returnListOfAccountByName() {
		List<AccountDto> dto=accountService.findByName("ujang");
		
		assertEquals(dto.size(), 1);
	}
	
	@Test
	public void whenInsertAccount_returnAccountDto() throws BadRequestException {
		AccountDto dto=accountService.create(new AccountPostDto("222222", "saban", new BigDecimal(1000000)));
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(dto);
		assertEquals(list.size(),1);
	}
	
	@Test
	public void whenUpdateAccount_returnAccountDto() throws BadRequestException {
		AccountDto dto=accountService.update(new AccountPutDto("232344", new BigDecimal(1000000)));
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(dto);
		assertEquals(list.size(),1);
	}
	@Test
	public void whenUpdateStatus_returnAccountDto() throws BadRequestException {
		AccountDto dto=accountService.updateStatus(new AccountPutStatusDto("232344", Short.valueOf("1")));
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(dto);
		assertEquals(list.size(),1);
	}
	@Test
	public void whenGetAll_returnListAccountDTo()  {
		
		List<AccountDto> acc=accountService.findAll();
		assertEquals(acc.size(),1);
	}
	
	@Test
	public void whenCreateFalseName_throwExceptionBadRequest() throws BadRequestException {
		AccountPostDto dto=new AccountPostDto("123456", "apa", new BigDecimal(1000000));
		 expectedException.expect(BadRequestException.class);
	     expectedException.expectMessage("Name Mandatory");
	     accountService.create(dto);
	}
	@Test
	public void whenCreateFalseAccount_throwExceptionBadRequest() throws BadRequestException {
		AccountPostDto dto=new AccountPostDto("12345644f", "apasih", new BigDecimal(1000000));
		 expectedException.expect(BadRequestException.class);
	     expectedException.expectMessage("Name Mandatory");
	     accountService.create(dto);
	}
	@Test
	public void whenCreateBalanceUnder1Million_throwExceptionBadRequest() throws BadRequestException {
		AccountPostDto dto=new AccountPostDto("123456", "apasih", new BigDecimal(900000));
		 expectedException.expect(BadRequestException.class);
	     expectedException.expectMessage("Insufience Balance");
	     accountService.create(dto);
	}
	
	@Test
	public void whenFindByUnfoundAccount_throwExceptionNotFoundEXception()  {
		AccountPostDto dto=new AccountPostDto("123456", "apasih", new BigDecimal(900000));
		 expectedException.expect(NotFoundException.class);
	     expectedException.expectMessage("account gdgjgd is Not found");
	     accountService.findByAccount("gdgjgd");
	}
	
	@Test
	public void whenFindByUnfoundName_throwExceptionNotFoundEXception()  {
		AccountPostDto dto=new AccountPostDto("123456", "apasih", new BigDecimal(900000));
		 expectedException.expect(NotFoundException.class);
	     expectedException.expectMessage("Name AKU Is Not Found");
	     accountService.findByName("AKU");
	}
	@Test
	public void whenTransfer_thenReturnACcountDto() throws BadRequestException {
		TransferDto dto=new TransferDto("232344", "232344",new BigDecimal(1000000), "ini euy");
		AccountDto accDto=accountService.Transfer(dto);
		AccountDto acc=accountService.findByAccount("232344");
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(acc);
		assertEquals(list.size(),1);
	}
	
	@Test
	public void whenTransferPoint_thenReturnACcountDto() {
		PointDto point=new PointDto("232344");
		AccountDto accDto=accountService.Point(point);
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(accDto);
		assertEquals(list.size(),1);
	}
	@Test
	public void whenTransferPulsa_thenReturnACcountDto() throws BadRequestException {
		AccountPutDto put=new AccountPutDto("232344", new BigDecimal(1000000));
		AccountDto accDto=accountService.pulsa(put);
		List<AccountDto>list=new ArrayList<AccountDto>();
		list.add(accDto);
		assertEquals(list.size(),1);
	}
	
}

