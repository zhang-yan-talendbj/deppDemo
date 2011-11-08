<<<<<<< HEAD
package com.caribe.stone.db.utils;

import java.io.FileOutputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.db2.Db2Connection;

public class DBUnitHelper {

	public static void main(String[] args) throws Exception {
		Db2Connection cc = new Db2Connection(DBUtils.getDB2Connection(), "PRECLAIM");
		IDataSet fullDataSet = cc.createDataSet();
		XlsDataSet.write(fullDataSet, new FileOutputStream("test.xls"));
	}
}
=======
package com.caribe.stone.db.utils;

import java.io.FileOutputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.db2.Db2Connection;

public class DBUnitHelper {

	public static void main(String[] args) throws Exception {
		Db2Connection cc = new Db2Connection(DBUtils.getDB2Connection(), "PRECLAIM");
		IDataSet fullDataSet = cc.createDataSet();
		XlsDataSet.write(fullDataSet, new FileOutputStream("test.xls"));
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
