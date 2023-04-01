package database;


import model.Animal;
import model.Computer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	private Connection connection = null;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

//	public void deleteBranch(int branchId) {
//		try {
//			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
//			ps.setInt(1, branchId);
//
//			int rowCount = ps.executeUpdate();
//			if (rowCount == 0) {
//				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
//			}
//
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
//
//	public void insertBranch(BranchModel model) {
//		try {
//			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
//			ps.setInt(1, model.getId());
//			ps.setString(2, model.getName());
//			ps.setString(3, model.getAddress());
//			ps.setString(4, model.getCity());
//			if (model.getPhoneNumber() == 0) {
//				ps.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				ps.setInt(5, model.getPhoneNumber());
//			}
//
//			ps.executeUpdate();
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
//
	public Object[] getObjectInfo(String projection, String tables, String cond, Class<?> entityClass) {
		ArrayList<Object> result = new ArrayList<Object>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT ? FROM ? WHERE ?");
			ResultSet rs = ps.executeQuery();

			// get info on ResultSet
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()) {
				Object obj = entityClass.getDeclaredConstructor().newInstance();

				// check if each column exists in the ResultSet
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					String columnValue = rs.getString(columnName);

					try {
						Field field = entityClass.getDeclaredField(columnName);
						field.setAccessible(true);

						if (field.getType() == int.class) {
							field.setInt(obj, Integer.parseInt(columnValue));
						} else if (field.getType() == String.class) {
							field.set(obj, columnValue);
						}
					} catch (NoSuchFieldException e) {
						// ignore columns that don't exist in the object
					}
				}

				result.add(obj);
			}

			rs.close();
			ps.close();
		} catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Object[0]);
	}

	public Animal[] getAnimalInfo(ArrayList<String> columns) {
		ArrayList<Animal> result = new ArrayList<Animal>();

		String projection = String.join(", ", columns);

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT ? FROM COMPUTERS1 c1, COMPUTERS2 c2 WHERE c1.MODEL = c2.MODEL");
//			ps.setString(1, projection);
			ps.setString(1, "SELECT * FROM ANIMALS1 a1, ANIMALS2 a2 WHERE a1.SPECIES = a2.SPECIES");
			ResultSet rs = ps.executeQuery();

    		ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()) {
				String a_id = null;
				String p_id = null;
				String name = null;
				String species = null;
				String genus = null;

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					String columnValue = rs.getString(columnName);

					if ("a_id".equalsIgnoreCase(columnName)) {
						a_id = columnValue;
					} else if ("p_id".equalsIgnoreCase(columnName)) {
						p_id = columnValue;
					} else if ("name".equalsIgnoreCase(columnName)) {
						name = columnValue;
					} else if ("species".equalsIgnoreCase(columnName)) {
						species = columnValue;
					} else if ("genus".equalsIgnoreCase(columnName)) {
						genus = columnValue;
					}
				}


				Animal animal = new Animal(a_id, p_id, name, species, genus);
				result.add(animal);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Animal[0]);
	}

	public Computer[] getComputerInfo(ArrayList<String> columns) {
		ArrayList<Computer> result = new ArrayList<Computer>();

		StringBuilder builder = new StringBuilder();
		for (String value : columns) {
			builder.append(value);
		}
		String projection = builder.toString();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT ? FROM COMPUTERS1 c1, COMPUTERS2 c2 WHERE c1.MODEL = c2.MODEL");
//			ps.setString(1, projection);
			ps.setString(1, "C_ID, W_ID, c1.MODEL AS MODEL, MANUFACTURER, TYPE");
			ResultSet rs = ps.executeQuery();

    		// get info on ResultSet
    		ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()) {
				String c_id = null;
				String w_id = null;
				String model = null;
				String manufacturer = null;
				String type = null;

				// check if each column exists in the ResultSet
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					String columnValue = rs.getString(columnName);

					if ("c_id".equalsIgnoreCase(columnName)) {
						c_id = columnValue;
					} else if ("w_id".equalsIgnoreCase(columnName)) {
						w_id = columnValue;
					} else if ("model".equalsIgnoreCase(columnName)) {
						model = columnValue;
					} else if ("manufacturer".equalsIgnoreCase(columnName)) {
						manufacturer = columnValue;
					} else if ("type".equalsIgnoreCase(columnName)) {
						type = columnValue;
					}
				}

				Computer computer = new Computer(c_id, w_id, model, manufacturer, type);
				result.add(computer);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Computer[0]);
	}
//
//	public void updateBranch(int id, String name) {
//		try {
//		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
//		  ps.setString(1, name);
//		  ps.setInt(2, id);
//
//		  int rowCount = ps.executeUpdate();
//		  if (rowCount == 0) {
//		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
//		  }
//
//		  connection.commit();
//
//		  ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
//
//	public boolean login(String username, String password) {
//		try {
//			if (connection != null) {
//				connection.close();
//			}
//
//			connection = DriverManager.getConnection(ORACLE_URL, username, password);
//			connection.setAutoCommit(false);
//
//			System.out.println("\nConnected to Oracle!");
//			return true;
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			return false;
//		}
//	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
//	public void databaseSetup() {
//		dropBranchTableIfExists();
//
//		try {
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//		insertBranch(branch1);
//
//		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//		insertBranch(branch2);
//	}
//
//	private void dropBranchTableIfExists() {
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("select table_name from user_tables");
//
//			while(rs.next()) {
//				if(rs.getString(1).toLowerCase().equals("branch")) {
//					stmt.execute("DROP TABLE branch");
//					break;
//				}
//			}
//
//			rs.close();
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//	}
}
