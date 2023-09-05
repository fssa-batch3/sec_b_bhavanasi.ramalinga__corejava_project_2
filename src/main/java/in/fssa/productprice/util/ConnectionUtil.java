package in.fssa.productprice.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		String url;
		String userName;
		String passWord;
		
		// cloud//
            url = System.getenv("DATABASE_HOSTNAME");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
      
        	// local//
//        	url = "jdbc:mysql://164.52.216.41/bhavanasi_ramalinga__corejava_project";
//             userName ="uCyUroAdHR99";
//        	passWord	="1b926b10-5b63-4775-a252-561e679f0668";


       Connection connection1 = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

       connection1 = DriverManager.getConnection(url, userName,passWord);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection1;
	}
	/**
	 * 
	 * @param connection
	 * @param ps
	 */
	
	public static void close(Connection connection , PreparedStatement  ps) {
		try {
			if(ps !=null) {
				ps.close();
			}
			if(connection!= null) {
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */
			
	
	public static void close(Connection connection , PreparedStatement  ps , ResultSet  rs) {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(ps !=null) {
				ps.close();
			}
			if(connection!= null) {
				connection.close();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
