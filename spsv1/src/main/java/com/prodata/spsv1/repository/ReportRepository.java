package com.prodata.spsv1.repository;

import java.util.List;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.ProductivityReport;




public interface ReportRepository {
	
	public List<ProductivityReport> getReport(String sdate,String edate,String employee);
	//public List<ProductivityReport> getReport(String sdate,String edate, User u);

	List<Cheque> getPolicyRelatedUser(String policyValue);
	Cheque samplingMis();

	public List<Cheque> getCancelRemarks();
	public int insertCancelRemarkData(String subtype, String remark);
	public int updateCancelRemarkData(String subtype, String remark, String id1);
	public int deleteCancelRemarkData(String id1);

}
