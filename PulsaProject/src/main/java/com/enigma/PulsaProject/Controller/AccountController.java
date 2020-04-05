package com.enigma.PulsaProject.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.enigma.PulsaProject.Dto.AccountDto;
import com.enigma.PulsaProject.Dto.AccountPostDto;
import com.enigma.PulsaProject.Dto.AccountPutDto;
import com.enigma.PulsaProject.Dto.AccountPutStatusDto;
import com.enigma.PulsaProject.Dto.PointDto;
import com.enigma.PulsaProject.Dto.ResponseTransaction;
import com.enigma.PulsaProject.Dto.TransferDto;
import com.enigma.PulsaProject.Exception.BadRequestException;
import com.enigma.PulsaProject.Service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "E-Wallet")
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	AccountService service;
	
	
	@GetMapping("/accounts")
	@ApiOperation(value = "View all Account",response = AccountDto.class)
	public List<AccountDto> getAllList(){
		logger.info("Find All");
		return service.findAll();
	}
	
	@GetMapping("accounts/{account}")
	@ApiOperation(value = "View  Account By account number",response = AccountDto.class)
	public AccountDto getByAccount(@ApiParam(value = "Account numer") @PathVariable String account) {
		logger.info("find by account");
		return service.findByAccount(account);
	}
	
	@GetMapping("/accounts/search")
	@ApiOperation(value = "Search by name",response = AccountDto.class)
	public List<AccountDto> getByName(@ApiParam(value = "Account name") @RequestParam String name){
		logger.info("search by name");
		return service.findByName(name);
	}
	
	@PostMapping("/accounts")
	@ApiOperation(value = "Input data account",response = AccountDto.class)
	public ResponseEntity<AccountDto> save(@ApiParam(value = "Account ") @RequestBody AccountPostDto dto ) throws BadRequestException{
		return ResponseEntity.ok().body(service.create(dto));
		
	}
	@PutMapping("/accounts/balance")
	@ApiOperation(value = "Update Balance",response = AccountDto.class)
	public ResponseEntity<AccountDto> update(@ApiParam(value = "Account ") @RequestBody AccountPutDto dto)throws BadRequestException{
		logger.info("Update balance");
		return ResponseEntity.ok().body(service.update(dto));
	}
	@PutMapping("/accounts/status")
	@ApiOperation(value = "Update status",response = AccountDto.class)
	public ResponseEntity<AccountDto> updateStatus(@ApiParam(value = "Account ") @RequestBody AccountPutStatusDto dto) throws BadRequestException{
		logger.info("Update status");
		return ResponseEntity.ok().body(service.updateStatus(dto));
		
	}

	@PutMapping("/account/transfer")
	public ResponseEntity<AccountDto> transfer(@RequestBody TransferDto dto) throws BadRequestException{
		logger.info("Transfer Balance");
		return ResponseEntity.ok().body(service.Transfer(dto));
	}
	@PutMapping("/account/transfer/point")
	public ResponseEntity<AccountDto> point(@RequestBody PointDto dto){
		logger.info("Point to balance");
		logger.error("error here for update balance");
		return ResponseEntity.ok().body(service.Point(dto));
	}
	@PutMapping("/account/transfer/pulsa")
	public ResponseEntity<AccountDto> pulsa(@RequestBody AccountPutDto dto) throws BadRequestException{
		logger.info("Point to balance");
		logger.error("error here for update balance");
		return ResponseEntity.ok().body(service.pulsa(dto));
	}
}
