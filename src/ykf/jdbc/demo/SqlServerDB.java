package ykf.jdbc.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SqlServerDB {
	public static void main(String[] args) throws IOException {
		//读取properties
		InputStream in = new BufferedInputStream(new FileInputStream("src/dbconfig.properties")); 
		ResourceBundle rb = new PropertyResourceBundle(in) ;
		String driverName = rb.getString("sqlservel.driverName");
		String dbURL = rb.getString("sqlservel.dbURL");
		String userName = rb.getString("sqlservel.userName");
		String userPwd = rb.getString("sqlservel.userPwd");
		Connection dbConn = null;
		ResultSet rs=null;
		Statement stmt=null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!"); // 如果连接成功
			stmt= dbConn.createStatement();  
            String sql1 = "select * from dbo.ceshi";
            rs= stmt.executeQuery(sql1);
            while(rs.next()){
            	System.out.println(rs.getString(1));
            }
			} catch (Exception e) {
			e.printStackTrace();
			}finally{
			//进行资源的释放
			if(dbConn!=null){
			try {
			in.close();
			rs.close();
			stmt.close();
			dbConn.close();
			} catch (SQLException e) {
			e.printStackTrace();
		 }
		}
	  }
	}
}
