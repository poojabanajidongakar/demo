package com.prodata.spsv1.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;

@Repository
public interface TaskRepository {
	
	int assignTask(String employee,String assignee,int task,String paymentmode,String OneRsType);

	int deleteupload();
	
	//int assignTask(String employee, String assignee, int task, String paymentmode, String OneRsType, String amtrange, double amount);

}
