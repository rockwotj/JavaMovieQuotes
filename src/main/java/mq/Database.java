package mq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class Database {

	private static Database instance = new Database();
	
	public static Database getInstance() {
		return instance;
	}
	
	private Connection connection;

	private Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
			String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
			String name = "moviequotes";
			String user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
			String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
			connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port +"/" + name, user, password);
			System.out.println("Connection successful!");
		} catch (Exception e) {
			System.err.println("Cannot load SQL Driver!");
			e.printStackTrace();
		}
	}
	
	public List<MovieQuote> query() {
		List<MovieQuote> moviequotes = new ArrayList<MovieQuote>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM moviequotes;");
			while(rs.next()) {
				int id = rs.getInt("id");
				String movie = rs.getString("movie");
				String quote = rs.getString("quote");
				Date lastTouch = rs.getDate("last_touch");
				moviequotes.add(new MovieQuote(id, movie, quote, lastTouch));
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println("SQL Error in Query!");
			e.printStackTrace();
		}
//		moviequotes.sort(new Comparator<MovieQuote>() {
//			@Override
//			public int compare(MovieQuote o1, MovieQuote o2) {
//				return -1;
//			}
//		});
		return moviequotes;
	}
	
	public void insert(MovieQuote mq) {
		try {
			PreparedStatement statement;
			if (mq.getId() == -1) {
				statement = connection.prepareStatement("INSERT INTO moviequotes (movie, quote, last_touch) VALUES (?, ?, ?)");
				statement.setString(1, mq.getMovie());
				statement.setString(2, mq.getQuote());
				java.sql.Date date = new java.sql.Date(mq.getLastTouch().getTime());
				statement.setDate(3, date);
			} else {
				statement = connection.prepareStatement("UPDATE moviequotes SET movie=?, quote=?, last_touch=? WHERE id=?");
				statement.setInt(4, mq.getId());
				statement.setString(1, mq.getMovie());
				statement.setString(2, mq.getQuote());
				java.sql.Date date = new java.sql.Date(mq.getLastTouch().getTime());
				statement.setDate(3, date);
			}
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("SQL Error in Query!");
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			PreparedStatement statement;
			statement = connection.prepareStatement("DELETE FROM moviequotes WHERE id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("SQL Error in Query!");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		connection.close();
		super.finalize();
	}
	
}
