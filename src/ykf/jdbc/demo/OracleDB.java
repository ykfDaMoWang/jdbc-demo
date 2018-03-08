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

public class OracleDB {
	public static void main(String[] args) throws IOException {
		//��ȡproperties
		InputStream in = new BufferedInputStream(new FileInputStream("src/dbconfig.properties")); 
		ResourceBundle rb = new PropertyResourceBundle(in) ;
		String driverName = rb.getString("oracle.driverName");
		String dbURL = rb.getString("oracle.dbURL");
		String userName = rb.getString("oracle.userName");
		String userPwd = rb.getString("oracle.userPwd");
		Connection dbConn = null;
		ResultSet rs=null;
		Statement stmt=null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!"); // ������ӳɹ�
			stmt= dbConn.createStatement();  
            String sql1 = "select * from usertest";
            rs= stmt.executeQuery(sql1);
            while(rs.next()){
            	System.out.println(rs.getString(1)+"=="+rs.getString(2));
            }
			} catch (Exception e) {
			e.printStackTrace();
			}finally{
			//������Դ���ͷ�
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
