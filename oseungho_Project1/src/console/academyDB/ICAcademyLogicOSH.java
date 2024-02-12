package console.academyDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import common.utils.CommonUtils;
import console.academy.AcademyLogicOSH;
import console.academy.Person;
import console.academy.Student;
import console.academy.Teacher;
public class ICAcademyLogicOSH extends IConnectImpl{
	Scanner sc = new Scanner(System.in);
	int searchno=0, chkdata, flag;
	String searchdata = null, sql=null;
	ErrorCatch EC = new ErrorCatch();

	//[생성자]
	public ICAcademyLogicOSH() throws Exception {
		connect(ORACLE_BASE_URL, USER, PASSWORD);
	}

	//[글자 관련 색상]
	private static final String BOLD     		= "\u001B[1m"  ;
	private static final String NOBOLD   		= "\u001B[2m"  ;
	private static final String BLACK    		= "\u001B[30m" ;
	private static final String RED      		= "\u001B[31m" ;
	private static final String GREEN    		= "\u001B[32m" ;
	private static final String BLUE     		= "\u001B[34m" ;
	private static final String PURPLE   		= "\u001B[35m" ;
	private static final String CYAN     		= "\u001B[36m" ;
	private static final String WHITE            = "\u001B[37m" ;
    private static final String DEFAULT          = "\u001B[0m\u001B[2m"  ;
    private static final String FRAMED           = "\u001B[51m \u001B[47m";
    private static final String FRAMED_CLOCK     ="\u001B[21m\u001B[46m";
    private static final String FRAMED_CLOCK_TEXT="\u001B[21m\u001B[46m";
    private static final String DOUBLE_LINE      = "\u001B[21m";
	
	
	//1]메뉴 번호 입력용 메소드
	public int getMenuNumber() {	
		return EC.getChkInt(sc);
	}/////////getMenuNumber
	
	//1]메인메뉴 출력용 메소드
	public void printMainMenu() {
		System.out.println(BOLD + BLUE +"==========================Main Menu=========================="+ NOBOLD + DEFAULT);
		System.out.println(BOLD + BLUE +"0.모드변경 1.입력 2.출력 3.수정 4.삭제 5.검색 6.수강변경 7.파일로 저장 9.종료"+ NOBOLD + DEFAULT);
		System.out.println(BOLD + BLUE +"============================================================="+ NOBOLD + DEFAULT);
		System.out.println(BOLD + BLACK +"메인 메뉴 번호를 입력하세요?"+ NOBOLD + DEFAULT);
	}///////printMainMenu()
	
	//2]메인메뉴 번호에 따른 분기용 메소드
	public void seperateMainMenu(int mainMenu) throws Exception {
		switch(mainMenu) {
			case 0 :
				System.out.println("File버전 실행을 위해 DB버전을 종료합니다.");
				AcademyLogicOSH logic = new AcademyLogicOSH();
				while(true) {
					//1.메인 메뉴 출력
					logic.printMainMenu();
					//2.메인메뉴 번호 입력받기
					int mainMenu2 =logic.getMenuNumber();
					//3. 메인메뉴에 따른 분기
					logic.seperateMainMenu(mainMenu2);
				}//모드변경
			case 1: case 2: case 3: case 4: case 5: case 6: case 7: choicequery(mainMenu);break;
			case 9: //종료
					System.out.println("프로그램을 종료합니다");
					close();
					System.exit(0); break;
			default: System.out.println("메뉴에 없는 번호입니다");
		}//switch
	}///////////////////seperateMainMenu
	
	//3]서브메뉴 출력용 메소드
	private void printSubMenu(int subno) {
		System.out.println("++++++++++++++++++++Sub Menu++++++++++++++++++++");
		System.out.println(subno == 0? "1.학생 등록 2.교사 등록 3. 교과 등록 4.메인메뉴로 이동" :
						   subno == 1? "1.이름으로 조회 2.과목으로 조회 3.학번으로 조회 4.메인메뉴로 이동" :
						   subno == 2? "1.교사 조회 2.학생 조회 3.교과 조회 4.메인메뉴로 이동" :
						   subno == 3? "1.이름으로 조회 2.학번으로 조회 3.메인메뉴로 이동" :
						   subno == 4? "1.이름 수정 2.나이 수정 3.전화번호 수정 4.이메일 수정 5.메인메뉴로 이동" :
						   subno == 5? "1.수강 신청 2.수강 취소 3.성적 조회 4.메인메뉴로 이동" :
						   "1.교사DB 2.학생DB 3.교과DB 4.메인메뉴로 이동"); //파일 저장 시
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("서브 메뉴번호를 입력하세요?");	
	}////////////printSubMenu
	
	//3] 서브메뉴 출력 후 번호에 따른 분기용 메소드
	public int getsubNumber(int mainMenu) {
		if(mainMenu == 1) printSubMenu(0);
		else if(mainMenu == 2) printSubMenu(2);
		else if(mainMenu == 3 || mainMenu == 4) printSubMenu(3);
		else if(mainMenu == 6) printSubMenu(5);
		else if(mainMenu == 7) printSubMenu(6); 
		else {printSubMenu(1);}
		searchno=getMenuNumber();
		while(true) {
			if(((mainMenu == 1 || mainMenu == 2 || mainMenu == 5|| mainMenu==6 || mainMenu == 7) && searchno == 4) 
					|| ((mainMenu == 3 || mainMenu == 4) && searchno == 3)) return 0;
			switch(searchno) {
				case 1: return 1;
				case 2: return 2;
				case 3: return 3;
				default : System.out.println("서브메뉴에 없는 번호입니다. 이전 단계로 돌아갑니다."); return 0;
			}
		}
	}/////////getsubNumber
	
	// 선택한 메인,서브메뉴 번호에 따른 코드 실행 메소드
	public void choicequery(int mainMenu) throws Exception {
		connect(ORACLE_BASE_URL, USER, PASSWORD);
		searchno = getsubNumber(mainMenu);
		if(searchno != 0 && mainMenu == 2) {//출력일 경우
			DBPrint(searchno);
		}else if(searchno != 0 && mainMenu == 6) {
			enrolment(searchno);
		}else if(searchno != 0 && mainMenu == 7){//파일 저장인 경우
			fileSave(searchno);
		}else if(searchno != 0 && mainMenu != 2) {//출력을 제외한 메인메뉴(1,3,4,5)
			flag = 0;
			if(mainMenu != 1) {//입력을 제외한 메인 메뉴(3,4,5)
				if((mainMenu == 4 || mainMenu == 3) && searchno == 2) {searchno = searchno + 1;} 
				DBSearch(searchno);
			}
			if(flag == 0) {//flag를 통해 조회한 Data가 없을 경우(flag = 1) 예외처리 진행
				execute(mainMenu, searchno);
			}
		}
		close();
	}///////choicequery
	
	// DB 출력용 메소드
		@Override
		public void DBPrint(int searchno) throws Exception {
			System.out.println(searchno == 1? "[교사 정보를 출력합니다.]" :
							   searchno == 2? "[학생 정보를 출력합니다.]" :
							   "[교과 정보를 출력합니다.]");
			String order = getValue(searchno == 1? "(이름|나이|담당과목)정렬할 기준":
									searchno == 2? "(이름|나이|학번)정렬할 기준":
									"(교과번호|교과명)정렬할 기준");
			if(searchno == 1) {
				if(order.equals("이름") || order.equals("나이") || order.equals("담당과목")) {}
				else {
					System.out.println("올바르지 않은 입력값입니다. 기본값인 이름으로 정렬합니다.");
					order = "이름";
				}
				sql = "SELECT * FROM ("
					  + "SELECT TNAME 이름, TAGE 나이, TPHONE_NUMBER 전화번호, TEMAIL 이메일, sbdb.SubjectName 담당과목 FROM TeacherDB tdb "
					  + "LEFT JOIN subjectdb sbdb ON tdb.SubjectID = sbdb.SubjectID) ORDER BY "+order; 
			}else if(searchno == 2) {
				if(order.equals("이름") || order.equals("나이") || order.equals("학번")) {}
				else {
					System.out.println("올바르지 않은 입력값입니다. 기본값인 이름으로 정렬합니다.");
					order = "이름";
				}
				sql =   "SELECT s.SName 이름, s.Sage 나이, s.STNumber 학번, s.SPhone_number 전화번호, s.Semail 이메일,"
						+ "LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 수강과목 FROM StudentDB s "
						+ "LEFT JOIN StudentSubject ss ON s.StudentID = ss.StudentID "
						+ "LEFT JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID "
						+ "GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail ORDER BY "+order;
			}else {
				if(order.equals("교과번호") || order.equals("교과명")){}
				else {
					System.out.println("올바르지 않은 입력값입니다. 기본값인 교과번호로 정렬합니다.");
					order = "교과번호";
				}
				sql = "select * from ( "
					  + "select sbdb.SUBJECTID 교과번호, sbdb.SUBJECTNAME 교과명, tdb.tname 담당교수, sbdb.SUBJECT_STLIMIT 정원 from subjectDB sbdb "
					  + "LEFT JOIN teacherDB tdb on sbdb.SUBJECTID = tdb.SUBJECTID) order by "+ order;
			}
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount=rsmd.getColumnCount();
			List<Integer> dashCount = new Vector<>();
			for(int i=1;i <= columnCount;i++ ) {
				int columnSize=rsmd.getPrecision(i);
				int columnType=rsmd.getColumnType(i);
				if(i != 1) {
					switch(columnType) {
						case Types.NCHAR:
						case Types.NVARCHAR:
							dashCount.add(columnSize*2);break;
						case Types.NUMERIC:
						case Types.TIMESTAMP:
							dashCount.add(10);break;
							
						default:dashCount.add(columnSize);
					}
				}
				else{dashCount.add(5);}
				String columnName = rsmd.getColumnName(i).length() > dashCount.get(i-1) ?
			            rsmd.getColumnName(i).substring(0,dashCount.get(i-1)) :
			            rsmd.getColumnName(i);	
				System.out.print(String.format("%-"+(dashCount.get(i-1)+1)+"s", columnName));
			}
			System.out.println();
			for(Integer count:dashCount) {
				for(int i=0;i<count;i++) System.out.print('=');
				System.out.print(" ");
			}
			System.out.println();//줄바꿈
			int no =0;
			while(rs.next()) {
				//각 컬럼값 뽑아오기]
				for(int i=1;i<=columnCount;i++) {
					int columnType = rsmd.getColumnType(i);
					String columnValue;
					if(columnType==Types.TIMESTAMP)
						columnValue=rs.getDate(i).toString();
					else
						columnValue=rs.getString(i);
					System.out.print(String.format("%-"+(dashCount.get(i-1)+1)+"s",columnValue==null?"":columnValue));
				}
				System.out.println();//줄바꿈
				for(Integer count:dashCount) {
					for(int i=0;i<count;i++) System.out.print('-');
					System.out.print(" ");
				}
				System.out.println();//줄바꿈
				no++;
			}
			System.out.println(no+"행이 조회됐습니다.");
		}
	
	//DB 조회용 매소드
	@Override
	public void DBSearch(int searchno) throws SQLException {
		chkdata=0;
		String selectSQL = "select * from "
						   + "(Select '학생' AS 구분, s.StudentID AS 코드번호, SName AS 이름, Sage AS 나이, SPhone_number AS 전화번호, SEmail AS 이메일, stnumber as 학번, "
						   + "LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 과목 "
						   + "FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID "
						   + "Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID "
						   + "GROUP BY s.StudentID, s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail ";
		String selectSQL1ST = ") where ";
		String selectSQL2ND = "UNION "
						   + "SELECT '교사' AS 구분, TeacherID AS 코드번호, TName AS 이름, Tage AS 나이, TPhone_number AS 전화번호, TEmail AS 이메일, '' as 학번, subjectName 과목 "
						   + "FROM TeacherDB t LEFT JOIN subjectDB sbdb on t.subjectID = sbdb.subjectID) "
						   + "where ";		
		if(searchno == 1 || searchno == 111) {
			searchdata = EC.getChkNull(getValue("이름"), "조회할 이름");
			if(searchno ==1) {
				sql = selectSQL+ selectSQL2ND + "이름 ='"+searchdata+"' ORDER BY 구분, 이름";				
			}else {
				sql = selectSQL + selectSQL1ST + "이름 ='"+searchdata+"' ORDER BY 구분, 이름";
			}
		}else if(searchno == 2) {
			searchdata = getValue("과목");
			if(searchdata.equalsIgnoreCase("JAVA")) {
				sql = selectSQL+ selectSQL2ND + "과목 ='"+searchdata+"'or 과목 Like '%"+searchdata+",%' ORDER BY 구분, 이름";
			}else if(searchdata.equalsIgnoreCase("R")) {
				sql = selectSQL+ selectSQL2ND + "과목 ='"+searchdata+"'or 과목 Like '%"+searchdata+",%' or 과목 Like '%"+searchdata+"' ORDER BY 구분, 이름";
			}else {
				sql = selectSQL+ selectSQL2ND + "과목 ='"+searchdata+"'or 과목 Like '%"+searchdata+"%' ORDER BY 구분, 이름";				
			}
		}else if(searchno == 3 || searchno == 333){
			searchdata = getValue("학번");
			if(searchno == 3) {
				sql = selectSQL+ selectSQL2ND + "학번 ='"+searchdata+"' ORDER BY 이름";				
			}else {
				sql = selectSQL + selectSQL1ST + "학번 ='"+searchdata+"' ORDER BY 구분, 이름";
			}
		}
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnCount=rsmd.getColumnCount();
		List<Integer> dashCount = new Vector<>();
		for(int i=1;i <= columnCount;i++ ) {
			int columnSize=rsmd.getPrecision(i);
			int columnType=rsmd.getColumnType(i);
			if(i != 1) {
				switch(columnType) {
					case Types.NCHAR:
					case Types.NVARCHAR:
						dashCount.add(columnSize*2);break;
					case Types.NUMERIC:
					case Types.TIMESTAMP:
						dashCount.add(10);break;
						
					default:dashCount.add(columnSize);
				}
			}
			else{dashCount.add(5);}
			String columnName = rsmd.getColumnName(i).length() > dashCount.get(i-1) ?
		            rsmd.getColumnName(i).substring(0,dashCount.get(i-1)) :
		            rsmd.getColumnName(i);	
			System.out.print(String.format("%-"+(dashCount.get(i-1)+1)+"s", columnName));
		}
		System.out.println();
		for(Integer count:dashCount) {
			for(int i=0;i<count;i++) System.out.print('=');
			System.out.print(" ");
		}
		System.out.println();//줄바꿈
		int no =0;
		while(rs.next()) {
			//각 컬럼값 뽑아오기]
			for(int i=1;i<=columnCount;i++) {
				int columnType = rsmd.getColumnType(i);
				String columnValue;
				if(columnType==Types.TIMESTAMP)
					columnValue=rs.getDate(i).toString();
				else
					columnValue=rs.getString(i);
				System.out.print(String.format("%-"+(dashCount.get(i-1)+1)+"s",columnValue==null?"":columnValue));
			}
			System.out.println();//줄바꿈
			for(Integer count:dashCount) {
				for(int i=0;i<count;i++) System.out.print('-');
				System.out.print(" ");
			}
			System.out.println();//줄바꿈
			chkdata++;
		}

		if(chkdata == 0) {
			System.out.println("["+searchdata+"]로 검색한 결과가 없습니다. 이전 단계로 돌아갑니다.\n");
			flag = 1;
		}else {
			System.out.println("=======================================================");
			System.out.println("["+searchdata+"]로 조회된 Data는 총 "+chkdata+"건 입니다.");
		}
	}
	
	//DB DML(Insert, Update, Delete) 처리 메소드
	@Override
	public void execute(int mainMenu, int searchno) throws SQLException {
		int codeno =0;
		if(mainMenu == 1) {//입력
			if(searchno == 1) {//학생
				sql = "INSERT INTO StudentDB(SNAME, SAGE, SPhone_number, SEMAIL, STNUMBER, ID) VALUES(?, ?, ?, ?, ?, 'OSH')";
			}else if(searchno == 2){//교사
				sql = "INSERT INTO TeacherDB (TNAME, TAGE, TPhone_number, TEMAIL, SUBJECTID, ID) "
					  + "SELECT ?, ?, ?, ?, SubjectID, 'OSH' "
					  + "FROM SubjectDB "
					  + "WHERE SubjectName = ?";
			}else {//교과
				sql = "INSERT INTO SubjectDB(SubjectName,Subject_STLimit, ID) VALUES(?, ?,'OSH')";
			}
			try {
				psmt = conn.prepareStatement(sql);
				if(searchno == 1 || searchno == 2) {
					psmt.setString(1, EC.getChkNull(getValue("이름"), "이름"));
					psmt.setInt(2, EC.getChkInt(getValue("나이")));
					psmt.setString(3, getValue("전화번호"));		
					psmt.setString(4, getValue("이메일"));	
					if(searchno == 1) {
						psmt.setString(5, getValue("학번"));	
					}else {
						psmt.setString(5, getValue("과목"));
					}
				}else {
					psmt.setString(1, EC.getChkNull(getValue("교과명"), "교과명"));
					psmt.setInt(2, EC.getChkInt(getValue("정원 수")));
				}
				int a = psmt.executeUpdate();
				System.out.println((a == 0 ? "[Error Message] 개설되지 않은 교과입니다. 입력을 취소합니다.":"SQL>"+a+"행이 입력되었습니다"));
			}catch(SQLException e) {EC.getChkErrorCode(e.getErrorCode(), searchno);}
		}//if
		else if(mainMenu == 3) {//수정
			String updateSQL = null;
			printSubMenu(4);
			int updatecol = getMenuNumber();
			if(updatecol != 5) {
				while(1==1) {
					try {
						codeno = EC.getChkInt(getValue("수정할 코드번호"));
						if (codeno > 20000) {
							updateSQL = "UPDATE StudentDB SET ";
							if(updatecol == 1) {
								updateSQL = updateSQL + "sname = '"+getValue("변경할 이름")+"' where StudentID = "+codeno;
							}else if(updatecol == 2) {
								updateSQL = updateSQL + "sage = '"+ EC.getChkInt(getValue("변경할 나이"))+"' where StudentID = "+codeno;
							}else if(updatecol == 3) {
								updateSQL = updateSQL + "sPhone_number = '"+getValue("변경할 전화번호")+"' where StudentID = "+codeno;
							}else if(updatecol == 4) {
								updateSQL = updateSQL + "semail = '"+getValue("변경할 이메일")+"' where StudentID = "+codeno;
							}
						}else if(codeno > 10000 && codeno < 20001){
							updateSQL = "UPDATE TeacherDB SET ";
							if(updatecol == 1) {
								updateSQL = updateSQL + "tname = '"+getValue("변경할 이름")+"' where TeacherID = "+codeno;
							}else if(updatecol == 2) {
								updateSQL = updateSQL + "tage = '"+EC.getChkInt(getValue("변경할 나이"))+"' where TeacherID = "+codeno;
							}else if(updatecol == 3) {
								updateSQL = updateSQL + "tPhone_number = '"+getValue("변경할 전화번호")+"' where TeacherID = "+codeno;
							}else if(updatecol == 4) {
								updateSQL = updateSQL + "temail = '"+getValue("변경할 이메일")+"' where TeacherID = "+codeno;
							}
						}else {
							
						}
						psmt = conn.prepareStatement(updateSQL);
						System.out.println(psmt.executeUpdate()+"행이 수정됨");break;
					}catch(SQLException e) {EC.getChkErrorCode(e.getErrorCode(), searchno);continue;}
				}
			}
		}else if(mainMenu == 4) {//삭제
			try {
				String deleteSQL = "Delete StudentDB WHERE ";
				codeno = EC.getChkInt(getValue("삭제할 코드번호"));
				if (codeno > 20000) {
					deleteSQL = deleteSQL + "StudentID = "+ codeno +" AND "+ (searchno == 1? "SName = '":"STNumber = ") +searchdata +"'";
				}else if(codeno > 10000 && codeno < 20001){
					deleteSQL = "Delete TeacherDB WHERE TeacherID = "+ codeno;
				}			
				psmt = conn.prepareStatement(deleteSQL);
				int updaterow = psmt.executeUpdate();
				if(updaterow == 0) {
					System.out.println("[Error Message] 잘못된 코드번호를 입력하셨습니다.");
				}else {
					System.out.println("[CodeNo : "+codeno+"인 "+searchdata+" Data] "+updaterow+" 행이 삭제되었습니다.");
				}
			}catch(SQLException e) {EC.getChkErrorCode(e.getErrorCode(), searchno);}
		}
	}///////////
	
	
	//수강신청 메소드
	public void enrolment(int searchno) throws SQLException{
		printSubMenu(3);
		int searchno2 = getMenuNumber();
		if(searchno2 > 1) searchno2 = searchno2 + 1;
		
		if(searchno == 1 || searchno == 2) {
			while(1==1) {
				switch(searchno2) {
					case 1: DBSearch(111);break;
					case 3: DBSearch(333);break;
					case 4: break;
					default : System.out.println("서브메뉴에 없는 번호입니다. 다시 입력해주세요?");continue;
				}
				if(chkdata != 0 && (searchno2 == 1 || searchno2 == 3)) {
					if(searchno == 1) {
						int codeno = EC.getChkInt(getValue("수강 신청할 학생의 코드번호"));
						sql = "INSERT INTO StudentSubject(StudentID, SubjectID) "
								+ "select "+codeno+", SubjectID "
								+ "FROM SubjectDB "
								+ "where SubjectName = '"+getValue("과목명")+"'";
					}else if(searchno == 2) {
						int codeno = EC.getChkInt(getValue("수강 취소할 학생의 코드번호"));
						sql = "DELETE FROM StudentSubject "
								+ "WHERE SubjectID IN ( "
								+ "SELECT SubjectID "
								+ "FROM SubjectDB "
								+ "WHERE SubjectName = '"+getValue("과목명")+"' "
								+ ") AND StudentID = "+codeno;
					}
					psmt = conn.prepareStatement(sql);
					int a = psmt.executeUpdate();
					System.out.println((a == 0 ? "[Error Message] 개설되지 않은 교과입니다. 입력을 취소합니다.":"SQL>"+a+"행이 변경되었습니다"));
				}
				break;
			}
		}else {
			System.out.println("성적 조회 메뉴는 아직 구현중입니다. System관리자에게 문의하세요.");
		}
	}/////
	
	
	//파일로 내보내기 메소드
	public void fileSave(int searchno) throws Exception {
	    if (searchno == 1) {
	        sql = "SELECT * FROM ("
	                + "SELECT TNAME 이름, TAGE 나이, TPHONE_NUMBER 전화번호, TEMAIL 이메일, sbdb.SubjectName 담당과목 FROM TeacherDB tdb "
	                + "LEFT JOIN subjectdb sbdb ON tdb.SubjectID = sbdb.SubjectID)";
	    } else if (searchno == 2) {
	        sql = "SELECT s.SName 이름, s.Sage 나이, s.SPhone_number 전화번호, s.Semail 이메일, s.STNumber 학번 from  StudentDB s";
	    } else {
	        sql = "select SubjectID 교과번호, SubjectName 교과명, Subject_STLimit 정원 from SubjectDB";
	    }
	    psmt = conn.prepareStatement(sql);
	    rs = psmt.executeQuery();
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnCount = rsmd.getColumnCount();

	    // 파일로 저장
	    try {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("저장할 파일명을 입력해주세요?");
	        String fileName = sc.nextLine().trim();
	        FileWriter fileWriter = new FileWriter("src/txtfile/"+(searchno == 1?"Teacher_":
	        													   searchno == 2?"Student_":
	        													   "Subject_")+fileName + ".txt");
	        for (int i = 1; i <= columnCount; i++) {
	            fileWriter.write(rsmd.getColumnName(i));
	            if (i < columnCount) {
	                fileWriter.write(",");
	            }
	        }
	        fileWriter.write("\n");
	        while (rs.next()) {
	            for (int i = 1; i <= columnCount; i++) {
	                int columnType = rsmd.getColumnType(i);
	                String columnValue;
	                if (columnType == Types.TIMESTAMP) {
	                    columnValue = rs.getDate(i).toString();
	                } else {
	                    columnValue = rs.getString(i);
	                }
	                fileWriter.write(columnValue == null ? "" : columnValue);
	                if (i < columnCount) {
	                    fileWriter.write(",");
	                }
	            }
	            fileWriter.write("\n");
	        }
	        fileWriter.close();
	        System.out.println("["+(searchno == 1?"Teacher_":
				   					searchno == 2?"Student_":
	        						"Subject_")+ fileName + "] 파일명으로 저장했습니다.");
	    } catch (IOException e) {}
	}
}////class