package com.ConvertToCsv.csvHelper;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DatabaseConnection {

	public static ResultSet getDBConnection() throws ClassNotFoundException, SQLException {
		// Database Connectivity
		ResourceBundle rd = ResourceBundle.getBundle("application");
		String driver = rd.getString("driver");

		// url of the database
		String DB_URL = rd.getString("DB_URL");

		// username to connect db
		String USER = rd.getString("USER");

		// password to connect db
		String PASS = rd.getString("PASS");
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		if (conn != null) {
			System.out.println("database Connected successfully");
		}
		String query = "select *, "
				+ "cast(round(COALESCE(clicks / NULLIF(impression,0), 0),2) as numeric(36,4)) as CTR, "
				+ "cast(round(COALESCE(spend / NULLIF(clicks,0), 0),2) as numeric(36,2)) as CPC, "
				+ "cast(round(COALESCE((spend*1000) / NULLIF(impression,0), 0),2) as numeric(36,2)) as CPM, "
				+ "cast(round(COALESCE(orders / NULLIF(visits,0), 0),2) as numeric(36,2)) as Conversion, "
				+ "cast(round(COALESCE(orders / NULLIF(visits,0), 0),2) as numeric(36,4))*100 as [Conversion Rate], "
				+ "COALESCE(spend / NULLIF(cast(round(COALESCE(orders / NULLIF(visits,0), 0),2) as numeric(36,2)), 0), 0) as CPA, "
				+ "CASE WHEN Platform = 'DV360' THEN cast(round(COALESCE([Video played to 25%] / NULLIF(impression,0), 0),2) as numeric(36,2)) "
				+ "ELSE cast(round(COALESCE(views / NULLIF(impression,0), 0),2) as numeric(36,2)) END as [VR], "
				+ "cast(round(COALESCE(spend / NULLIF(views,0), 0),2) as numeric(36,2)) as CPV, "
				+ "cast(round(COALESCE(spend / NULLIF(visits,0), 0),2) as numeric(36,2)) as [Cost per Visit], "
				+ "cast(round(COALESCE(bounces / NULLIF(visits,0), 0),2) as numeric(36,2)) as [Bounce Rate], "
				+ "cast(round(COALESCE([Marketing Visits] / NULLIF(visits,0), 0),2) as numeric(36,2))*100 as [Marketing Visits Rate%], "
				+ "cast(round(COALESCE([Sales Engagement] / NULLIF([Marketing Visits],0), 0),2) as numeric(36,2))*100 as [Sale Engagement Rate%], "
				+ "cast(round(COALESCE(ATC / NULLIF([Marketing Visits],0), 0),2) as numeric(36,2))*100 as [Cart Add (event) Rate%], "
				+ "cast(round(COALESCE(orders / NULLIF(ATC,0), 0),2) as numeric(36,2))*100 as [Order Rate%] "
				+ "from V_TH_SAMSUNG_AD_DETAIL";

		ResultSet result = null;

		try {

			// creating the statement
			Statement stmt = conn.createStatement();

			if (!query.isEmpty()) {

				// injecting the query
				result = stmt.executeQuery(query);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

}
