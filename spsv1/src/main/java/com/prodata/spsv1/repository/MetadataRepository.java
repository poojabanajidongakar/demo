package com.prodata.spsv1.repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface MetadataRepository {

//Map<String, Object> mandateDbToExcel();

Map<String,Object> prediscreDbToExcel(String sdate, String edate);
Map<String, Object> mandateDbToExcel(String sdate, String edate);
Map<String, Object> dbToExcel(String sdate, String edate);
Map<String, Object> discrepancyUpdateExcel(String sdate, String edate);



}
