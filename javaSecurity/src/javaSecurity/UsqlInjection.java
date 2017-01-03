package javaSecurity;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class UsqlInjection {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Properties prop = new Properties();
			FileInputStream fi = new FileInputStream("jdbc.properties");
			prop.load(fi);
			String tableName = prop.getProperty("jdbc.tableName");
			String name = prop.getProperty("jdbc.name");
			String query = "select * from "+tableName+" where name='"+name+"'";
			
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"),prop.getProperty("passwd"));
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.print(rs.getString("id"));
				System.out.print(":");
				System.out.println(rs.getString("pass"));
				
			}
			rs.close(); stmt.close(); conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
