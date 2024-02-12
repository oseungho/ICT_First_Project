package console.academyDB;

import java.sql.ResultSetMetaData;
import java.util.Scanner;

public class ICLogin extends IConnectImpl{
	Scanner sc = new Scanner(System.in);
	String sql=null;
	String DBID, DBPW;
	
	public ICLogin() throws Exception {
		connect(ORACLE_BASE_URL, USER, PASSWORD);		
	}
	
	boolean DBLogin() throws Exception{
		sql = "Select * from SHADMIN where ID = '"+ 
				getValue("DB에 접속할 ID") + "' and PASSWORD = '" + 
				getValue("DB에 접속할 PW")+"'";
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		if(rs.next()) {
			System.out.println("You were successfully logged in.");
			return true;
		}else {
			System.out.println("[Error Message] Invalid username or password.");
			return false;
		}
	}
}





