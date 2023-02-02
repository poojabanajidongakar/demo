package com.prodata.spsv1.repository;

import java.util.List;

import com.prodata.spsv1.model.Glenysfilemodel;
import com.prodata.spsv1.model.Holdfilemodel;

public interface HoldfileRepo {

	// =============================Daily File=========================
	boolean uplHoldFile(String string);

	boolean uplexpress_data(String string);

	boolean upldor_data(String string);

	// =============================Fixed File=========================
	boolean uplwrongcalllog(String string);

	boolean uplversion2(String string);

	boolean upllogCTSTFormat(String string);

	boolean uplGlenysquery(String string);

	boolean uplOwnership_and_department(String string);

	boolean uplOwnership_department(String string);

	// =============================Export File=========================

	List<Glenysfilemodel> exportGlenysfile();

	List<Holdfilemodel> exportholdfile();
	
	boolean exequery(String string);
}
