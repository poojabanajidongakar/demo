package com.prodata.spsv1.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;


@Repository
public interface SpoolRepository 
{
	Cheque findById(String prnno,User u);
	List<Cheque> sortAmt(String paymode,String amt,User u);
	int countToDoListpaymode(User u, String paymode, String amt);
	boolean checkAmountRange(double amount,String amountRange);
}
