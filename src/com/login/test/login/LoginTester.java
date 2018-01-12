package com.login.test.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginTester {

	public static void main(String[] args) {

		boolean loginValid = isLoginValid("Amogh", "Davidson");
		if (loginValid) {
			System.out.println("Username and password match");
		} else {
			System.out.println("Username and password do not match");
		}

	}

	private static boolean isLoginValid(String userName, String pwd) {
		/*
		 * Steps: 1. Get JDBC connection 2. Run query to test username and
		 * password 3. IF there is record that pass. If no, fails
		 */

		boolean loginValid = false;

		Connection con = null;
		Statement stmt;
		ResultSet rs;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "amogh09");

			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"'Select * from usersname where userId = '" + userName + "' and password = '" + pwd + "'");
			


			if (rs.next()) {
				loginValid = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Couldn't close jdbc connection");
				e.printStackTrace();
			} finally {
				System.out.println("");
			}
		}

		return loginValid;

	}

}
