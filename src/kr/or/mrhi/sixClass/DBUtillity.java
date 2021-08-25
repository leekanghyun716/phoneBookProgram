package kr.or.mrhi.sixClass;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtillity {
	public static Connection getConnection() {
		Connection con = null;
		// collection framwork Properties 사용해서 드라이버 URL등을 가져온다.
		try {

			Properties properties = new Properties();
			String filePath = DataBaseTest.class.getResource("db.properties").getPath();
			
			filePath = URLDecoder.decode(filePath, "utf-8");
			properties.load(new FileReader(filePath));

			String driver = properties.getProperty("DRIVER");
			String url = properties.getProperty("URL");
			String userId = properties.getProperty("USER_ID");
			String userPassword = properties.getProperty("USER_PASSWORD");

			// 1.jdbc Driver loader
			Class.forName(driver);
			// 2.MySql Database connection
			con = DriverManager.getConnection(url, userId, userPassword);
		} catch (Exception e1) {
			System.out.println("mysql database connetion fail");
			e1.printStackTrace();
		}

		

		
		System.out.println("database connection success");

		return con;
	}

	

}
