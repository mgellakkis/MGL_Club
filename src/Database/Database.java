package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.Database;

public class Database {

	/**
	 *  This class is using a Singleton pattern.
	 *  We do this so that our entire application
	 *  shares only one connection
	 *  We will do this though the use of a private constructor
	 *  and a static instance variable.
	 *  We will also have a static method getInstance
	 *  This method will create/return one instance 
	 *  of the database class
	 */
	//Create an instance variable
	private static Database instance = null;
	private static Connection connection = null;
	
	public static final String CREATE_TABLE_MEMBERS = 
			"CREATE TABLE "  + Const.TABLE_MEMBERS  + " (" + 
			Const.MEMBERS_COLUMN_MEMBER_ID + " VARCHAR(3) NOT NULL AUTO_INCREMENT, " +
			Const.MEMBERS_COLUMN_FNAME + " VARCHAR(50), " + 
			Const.MEMBERS_COLUMN_LNAME + " VARCHAR(50), " + 
			Const.MEMBERS_COLUMN_GENDER + " VARCHAR(1), " + 
			Const.MEMBERS_COLUMN_PHONE + " VARCHAR(50), " + 
			"PRIMARY KEY(" + Const.MEMBERS_COLUMN_MEMBER_ID  + "));";
	
	public static final String CREATE_TABLE_MEMBERSHIPS = 
			"CREATE TABLE "  + Const.TABLE_MEMBERSHIPS  + " (" + 
			Const.MEMBERSHIPS_COLUMN_ID + " VARCHAR(3) NOT NULL AUTO_INCREMENT, " +
			Const.MEMBERSHIPS_COLUMN_MEMBER_ID + " VARCHAR(3) NOT NULL, " + 
			Const.MEMBERSHIPS_COLUMN_TYPE + " VARCHAR(50), " + 
			"PRIMARY KEY(" + Const.MEMBERSHIPS_COLUMN_ID  + "));";
	
	
	//Create a private constructor
	private Database() {
		if(connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = 
					DriverManager.getConnection( Const.url
							+ Const.DB_NAME + "?useSSL=false", 
							Const.DB_USER, Const.DB_PASS);
				System.out.println("Successfully Created Connection");
			}catch(Exception e) {
				e.printStackTrace();
			}
			//Create the tables in the database
			createTable(Const.TABLE_MEMBERS, CREATE_TABLE_MEMBERS, connection);
			createTable(Const.TABLE_MEMBERSHIPS, CREATE_TABLE_MEMBERSHIPS, connection);
		}
	}
	
	//Create a getInstance method
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	//Create a close connection method
	
	public void close() {
		System.out.println("Closing connection");
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param tableName should be the name of the table you want to create
	 * @param tableQuery should be the create statement for that table
	 * @param connection should be the connection to the database
	 */
	public static void createTable(String tableName, String tableQuery,
			Connection connection) {
		/**
		 * Create a statement
		 * Statements are used to execute queries on the database
		 */
		Statement createTables;
		//Check if the table exists
		try {
			//Grab the metadata from the database
			DatabaseMetaData md = connection.getMetaData();
			//filter the metadata to show if the table in question exists
			ResultSet result = md.getTables(null,null,tableName,null);
			if(result.next()) {
				System.out.println(tableName + " Table already exists");
			}
			else {
				//if the table does not exist create and run
				//an SQL statement that creates the table
				createTables = connection.createStatement();
				createTables.execute(tableQuery);
				System.out.println("The " + tableName +
						" table has been created");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Connection getConnection() {
		return connection;
	}
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		instance = null;
	}
}
