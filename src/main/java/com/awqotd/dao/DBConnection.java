package com.awqotd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
	public static Connection getConnection()
	{
		Connection connection = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qotd","adminWrhucm3","qvC2LFh6la5a");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) throws SQLException 
	{
		Connection conn = DBConnection.getConnection();
		System.out.println(conn.isClosed());
		conn.close();
		System.out.println(conn.isClosed());
	}
}
