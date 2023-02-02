package com.prodata.spsv1.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Repatmodel;
import com.prodata.spsv1.repository.RepatletterRepo;

@Repository
public class RepatRepoimpl implements RepatletterRepo {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean uplsamplingfile(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_repatsampling](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean upltotfile(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_repattot](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean uplpayoutcollection(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_samplingpayout](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
