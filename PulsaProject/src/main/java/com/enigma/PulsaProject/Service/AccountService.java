package com.enigma.PulsaProject.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.PulsaProject.Dto.AccountDto;
import com.enigma.PulsaProject.Dto.AccountPostDto;
import com.enigma.PulsaProject.Dto.AccountPutDto;
import com.enigma.PulsaProject.Dto.AccountPutStatusDto;
import com.enigma.PulsaProject.Dto.PointDto;
import com.enigma.PulsaProject.Dto.ResponseTransaction;
import com.enigma.PulsaProject.Dto.TransferDto;
import com.enigma.PulsaProject.Entity.Account;
import com.enigma.PulsaProject.Exception.BadRequestException;
import com.enigma.PulsaProject.Exception.NotFoundException;
import com.enigma.PulsaProject.Repository.AccountRepository;
import com.enigma.PulsaProject.Validation.Validation;

@Service
public class AccountService {
	@Autowired
	AccountRepository repo;
	
	
	Date date=new Date();
	public List<AccountDto> findAll(){
		List<AccountDto> list=new ArrayList<AccountDto>();
		repo.findAll().forEach(account ->{
			ModelMapper model=new ModelMapper();
			AccountDto dto=model.map(account, AccountDto.class);
			list.add(dto);
		});
		return list;
	}
	
	public AccountDto create (AccountPostDto dto) throws BadRequestException{
		Validation valid=new Validation();
		if(!valid.postValidation(dto.getName(), dto.getAccount())) {
			throw new BadRequestException("Name Mandatory");
		}else if(valid.balanceValidation(dto.getBalance())) {
			throw new BadRequestException("Insufience Balance");
		}else {
		repo.save(new Account(null, dto.getAccount(), dto.getName(), dto.getBalance(),0.0, Short.valueOf("0"), date));
		ModelMapper model=new ModelMapper();
		AccountDto adto=model.map(new Account(null, dto.getAccount(), dto.getName(), dto.getBalance(),null, null,date),AccountDto.class);
		return adto;
		}
	}
	
	
	public AccountDto findByAccount(String account) {
		Account acc=repo.findByAccount(account);
		if(acc==null) {
			throw new NotFoundException("account "+account+" is Not found");
		}else {
			ModelMapper model=new ModelMapper();
			AccountDto dto=model.map(acc, AccountDto.class);
			return dto;
		}
	}
	public List<AccountDto> findByName(String name){
		List<AccountDto> list=new ArrayList<AccountDto>();
		List<Account> accounts=repo.findByName(name);
		if(accounts.isEmpty()) {
			throw new NotFoundException("Name "+name+" Is Not Found");
		}else {
			repo.findByName(name).forEach(acc -> {
				ModelMapper model=new ModelMapper();
				AccountDto dto=model.map(acc, AccountDto.class);
				list.add(dto);
			});
			return list;
		}
	}
	public AccountDto update(AccountPutDto dto) throws BadRequestException {
		Account acc=repo.findByAccount(dto.getAccount());
		if(acc==null)throw new NotFoundException("Account "+dto.getAccount()+ "is Not Found");
		else if(dto.getBalance().compareTo(new BigDecimal(10000))<0)throw new BadRequestException("Insufience Balance");
		else if(acc.getStatus()==0)throw new BadRequestException("Account is not active");
		else {
			acc.setBalance(acc.getBalance().add(dto.getBalance()));
			acc.setTransactionDate(date);
			repo.save(acc);
			ModelMapper model=new ModelMapper();
			AccountDto dt=model.map(acc, AccountDto.class);
			return dt;
		}
	}
	
	public AccountDto updateStatus(AccountPutStatusDto dto) throws BadRequestException {
		Account acc=repo.findByAccount(dto.getAccount());
		if(acc==null)throw new NotFoundException("Account "+dto.getAccount()+ "is Not Found");
		else if(dto.getStatus()!=1 || dto.getStatus()!=0)throw new BadRequestException("Status is not Define");
		else {
			acc.setStatus(dto.getStatus());
			acc.setTransactionDate(date);
			repo.save(acc);
			ModelMapper model=new ModelMapper();
			AccountDto dt=model.map(acc, AccountDto.class);
			return dt;
		}
	}
	
	public AccountDto Transfer(TransferDto dto) throws BadRequestException {
		Account acc1=repo.findByAccount(dto.getAccount());
		Account acc2=repo.findByAccount(dto.getTo());
		if(acc2==null) {
			throw new NotFoundException(dto.getTo()+" is not found");
		}else if(acc1.getStatus()==0|| acc2.getStatus()==0)throw new BadRequestException("Account is not active");
		else {
		acc1.setBalance(acc1.getBalance().subtract(dto.getAmount()));
		acc2.setBalance(acc2.getBalance().add(dto.getAmount()));
		repo.save(acc1);
		repo.save(acc2);
		}
		ModelMapper model=new ModelMapper();
		AccountDto dt=model.map(acc1, AccountDto.class);
		
		return dt;
		
	}
	
	public AccountDto Point(PointDto dto) {
		Account acc=repo.findByAccount(dto.getAccount());
		acc.setBalance(acc.getBalance().add(new BigDecimal(acc.getPoint())));
		acc.setPoint(0.0);
		repo.save(acc);
		ModelMapper model=new ModelMapper();
		AccountDto dt=model.map(acc, AccountDto.class);
		return dt;
	}
	
	public AccountDto pulsa(AccountPutDto dto) throws BadRequestException {
		Account acc=repo.findByAccount(dto.getAccount());
		if(acc.getStatus()==0) {
			throw new BadRequestException("Account is not active");
		}else {
		acc.setPoint(acc.getPoint()+getPoint(dto.getBalance()));
		acc.setBalance(acc.getBalance().subtract(dto.getBalance()));
		repo.save(acc);
		}
		ModelMapper model=new ModelMapper();
		AccountDto dt=model.map(acc, AccountDto.class);
		return dt;
		
	}
	public Double getPoint(BigDecimal balance) {
		Double point=0.0;
		if(balance.doubleValue()>=10000.0 ) {
			point+=0.1*balance.doubleValue();
		}
		
		if(point>10000.0) {
			point=10000.0;
		}
		return point;
	}
}
