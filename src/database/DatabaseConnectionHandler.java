package database;

import exceptions.NotExists;

import model.*;
import util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class handles all database related transactions
 * Borrowed from 304 JavaDemo
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
	public Computer[] selectManufacturer(String manufacturerName) throws SQLException, NotExists {
		ArrayList<String> columns = new ArrayList<>();

		columns.add(Constants.C_ID);
		columns.add(Constants.W_ID);
		columns.add(Constants.MODEL);
		columns.add(Constants.MANUFACTURER);
		columns.add(Constants.TYPE);

		String projection = String.join(", ", columns);

		PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM COMPUTERS1 c1, COMPUTERS2 c2 WHERE c1.MODEL = c2.MODEL AND manufacturer = ?");
		ps.setString(1, manufacturerName);


		Object[][] queryResults = this.getTableInfoParam(ps);
		Computer[] result = new Computer[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];
			String c_id = null;
			String w_id = null;
			String model = null;
			String manufacturer = null;
			String type = null;
			for (int j = 0; j < row.length; j++) {
				if ("c_id".equalsIgnoreCase(columns.get(j))) {
					c_id = (String) row[j];
				} else if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if (Constants.MODEL.equalsIgnoreCase(columns.get(j))) {
					model = (String) row[j];
				} else if ("manufacturer".equalsIgnoreCase(columns.get(j))) {
					manufacturer = (String) row[j];
				} else if ("type".equalsIgnoreCase(columns.get(j))) {
					type = (String) row[j];
				}
			}

			Computer computer = new Computer(c_id, w_id, model, manufacturer, type);
			result[i] = computer;
		}
		if (result.length == 0) {
			throw new NotExists("Manufacturer " + manufacturerName + " not fond!");
		}
		return result;
	}
	public String[] getSpeciesPreppedFood(String species) throws SQLException, NotExists {
		ArrayList<String> result = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT pf.NAME FROM ANIMALS1 a, PREPPED_FOOD pf WHERE a.A_ID = pf.A_ID AND a.SPECIES = ?");
			ps.setString(1, species);

			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			rsmd.getColumnCount();

			while (rs.next()) {
				String values = rs.getString(rsmd.getColumnLabel(1));

				result.add(values);
			}
			if (result.size() == 0) {
				throw new NotExists(WARNING_TAG + " Species " + species + " Does not exist!");
			}

			rs.close();
			ps.close();
		} catch (SQLException | NotExists e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			throw e;
		}
		return result.toArray(new String[0]);
	}


	public void deleteAnimal(String a_id) throws SQLException, NotExists {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM ANIMALS1 WHERE A_ID = ?");
			ps.setString(1, a_id);

			int rowCount = ps.executeUpdate();


			connection.commit();

			ps.close();

			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Animal " + a_id + " does not exist!");
				throw new NotExists(WARNING_TAG + " Animal " + a_id + " does not exist!");
			}
		} catch (SQLException | NotExists e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}



	public void insertVeterinarian(Veterinarian model) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO WORKERS VALUES (?,?,?,?,?,?)");
			ps.setString(1, model.getW_id());
			ps.setString(2, model.getName());
			ps.setFloat(3, model.getPay_rate());
			ps.setString(4, model.getAddress());
			ps.setString(5, model.getEmail());
			ps.setString(6, model.getPhone());



			ps.executeUpdate();

			ps = connection.prepareStatement("INSERT INTO VETERINARIANS VALUES (?,?)");
			ps.setString(1, model.getW_id());
			ps.setString(2, model.getSpecialization());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	public Object[][] getTableInfoParam(PreparedStatement ps) throws SQLException {
		ArrayList<Object[]> result = new ArrayList<>();


		try {
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				Object[] values = new Object[rsmd.getColumnCount()];

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					if (rsmd.getColumnType(i) == Types.VARCHAR) {
						values[i - 1] = rs.getString(columnName);
					} else if (rsmd.getColumnType(i) == Types.NUMERIC) {
						values[i - 1] = rs.getFloat(columnName);
					} else if (rsmd.getColumnType(i) == Types.TIMESTAMP) {
						values[i - 1] = rs.getDate(columnName).getTime();
					}
				}

				result.add(values);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			throw e;
		}

		return result.toArray(new Object[0][0]);
	}



	public Object[][] getTableInfo(String projection, String tableName) throws SQLException {
		ArrayList<Object[]> result = new ArrayList<>();


		try {
			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM " + tableName);
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				Object[] values = new Object[rsmd.getColumnCount()];

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					if (rsmd.getColumnType(i) == Types.VARCHAR) {
						values[i - 1] = rs.getString(columnName);
					} else if (rsmd.getColumnType(i) == Types.NUMERIC) {
						values[i - 1] = rs.getFloat(columnName);
					} else if (rsmd.getColumnType(i) == Types.TIMESTAMP) {
						values[i - 1] = rs.getDate(columnName).getTime();
					}
				}

				result.add(values);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			throw e;
		}

		return result.toArray(new Object[0][0]);
	}

	public StoredAt[] getStoredAtInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.STORED_AT);
		StoredAt[] result = new StoredAt[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String a_id = null;
			String name = null;
			String p_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("a_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				}
			}
			result[i] = new StoredAt(a_id, name, p_id);
		}
		return result;
	}

	public MaintainsHealthOf[] getMaintainsHealthOfInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.MAINTAINS_HEALTH_OF);
		MaintainsHealthOf[] result = new MaintainsHealthOf[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String a_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("a_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				}
			}
			result[i] = new MaintainsHealthOf(w_id, a_id);
		}
		return result;
	}


	public MadeFrom[] getMadeFromInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.MADE_FROM);
		MadeFrom[] result = new MadeFrom[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String a_id = null;
			String name = null;
			String o_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("a_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("o_id".equalsIgnoreCase(columns.get(j))) {
					o_id = (String) row[j];
				}
			}
			result[i] = new MadeFrom(a_id, name, o_id);
		}
		return result;
	}

	public LocatedAt[] getLocatedAtInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.LOCATED_AT);
		LocatedAt[] result = new LocatedAt[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String o_id = null;
			String p_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("o_id".equalsIgnoreCase(columns.get(j))) {
					o_id = (String) row[j];
				} else if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				}
			}
			result[i] = new LocatedAt(o_id, p_id);
		}
		return result;
	}

	public Feeds[] getFeedsInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.FEEDS);
		Feeds[] result = new Feeds[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String a_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("a_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				}
			}
			result[i] = new Feeds(w_id, a_id);
		}
		return result;
	}

	public CohabitatesWith[] getCohabitatesWithInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.COHABITATES_WITH);
		CohabitatesWith[] result = new CohabitatesWith[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String a_id1 = null;
			String a_id2 = null;
			for (int j = 0; j < row.length; j++) {
				if ("a_id1".equalsIgnoreCase(columns.get(j))) {
					a_id1 = (String) row[j];
				} else if ("a_id2".equalsIgnoreCase(columns.get(j))) {
					a_id2 = (String) row[j];
				}
			}
			result[i] = new CohabitatesWith(a_id1, a_id2);
		}
		return result;
	}

	public AssignedTo[] getAssignedToInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "ASSIGNED_TO");
		AssignedTo[] result = new AssignedTo[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String p_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				}
			}
			result[i] = new AssignedTo(w_id, p_id);
		}
		return result;
	}

	public WorksAt[] getWorksAtInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, Constants.WORKS_AT);
		WorksAt[] result = new WorksAt[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String p_id = null;
			for (int j = 0; j < row.length; j++) {
				if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				}
			}
			result[i] = new WorksAt(w_id, p_id);
		}
		return result;
	}

	public PreppedFood[] getPreppedFoodInfo(ArrayList<String> columns) throws SQLException {

		String projection = String.join(", ", columns);
		Object[][] queryResults = this.getTableInfo(projection, "PREPPED_FOOD");

		PreppedFood[] result = new PreppedFood[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String a_id = null;
			String name = null;
			float weight = 0;
			for (int j = 0; j < row.length; j++) {
				if ("a_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("weight".equalsIgnoreCase(columns.get(j))) {
					weight = (Float) row[j];
				}
			}
			result[i] = new PreppedFood(a_id, name, weight);
		}
		return result;
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM PREPPED_FOOD");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while (rs.next()) {
//				String a_id = null;
//				String name = null;
//				float weight = 0;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("a_id".equalsIgnoreCase(columnName)) {
//						a_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("weight".equalsIgnoreCase(columnName)) {
//						weight = rs.getFloat(columnName);
//					}
//				}
//
//				PreppedFood preppedFood = new PreppedFood(a_id, name, weight);
//				result.add(preppedFood);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new PreppedFood[0]);
	}

	public RawFoodOrder[] getRawFoodOrderInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "RAW_FOOD_ORDERS");
		RawFoodOrder[] result = new RawFoodOrder[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String o_id = null;
			String contents = null;
			int weight = 0;
			Date date_received = null;
			Date expiry_date = null;
			for (int j = 0; j < row.length; j++) {
				if ("o_id".equalsIgnoreCase(columns.get(j))) {
					o_id = (String) row[j];
				} else if ("contents".equalsIgnoreCase(columns.get(j))) {
					contents = (String) row[j];
				} else if ("weight".equalsIgnoreCase(columns.get(j))) {
					weight = Math.round((float) row[j]);
				} else if ("date_received".equalsIgnoreCase(columns.get(j))) {
					date_received = new Date((long) row[j]);
				} else if ("expiry_date".equalsIgnoreCase(columns.get(j))) {
					expiry_date = new Date((long) row[j]);
				}
			}
			RawFoodOrder rawFoodOrder = new RawFoodOrder(o_id, contents, weight, date_received, expiry_date);
			result[i] = rawFoodOrder;
		}
		return result;
	}

	public Zookeeper[] getZookeeperInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "ZOOKEEPERS z, WORKERS w WHERE z.W_ID = w.W_ID");
		Zookeeper[] result = new Zookeeper[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String name = null;
			float pay_rate = 0;
			String address = null;
			String email = null;
			String phone = null;

			for (int j = 0; j < row.length; j++) {
				if (Constants.ZOO_W_ID.equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("pay_rate".equalsIgnoreCase(columns.get(j))) {
					pay_rate = (float) row[j];
				} else if ("address".equalsIgnoreCase(columns.get(j))) {
					address = (String) row[j];
				} else if ("email".equalsIgnoreCase(columns.get(j))) {
					email = (String) row[j];
				} else if ("phone".equalsIgnoreCase(columns.get(j))) {
					phone = (String) row[j];
				}
			}

			Zookeeper zookeeper = new Zookeeper(w_id, name, pay_rate, address, email, phone);
			result[i] = zookeeper;
		}
		return result;
	}

	public Vendor[] getVendorInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);
		Object[][] queryResults = this.getTableInfo(projection, "VENDORS v, WORKERS w WHERE v.W_ID = w.W_ID");
		Vendor[] result = new Vendor[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String name = null;
			float pay_rate = 0;
			String address = null;
			String email = null;
			String phone = null;

			for (int j = 0; j < row.length; j++) {
				if (Constants.VET_W_ID.equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("pay_rate".equalsIgnoreCase(columns.get(j))) {
					pay_rate = (float) row[j];
				} else if ("address".equalsIgnoreCase(columns.get(j))) {
					address = (String) row[j];
				} else if ("email".equalsIgnoreCase(columns.get(j))) {
					email = (String) row[j];
				} else if ("phone".equalsIgnoreCase(columns.get(j))) {
					phone = (String) row[j];
				}
			}

			Vendor vendor = new Vendor(w_id, name, pay_rate, address, email, phone);
			result[i] = vendor;
		}

		return result;
	}

	//returns an array of vets
	public Veterinarian[] getVeterinarianInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);
		Object[][] queryResults = this.getTableInfo(projection, "VETERINARIANS v, WORKERS w WHERE v.W_ID = w.W_ID");
		Veterinarian[] result = new Veterinarian[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String name = null;
			float pay_rate = 0.0f;
			String address = null;
			String email = null;
			String phone = null;
			String specialization = null;

			for (int j = 0; j < row.length; j++) {
				if (Constants.VET_W_ID.equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("pay_rate".equalsIgnoreCase(columns.get(j))) {
					pay_rate = (float) row[j];
				} else if ("address".equalsIgnoreCase(columns.get(j))) {
					address = (String) row[j];
				} else if ("email".equalsIgnoreCase(columns.get(j))) {
					email = (String) row[j];
				} else if ("phone".equalsIgnoreCase(columns.get(j))) {
					phone = (String) row[j];
				} else if ("specialization".equalsIgnoreCase(columns.get(j))) {
					specialization = (String) row[j];
				}
			}
			Veterinarian vet = new Veterinarian(w_id, name, pay_rate, address, email, phone, specialization);
			result[i] = vet;
		}
		return result;
	}

	public Worker[] getWorkerInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);
		Object[][] queryResults = this.getTableInfo(projection, "WORKERS");
		Worker[] result = new Worker[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String name = null;
			float pay_rate = 0.0f;
			String address = null;
			String email = null;
			String phone = null;

			for (int j = 0; j < row.length; j++) {
				if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("pay_rate".equalsIgnoreCase(columns.get(j))) {
					pay_rate = (float) row[j];
				} else if ("address".equalsIgnoreCase(columns.get(j))) {
					address = (String) row[j];
				} else if ("email".equalsIgnoreCase(columns.get(j))) {
					email = (String) row[j];
				} else if ("phone".equalsIgnoreCase(columns.get(j))) {
					phone = (String) row[j];
				}
			}

			Worker worker = new Worker(w_id, name, pay_rate, address, email, phone);
			result[i] = worker;
		}
		return result;
	}

	public StorageUnit[] getStorageUnitInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "STORAGE_UNITS");
		StorageUnit[] result = new StorageUnit[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String p_id = null;
			String name = null;
			int temperature = 0;

			for (int j = 0; j < row.length; j++) {
				if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("temperature".equalsIgnoreCase(columns.get(j))) {
					temperature = Math.round((float) row[j]);
				}
			}

			StorageUnit storageUnit = new StorageUnit(p_id, name, temperature);
			result[i] = storageUnit;
		}

		return result;
	}

	public Habitat[] getHabitatInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "HABITATS1 h1, HABITATS2 h2 WHERE h1.BIOME = h2.BIOME");
		Habitat[] result = new Habitat[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String p_id = null;
			String name = null;
			String biome = null;
			int area = 0;
			int temperature = 0;
			int humidity = 0;

			for (int j = 0; j < row.length; j++) {
				if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if (Constants.BIOME.equalsIgnoreCase(columns.get(j))) {
					biome = (String) row[j];
				} else if ("area".equalsIgnoreCase(columns.get(j))) {
					area = Math.round((float) row[j]);
				} else if ("temperature".equalsIgnoreCase(columns.get(j))) {
					temperature = Math.round((float) row[j]);
				} else if ("humidity".equalsIgnoreCase(columns.get(j))) {
					humidity = Math.round((float) row[j]);
				}
			}

			Habitat habitat = new Habitat(p_id, name, biome, area, temperature, humidity);
			result[i] = habitat;
		}

		return result;
	}

	public Shop[] getShopInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "SHOPS");
		Shop[] result = new Shop[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String p_id = null;
			String name = null;
			String type = null;

			for (int j = 0; j < row.length; j++) {
				if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("type".equalsIgnoreCase(columns.get(j))) {
					type = (String) row[j];
				}
			}

			Shop shop = new Shop(p_id, name, type);
			result[i] = shop;
		}

		return result;
	}

	public Item[] getItemInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "ITEMS");
		Item[] result = new Item[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String a_id = null;
			String p_id = null;
			String name = null;
			int stock = 0;
			Float price = null;
			for (int j = 0; j < row.length; j++) {
				if ("i_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				} else if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("stock".equalsIgnoreCase(columns.get(j))) {
					stock = Math.round((float) row[j]);
				} else if ("price".equalsIgnoreCase(columns.get(j))) {
					price = (Float) row[j];
				}
			}
			Item item = new Item(a_id, p_id, name, stock, price);
			result[i] = item;
		}
		return result;
	}

	public Animal[] getAnimalInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "ANIMALS1 a1, ANIMALS2 a2 WHERE a1.SPECIES = a2.SPECIES");
		Animal[] result = new Animal[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String a_id = null;
			String p_id = null;
			String name = null;
			String species = null;
			String genus = null;

			for (int j = 0; j < row.length; j++) {
				if ("a_id".equalsIgnoreCase(columns.get(j))) {
					a_id = (String) row[j];
				} else if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if (Constants.SPECIES.equalsIgnoreCase(columns.get(j))) {
					species = (String) row[j];
				} else if ("genus".equalsIgnoreCase(columns.get(j))) {
					genus = (String) row[j];
				}
			}
			Animal animal = new Animal(a_id, p_id, name, species, genus);
			result[i] = animal;
		}
		return result;
	}

	public Computer[] getComputerInfo(ArrayList<String> columns) throws SQLException {
		String projection = String.join(", ", columns);

		Object[][] queryResults = this.getTableInfo(projection, "COMPUTERS1 c1, COMPUTERS2 c2 WHERE c1.MODEL = c2.MODEL");
		Computer[] result = new Computer[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String c_id = null;
			String w_id = null;
			String model = null;
			String manufacturer = null;
			String type = null;

			for (int j = 0; j < row.length; j++) {
				if ("c_id".equalsIgnoreCase(columns.get(j))) {
					c_id = (String) row[j];
				} else if ("w_id".equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if (Constants.MODEL.equalsIgnoreCase(columns.get(j))) {
					model = (String) row[j];
				} else if ("manufacturer".equalsIgnoreCase(columns.get(j))) {
					manufacturer = (String) row[j];
				} else if ("type".equalsIgnoreCase(columns.get(j))) {
					type = (String) row[j];
				}
			}

			Computer computer = new Computer(c_id, w_id, model, manufacturer, type);
			result[i] = computer;
		}

		return result;
	}

	public Zookeeper[] getSuperZookeepers() throws SQLException {
		ArrayList<String> columns = new ArrayList<>();

		columns.add(Constants.W_ID);
		columns.add(Constants.NAME);
		columns.add(Constants.PAY_RATE);
		columns.add(Constants.ADDRESS);
		columns.add(Constants.EMAIL);
		columns.add(Constants.PHONE);

		PreparedStatement ps = connection.prepareStatement(
				"SELECT w.W_ID, w.NAME, w.PAY_RATE, w.ADDRESS, w.EMAIL, w.PHONE " +
					"FROM ZOOKEEPERS z, WORKERS w " +
					"WHERE NOT EXISTS " +
						"((SELECT a.A_ID " +
							"FROM ANIMALS1 a) " +
							"MINUS " +
							"((SELECT f.A_ID " +
							"FROM FEEDS f " +
							"WHERE f.W_ID = z.W_ID))) " +
					"AND z.W_ID = w.W_ID");

		Object[][] queryResults = this.getTableInfoParam(ps);
		Zookeeper[] result = new Zookeeper[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String name = null;
			float pay_rate = 0;
			String address = null;
			String email = null;
			String phone = null;

			for (int j = 0; j < row.length; j++) {
				if (Constants.W_ID.equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("pay_rate".equalsIgnoreCase(columns.get(j))) {
					pay_rate = (float) row[j];
				} else if ("address".equalsIgnoreCase(columns.get(j))) {
					address = (String) row[j];
				} else if ("email".equalsIgnoreCase(columns.get(j))) {
					email = (String) row[j];
				} else if ("phone".equalsIgnoreCase(columns.get(j))) {
					phone = (String) row[j];
				}
			}

			Zookeeper zookeeper = new Zookeeper(w_id, name, pay_rate, address, email, phone);
			result[i] = zookeeper;
		}
		return result;


	}

	public Veterinarian[] getCheapVeterinarians() throws SQLException {
		ArrayList<String> columns = new ArrayList<>();

		columns.add(Constants.W_ID);
		columns.add(Constants.NAME);
		columns.add(Constants.PAY_RATE);
		columns.add(Constants.ADDRESS);
		columns.add(Constants.EMAIL);
		columns.add(Constants.PHONE);
		columns.add(Constants.SPECIALIZATION);

		PreparedStatement ps = connection.prepareStatement(
				"SELECT w.W_ID, w.NAME, w.PAY_RATE, w.ADDRESS, w.EMAIL, w.PHONE, v.SPECIALIZATION " +
					"FROM VETERINARIANS v, WORKERS w " +
					"WHERE v.W_ID = w.W_ID AND " +
					"w.PAY_RATE <= ALL (SELECT AVG(w2.pay_rate) " +
										"FROM Workers w2, VETERINARIANS v2 " +
										"WHERE w2.w_id = v2.w_id AND v.SPECIALIZATION = v2.SPECIALIZATION\n" +
										"GROUP BY v2.specialization\n)");

		Object[][] queryResults = this.getTableInfoParam(ps);
		Veterinarian[] result = new Veterinarian[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String w_id = null;
			String name = null;
			float pay_rate = 0.0f;
			String address = null;
			String email = null;
			String phone = null;
			String specialization = null;

			for (int j = 0; j < row.length; j++) {
				if (Constants.W_ID.equalsIgnoreCase(columns.get(j))) {
					w_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("pay_rate".equalsIgnoreCase(columns.get(j))) {
					pay_rate = (float) row[j];
				} else if ("address".equalsIgnoreCase(columns.get(j))) {
					address = (String) row[j];
				} else if ("email".equalsIgnoreCase(columns.get(j))) {
					email = (String) row[j];
				} else if ("phone".equalsIgnoreCase(columns.get(j))) {
					phone = (String) row[j];
				} else if ("specialization".equalsIgnoreCase(columns.get(j))) {
					specialization = (String) row[j];
				}
			}
			Veterinarian vet = new Veterinarian(w_id, name, pay_rate, address, email, phone, specialization);
			result[i] = vet;
		}
		return result;

	}

	public SumWeights[] getFreeStorage() throws SQLException {
		ArrayList<String> columns = new ArrayList<>();

		columns.add(Constants.P_ID);
		columns.add(Constants.NAME);
		columns.add("sum");

		PreparedStatement ps = connection.prepareStatement(
				"SELECT s.P_ID, NAME, SUM(weight) " +
						"FROM RAW_FOOD_ORDERS o, LOCATED_AT l, STORAGE_UNITS s " +
						"WHERE o.O_ID = l.O_ID AND s.P_ID = l.P_ID " +
						"GROUP BY s.P_ID, NAME " +
						"HAVING SUM(weight) < 50");

		Object[][] queryResults = this.getTableInfoParam(ps);
		SumWeights[] result = new SumWeights[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String p_id = null;
			String name = null;
			int sum = 0;

			for (int j = 0; j < row.length; j++) {
				if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("sum".equalsIgnoreCase(columns.get(j))) {
					sum = Math.round((float) row[j]);
				}
			}

			SumWeights sumweights = new SumWeights(p_id, name, sum);
			result[i] = sumweights;
		}

		return result;
	}
	public SumWeights[] getSumWeights() throws SQLException {
		ArrayList<String> columns = new ArrayList<>();

		columns.add(Constants.P_ID);
		columns.add(Constants.NAME);
		columns.add("sum");

		PreparedStatement ps = connection.prepareStatement(
				"SELECT s.P_ID, NAME, SUM(weight) " +
						"FROM RAW_FOOD_ORDERS o, LOCATED_AT l, STORAGE_UNITS s " +
						"WHERE o.O_ID = l.O_ID AND s.P_ID = l.P_ID " +
						"GROUP BY s.P_ID, NAME");

		Object[][] queryResults = this.getTableInfoParam(ps);
		SumWeights[] result = new SumWeights[queryResults.length];

		for (int i = 0; i < queryResults.length; i++) {
			Object[] row = queryResults[i];

			String p_id = null;
			String name = null;
			int sum = 0;

			for (int j = 0; j < row.length; j++) {
				if ("p_id".equalsIgnoreCase(columns.get(j))) {
					p_id = (String) row[j];
				} else if ("name".equalsIgnoreCase(columns.get(j))) {
					name = (String) row[j];
				} else if ("sum".equalsIgnoreCase(columns.get(j))) {
					sum = Math.round((float) row[j]);
				}
			}

			SumWeights sumweights = new SumWeights(p_id, name, sum);
			result[i] = sumweights;
		}

		return result;
	}

	public void updateWorker(String w_id, String column, Object value) throws SQLException, NotExists {
		try {

		  PreparedStatement ps = connection.prepareStatement("UPDATE WORKERS SET " + column + " = ? WHERE W_ID = ?");
		  if (column.equalsIgnoreCase(Constants.PAY_RATE)) {
			  ps.setFloat(1, (float) value);
		  } else {
			  ps.setString(1, (String) value);
		  }
		  ps.setString(2, w_id);


		  int rowCount = ps.executeUpdate();


		  connection.commit();

		  ps.close();

			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Worker " + w_id + " does not exist!");
				throw new NotExists(WARNING_TAG + " Worker " + w_id + " does not exist!");
			}
		} catch (SQLException | NotExists e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
			throw e;
		}
	}

	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}

			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);

			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

}
