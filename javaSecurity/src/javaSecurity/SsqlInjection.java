package javaSecurity;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Properties;

public class SsqlInjection {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Properties prop = new Properties();
			FileInputStream fi = new FileInputStream("jdbc.properties");
			prop.load(fi);
			String tableName = prop.getProperty("jdbc.tableName");
			String name = prop.getProperty("jdbc.name");
			String query = "select * from "+tableName+" where name=?";
			
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"),prop.getProperty("passwd"));
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("id"));
				System.out.print(":");
				System.out.println(rs.getString("pass"));
				
			}
			rs.close(); pstmt.close(); conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
