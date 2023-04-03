package database;


//import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.*;
import util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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



	public void deleteAnimal(String a_id) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM ANIMALS1 WHERE A_ID = ?");
			ps.setString(1, a_id);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Animal " + a_id + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
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

	public PreppedFood[] getPreppedFoodInfo(ArrayList<String> columns) {
		ArrayList<PreppedFood> result = new ArrayList<>();

		String projection = String.join(", ", columns);

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM PREPPED_FOOD");
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				String a_id = null;
				String name = null;
				float weight = 0;

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);

					if ("a_id".equalsIgnoreCase(columnName)) {
						a_id = rs.getString(columnName);
					} else if ("name".equalsIgnoreCase(columnName)) {
						name = rs.getString(columnName);
					} else if ("weight".equalsIgnoreCase(columnName)) {
						weight = rs.getFloat(columnName);
					}
				}

				PreppedFood preppedFood = new PreppedFood(a_id, name, weight);
				result.add(preppedFood);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new PreppedFood[0]);
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


//	public RawFoodOrder[] getRawFoodOrderInfo(ArrayList<String> columns) {
//		ArrayList<RawFoodOrder> result = new ArrayList<RawFoodOrder>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM RAW_FOOD_ORDERS");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String o_id = null;
//				String contents = null;
//				int weight = 0;
//				Date date_received = null;
//				Date expiry_date = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("o_id".equalsIgnoreCase(columnName)) {
//						o_id = rs.getString(columnName);
//					} else if ("contents".equalsIgnoreCase(columnName)) {
//						contents = rs.getString(columnName);
//					} else if ("weight".equalsIgnoreCase(columnName)) {
//						weight = rs.getInt(columnName);
//					} else if ("date_received".equalsIgnoreCase(columnName)) {
//						date_received = new Date(rs.getDate(columnName).getTime());
//					} else if ("expiry_date".equalsIgnoreCase(columnName)) {
//						expiry_date = new Date(rs.getDate(columnName).getTime());
//					}
//				}
//
//				RawFoodOrder raw_food_order = new RawFoodOrder(o_id, contents, weight, date_received, expiry_date);
//				result.add(raw_food_order);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new RawFoodOrder[0]);
//
//	}

//	public Zookeeper[] getZookeeperInfo(ArrayList<String> columns) {
//		ArrayList<Zookeeper> result = new ArrayList<>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM ZOOKEEPERS z, WORKERS w WHERE z.W_ID = w.W_ID");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String w_id = null;
//				String name = null;
//				float pay_rate = 0;
//				String address = null;
//				String email = null;
//				String phone = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("w_id".equalsIgnoreCase(columnName)) {
//						w_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("pay_rate".equalsIgnoreCase(columnName)) {
//						pay_rate = rs.getFloat(columnName);
//					} else if ("address".equalsIgnoreCase(columnName)) {
//						address = rs.getString(columnName);
//					} else if ("email".equalsIgnoreCase(columnName)) {
//						email = rs.getString(columnName);
//					} else if ("phone".equalsIgnoreCase(columnName)) {
//						phone = rs.getString(columnName);
//					}
//				}
//
//				Zookeeper zookeeper = new Zookeeper(w_id, name, pay_rate, address, email, phone);
//				result.add(zookeeper);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Zookeeper[0]);
//	}

//	public Vendor[] getVendorInfo(ArrayList<String> columns) {
//		ArrayList<Vendor> result = new ArrayList<Vendor>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM VENDORS v, WORKERS w WHERE v.W_ID = w.W_ID");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String w_id = null;
//				String name = null;
//				float pay_rate = 0;
//				String address = null;
//				String email = null;
//				String phone = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("w_id".equalsIgnoreCase(columnName)) {
//						w_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("pay_rate".equalsIgnoreCase(columnName)) {
//						pay_rate = rs.getFloat(columnName);
//					} else if ("address".equalsIgnoreCase(columnName)) {
//						address = rs.getString(columnName);
//					} else if ("email".equalsIgnoreCase(columnName)) {
//						email = rs.getString(columnName);
//					} else if ("phone".equalsIgnoreCase(columnName)) {
//						phone = rs.getString(columnName);
//					}
//				}
//
//				Vendor vendor = new Vendor(w_id, name, pay_rate, address, email, phone);
//				result.add(vendor);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Vendor[0]);
//	}

//	public Veterinarian[] getVeterinarianInfo(ArrayList<String> columns) {
//		ArrayList<Veterinarian> result = new ArrayList<Veterinarian>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM VETERINARIANS v, WORKERS w WHERE v.W_ID = w.W_ID");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String w_id = null;
//				String name = null;
//				float pay_rate = 0.0f;
//				String address = null;
//				String email = null;
//				String phone = null;
//				String specialization = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("w_id".equalsIgnoreCase(columnName)) {
//						w_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("pay_rate".equalsIgnoreCase(columnName)) {
//						pay_rate = rs.getFloat(columnName);
//					} else if ("address".equalsIgnoreCase(columnName)) {
//						address = rs.getString(columnName);
//					} else if ("email".equalsIgnoreCase(columnName)) {
//						email = rs.getString(columnName);
//					} else if ("phone".equalsIgnoreCase(columnName)) {
//						phone = rs.getString(columnName);
//					} else if ("specialization".equalsIgnoreCase(columnName)) {
//						specialization = rs.getString(columnName);
//					}
//				}
//
//				Veterinarian vet = new Veterinarian(w_id, name, pay_rate, address, email, phone, specialization);
//				result.add(vet);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Veterinarian[0]);
//	}

//	public Worker[] getWorkerInfo(ArrayList<String> columns) {
//		ArrayList<Worker> result = new ArrayList<>();
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM WORKERS");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String w_id = null;
//				String name = null;
//				float pay_rate = 0.0f;
//				String address = null;
//				String email = null;
//				String phone = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("w_id".equalsIgnoreCase(columnName)) {
//						w_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("pay_rate".equalsIgnoreCase(columnName)) {
//						pay_rate = rs.getFloat(columnName);
//					} else if ("address".equalsIgnoreCase(columnName)) {
//						address = rs.getString(columnName);
//					} else if ("email".equalsIgnoreCase(columnName)) {
//						email = rs.getString(columnName);
//					} else if ("phone".equalsIgnoreCase(columnName)) {
//						phone = rs.getString(columnName);
//					}
//				}
//
//				Worker worker = new Worker(w_id, name, pay_rate, address, email, phone);
//				result.add(worker);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Worker[0]);
//	}
//	public StorageUnit[] getStorageUnitInfo(ArrayList<String> columns) {
//		ArrayList<StorageUnit> result = new ArrayList<>();
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM STORAGE_UNITS");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String p_id = null;
//				String name = null;
//				int temperature = 0;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("p_id".equalsIgnoreCase(columnName)) {
//						p_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("temperature".equalsIgnoreCase(columnName)) {
//						temperature = rs.getInt(columnName);
//					}
//				}
//
//				StorageUnit storageUnit = new StorageUnit(p_id, name, temperature);
//				result.add(storageUnit);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new StorageUnit[0]);
//	}
//	public Shop[] getShopInfo(ArrayList<String> columns) {
//		ArrayList<Shop> result = new ArrayList<>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM SHOPS");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String p_id = null;
//				String name = null;
//				String type = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("p_id".equalsIgnoreCase(columnName)) {
//						p_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("type".equalsIgnoreCase(columnName)) {
//						type = rs.getString(columnName);
//					}
//				}
//
//				Shop shop = new Shop(p_id, name, type);
//				result.add(shop);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Shop[0]);
//	}
//	public Habitat[] getHabitatInfo(ArrayList<String> columns) {
//		ArrayList<Habitat> result = new ArrayList<>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM HABITATS1 h1, HABITATS2 h2 WHERE h1.BIOME = h2.BIOME");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String p_id = null;
//				String name = null;
//				String biome = null;
//				int area = 0;
//				int temperature = 0;
//				int humidity = 0;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("p_id".equalsIgnoreCase(columnName)) {
//						p_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("biome".equalsIgnoreCase(columnName)) {
//						biome = rs.getString(columnName);
//					} else if ("area".equalsIgnoreCase(columnName)) {
//						area = rs.getInt(columnName);
//					} else if ("temperature".equalsIgnoreCase(columnName)) {
//						temperature = rs.getInt(columnName);
//					} else if ("humidity".equalsIgnoreCase(columnName)) {
//						humidity = rs.getInt(columnName);
//					}
//				}
//
//
//				Habitat habitat = new Habitat(p_id, name, biome, area, temperature, humidity);
//				result.add(habitat);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Habitat[0]);
//	}
//	public Item[] getItemInfo(ArrayList<String> columns) {
//		ArrayList<Item> result = new ArrayList<>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM Items");
//			ResultSet rs = ps.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String a_id = null;
//				String p_id = null;
//				String name = null;
//				int stock = 0;
//				Float price = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//
//					if ("i_id".equalsIgnoreCase(columnName)) {
//						a_id = rs.getString(columnName);
//					} else if ("p_id".equalsIgnoreCase(columnName)) {
//						p_id = rs.getString(columnName);
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = rs.getString(columnName);
//					} else if ("stock".equalsIgnoreCase(columnName)) {
//						stock = rs.getInt(columnName);
//					} else if ("price".equalsIgnoreCase(columnName)) {
//						price = rs.getFloat(columnName);
//					}
//				}
//
//
//				Item item = new Item(a_id, p_id, name, stock, price);
//				result.add(item);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Item[0]);
//	}

//	public Animal[] getAnimalInfo(ArrayList<String> columns) {
//		ArrayList<Animal> result = new ArrayList<>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM ANIMALS1 a1, ANIMALS2 a2 WHERE a1.SPECIES = a2.SPECIES");
//			ResultSet rs = ps.executeQuery();
//
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String a_id = null;
//				String p_id = null;
//				String name = null;
//				String species = null;
//				String genus = null;
//
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//					String columnValue = rs.getString(columnName);
//
//					if ("a_id".equalsIgnoreCase(columnName)) {
//						a_id = columnValue;
//					} else if ("p_id".equalsIgnoreCase(columnName)) {
//						p_id = columnValue;
//					} else if ("name".equalsIgnoreCase(columnName)) {
//						name = columnValue;
//					} else if ("species".equalsIgnoreCase(columnName)) {
//						species = columnValue;
//					} else if ("genus".equalsIgnoreCase(columnName)) {
//						genus = columnValue;
//					}
//				}
//
//
//				Animal animal = new Animal(a_id, p_id, name, species, genus);
//				result.add(animal);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Animal[0]);
//	}

//	public Computer[] getComputerInfo(ArrayList<String> columns) {
//		ArrayList<Computer> result = new ArrayList<>();
//
//		String projection = String.join(", ", columns);
//
//		try {
//			PreparedStatement ps = connection.prepareStatement("SELECT " + projection + " FROM COMPUTERS1 c1, COMPUTERS2 c2 WHERE c1.MODEL = c2.MODEL");
//			ResultSet rs = ps.executeQuery();
//
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//			while(rs.next()) {
//				String c_id = null;
//				String w_id = null;
//				String model = null;
//				String manufacturer = null;
//				String type = null;
//
//				// check if each column exists in the ResultSet
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					String columnName = rsmd.getColumnName(i);
//					String columnValue = rs.getString(columnName);
//
//					if ("c_id".equalsIgnoreCase(columnName)) {
//						c_id = columnValue;
//					} else if ("w_id".equalsIgnoreCase(columnName)) {
//						w_id = columnValue;
//					} else if ("model".equalsIgnoreCase(columnName)) {
//						model = columnValue;
//					} else if ("manufacturer".equalsIgnoreCase(columnName)) {
//						manufacturer = columnValue;
//					} else if ("type".equalsIgnoreCase(columnName)) {
//						type = columnValue;
//					}
//				}
//
//				Computer computer = new Computer(c_id, w_id, model, manufacturer, type);
//				result.add(computer);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new Computer[0]);
//	}
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
