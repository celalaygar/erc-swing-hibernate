package com.datasel.patient.connection.jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.datasel.patient.entity.Patient;

public class ORACLEConnection {
	private static Connection connection;
	private ArrayList<Patient> patients;

	public static Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}
		connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@172.17.2.36:1521:AVC", "avc237", "avctest");
		if (connection != null) {
			System.out.println("Connected to the database!");
		} else {
			System.out.println("Failed to make connection!");
		}

		return connection;
	}

	public static void closeConnection() throws SQLException {
		connection.close();
	}


}
