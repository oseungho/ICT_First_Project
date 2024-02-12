package console.academyDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectImpl implements IConnect {

	//필드]
	public Connection conn;
	public ResultSet rs;
	public Statement stmt;
	public PreparedStatement psmt;
	public CallableStatement csmt;
	private Scanner sc = new Scanner(System.in);
	//[static블락]
	static {
		try {
			//드라이버 로딩]
			Class.forName(ORACLE_DRIVER);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
	}////
	//[기본 생성자]
	public IConnectImpl() {}
	//[인자 생성자]
	public IConnectImpl(String url, String user, String password) {
		connect(url, user, password);
	}//////
	
	//데이타베이스 연결하는 메소드]	
	@Override
	public void connect(String url, String user, String password) {	
		try {
			conn=DriverManager.getConnection(url, user, password);
			//System.out.println("DB Connect Success");
		}
		catch(SQLException e) {
			//System.out.println("DB Connect Fail");
		}
	}/////connect
	
	//쿼리 실행 메소드]
	@Override
	public void execute(int mainMenu, int searchno) throws Exception {}
	
	@Override
	public void DBPrint(int searchno) throws Exception {}
	
	@Override
	public void DBSearch(int searchno) throws Exception {}

	
	//데이타베이스 연결 끊는 메소드]
	@Override
	public void close() {
		try{
			if(csmt !=null) csmt.close();
			if(psmt !=null) psmt.close();
			if(stmt !=null) stmt.close();
			if(rs !=null) rs.close();
			if(conn !=null) conn.close();
		}
		catch(SQLException e) {}
	}/////////////////////////
	//사용자 입력용 메소드]
	@Override
	public String getValue(String message) {
		System.out.println(message+"을(를) 입력하세요?");
		String value = sc.nextLine().trim();
		if("EXIT".equalsIgnoreCase(value)) {
			close();//데이타베이스 연결끊기(자원반납)]
			System.out.println("Oracle Database 18c Express Edition Release 18.0.0.0.0 - Production\r\n"
					+ "Version 18.4.0.0.0에서 분리되었습니다.");
			System.exit(0);
		}
		return value;
	}/////////////////
	@Override
	public String getQueryString() {
		System.out.print("SQL>");
		return sc.nextLine().trim();
	}
}
