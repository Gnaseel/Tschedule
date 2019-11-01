
import java.sql.*;
public class TempDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("isit work");
		
		
		Connection con;
		Statement stmt;
		ResultSet rSet;
		String query = "select * from a";
		String key="vKj/KXP67Uh63gVLXld7CXB+FTqXtEuP20r5gpBwPw0";
		String ENC=java.net.URLEncoder.encode(key);
		System.out.println(ENC);
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1511");
			stmt=con.createStatement();
			rSet = stmt.executeQuery(query);
			while(rSet.next()) {
				String id = rSet.getString(1);
				System.out.println("id = "+id);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("in E");
		}
		
	}

	private static String encodeURL(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
