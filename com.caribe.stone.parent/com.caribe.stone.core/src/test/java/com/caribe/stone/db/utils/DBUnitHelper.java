package com.caribe.stone.db.utils;

import java.io.FileOutputStream;
import java.sql.Connection;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.db2.Db2Connection;

public class DBUnitHelper {

	public static void main(String[] args) throws Exception {
		Connection db2Connection = DBUtils.getDB2Connection();
		System.out.println(db2Connection);
		if(true){
			return ;
		}
		Db2Connection cc = new Db2Connection(db2Connection, "CLAIMS");
		IDataSet fullDataSet = cc.createDataSet();
		XlsDataSet.write(fullDataSet, new FileOutputStream("test.xls"));
	}
}
