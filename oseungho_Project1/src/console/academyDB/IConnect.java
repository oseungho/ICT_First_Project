package console.academyDB;

public interface IConnect {
	//상수
	String ORACLE_DRIVER ="oracle.jdbc.OracleDriver";
	String ORACLE_BASE_URL ="jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String USER = "ACADEMY";
	String PASSWORD = "ACADEMY";
	//추상 메소드
	void connect(String url,String user,String password);
	void execute(int mainMenu, int searchno) throws Exception;
	void close();
	String getValue(String message);
	String getQueryString();
	void DBPrint(int searchno) throws Exception;
	void DBSearch(int searchno) throws Exception;
}
