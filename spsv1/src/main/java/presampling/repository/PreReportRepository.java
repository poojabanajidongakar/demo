package presampling.repository;

import java.util.List;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.ProductivityReport;




public interface PreReportRepository {
	
	public List<ProductivityReport> getReport(String sdate,String edate,String employee);
	//public List<ProductivityReport> getReport(String sdate,String edate, User u);

	List<Cheque> getPolicyRelatedUser(String policyValue);
	Cheque samplingMis();

}
