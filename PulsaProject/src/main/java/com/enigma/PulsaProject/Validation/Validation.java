package com.enigma.PulsaProject.Validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;


public class Validation {
	public boolean postValidation(String name,String account) {
		if(name.length()>=5 && account.matches("[0-9]*") && account.length()<=6) {
			return true;
		}else return false;
	}
	public boolean balanceValidation(BigDecimal balance) {
		BigDecimal compare=new BigDecimal(1000000);
		if(balance.compareTo(compare)>=0) {
			return false;
		}else return true;
	}
	
	
}
