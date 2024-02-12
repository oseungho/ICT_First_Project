package console.academy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import collection20.Address;
import common.utils.CommonUtils;
import console.academy.Person;
import console.academy.Student;
import console.academy.Teacher;
import console.academyDB.ErrorCatch;
public class AcademyLogicOSH {
	Scanner sc = new Scanner(System.in);
	String info, fileName = null;
	String[] title = {"이름", "나이", "전화번호", "이메일", "학번", "과목"};
	int searchno, chk, saveYN;
	StringBuilder sbFile = new StringBuilder();
	ErrorCatch EC = new ErrorCatch();
//[멤버 상수]
	public static final int MAX_PERSONS=100;
//[멤버 변수]
	List<Person> person;
	
//[생성자]
	public AcademyLogicOSH() {
		person = new Vector<Person>();
		showFileList();
		loadPerson();
	}////
	// 숫자 유효성 검사 메소드
	public int getChkInt(Scanner sc) {
		int intno;
		while(true) {
			try {
				intno = Integer.parseInt(sc.nextLine().trim());break;
			}
			catch(NumberFormatException e) {
				System.out.println("[경고]올바른 값을 입력해주세요!");
			}
		}////while
		return intno;
	}//////////getChkInt
	
	//1]메뉴 출력용 메소드
	public void printMainMenu() {
		System.out.println("========Main Menu========");
		System.out.println("1.입력 2.출력 3.수정 4.삭제 5.검색 6.파일저장 9.종료");
		System.out.println("=========================");
		System.out.println("메인 메뉴 번호를 입력하세요?");
	}///////printMainMenu()
	
	//2]메뉴 번호 입력용 메소드
	public int getMenuNumber() {	
		return getChkInt(sc);
	}/////////getMenuNumber
	
	//3]메뉴 번호에 따른 분기용 메소드
	public void seperateMainMenu(int mainMenu) throws IOException {
		switch(mainMenu) {
			case 1://입력
				while(true) {
					printSubMenu(0);
					searchno=getMenuNumber();
					if(searchno==3) break;
					switch(searchno) {
						case 1:
						case 2:setPerson(searchno);break;
						default:System.out.println("서브메뉴에 없는 번호입니다");
					}//switch				
				}///while
				break;
			case 2: //출력
				printPerson(); break;
			case 3: //수정
				if(getsubNumber() == 0) break;
				updatePerson();break;
			case 4: //삭제
				if(getsubNumber() == 0) break;
				deletePerson(); break;
			case 5: //검색
				if(getsubNumber() == 0) break;
				findPersonByName();break;
			case 6: //파일저장
				savePerson();break;
			case 7: //파일변경
				showFileList();
				loadPerson();break;
			case 9: //종료
					System.out.println("프로그램을 종료합니다");
					System.exit(0); break;
			default:
				System.out.println("메뉴에 없는 번호입니다");
		}//switch
	}///////////////////seperateMainMenu
	
	//4]서브메뉴 출력용 메소드
	private void printSubMenu(int subno) {
		if(subno == 0) {
			System.out.println("++++++++++입력 서브 메뉴++++++++++");
			System.out.println("1.학생 2.교사 3.메인메뉴로 이동");
			System.out.println("+++++++++++++++++++++++++++++");
			System.out.println("서브 메뉴번호를 입력하세요?");		
		}else if(subno == 1) {
			System.out.println("++++++++++조회 서브 메뉴++++++++++");
			System.out.println("1.이름으로 조회 2.과목으로 조회 3.학번으로 조회 4.메인메뉴로 이동");
			System.out.println("+++++++++++++++++++++++++++++");
			System.out.println("서브 메뉴번호를 입력하세요?");		
		}
	}////////////printSubMenu
	
	//5]서브메뉴에 따른 학생 및 교사 데이타 입력용 메소드
	private void setPerson(int subMenu) {
		//정원이 찼는지 여부 판단]
		if(person.size() == MAX_PERSONS) {
			System.out.println("정원이 찼어요.. 더 이상 입력할 수 없어요");
			return;
		}
		//정원이 안 찬 경우, 즉 index가 -1이 아닌 경우..
		System.out.println(title[0]+"을 입력해주세요?");		
		String name = sc.nextLine().trim();

		System.out.println(title[1]+"을 입력해주세요?");		
		int age = getChkInt(sc);
		
		System.out.println(title[2]+"을 입력해주세요?");		
		String phno = sc.nextLine().trim();
		
		System.out.println(title[3]+"을 입력해주세요?");		
		String email = sc.nextLine().trim();
		
		switch(subMenu) {
			case 1: //학생인 경우
				System.out.println(title[4]+"을 입력해주세요?");
				String stNumber = sc.nextLine().trim();
				person.add(new Student(name, age, phno, email, stNumber));
				break;
			default: //교사인 경우
				System.out.println(title[5]+"을 입력해주세요?");
				String subject= sc.nextLine().trim();
				person.add(new Teacher(name, age, phno, email, subject));		
		}/////switch
		System.out.println("입력이 완료되었습니다.");
	}/////////setPerson
	
	//5-1] 서브메뉴 출력 후 서브메뉴 번호 입력용 메소드
	public int getsubNumber() {
		printSubMenu(1);
		int subMenu=getMenuNumber();
		chk = subMenu;
		while(true) {
			if(subMenu == 4) return 0;
			switch(subMenu) {
				case 1: return 1;
				case 2: return 1;
				case 3: return 1;
				default : System.out.println("서브메뉴에 없는 번호입니다.");
			}
		}
	}/////////getsubNumber
	
	//6]출력용 메소드
	private void printPerson() {
		System.out.println("[학생/교사 구분업이 출력]");
		Map<Character, List<Person>> sortedListfile = new TreeMap<>();
		for(Person p:person) {
			char consonant	= CommonUtils.getInitialConsonant(p.name);
			if(consonant == '0') {
				System.out.println("한글명이 아닙니다");
				continue;
			}
			//
			if (!sortedListfile.containsKey(consonant)) {
	            sortedListfile.put(consonant, new ArrayList<>());
	        }

	        sortedListfile.get(consonant).add(new Person(p.name, p.age, p.phno, p.email));
		}
		for (Map.Entry<Character, List<Person>> entry : sortedListfile.entrySet()) {
	        System.out.println(String.format("[%c로 시작하는 명단]", entry.getKey()));
	        for (Person value : entry.getValue()) {
	            System.out.println(value.get());
	        }
	    }
		System.out.println("\n[학생/교사 구분해서 출력]");
		System.out.println("===================");
		StringBuffer student = new StringBuffer("[학생 목록]\r\n");
		StringBuffer teacher = new StringBuffer("[교사 목록]\r\n");
		
		for(Person p:person) {
			if(p instanceof Student)student.append(p.get()+"\r\n");
			else teacher.append(p.get()+"\r\n");
		}
		System.out.println(student.toString()+teacher);	
	}////pritnPerson
	
	//7]검색하는 메소드
	private List<Person> searchPerson(String search) {
		System.out.println(search+"할 정보을 입력하세요?");
		info = sc.nextLine().trim();
		
		List<Person> searchResults = new ArrayList<>();
		
		for(Person p:person) {
			if(p instanceof Student && chk == 3) {
				if(((Student)p).stNumber.equals(info)) {
					searchResults.add(p);
				}
			}else if(p instanceof Teacher && chk == 2) {
				if(((Teacher)p).subject.equals(info)) {
					searchResults.add(p);
					System.out.println("검색결과를 찾았어요");
				}
			}else if(p.name.equals(info) && chk ==1) {
				searchResults.add(p);
			}
		}
		if(searchResults.isEmpty()) {
			System.out.println(info+"로(으로) 검색된 정보가 없어요");
		}
		return searchResults;
	}/////////////searchPerson
	
	//8]검색용 메소드
	private void findPersonByName() {
		List<Person> searchResults = searchPerson("검색");
		if(!searchResults.isEmpty()) {
			System.out.println(String.format("[%s로 검색한 결과]", info));
			for(Person findPerson : searchResults) {
				if((chk==2 && findPerson instanceof Teacher) || 
						(chk == 3 && findPerson instanceof Student) ||
						chk == 1) {
					findPerson.print();					
				}
			}
		}
	}/////////findPersonByName
	
	//9]수정용 메소드
	private void updatePerson() {
		List<Person> searchResults = searchPerson("수정");
		if(!searchResults.isEmpty()) {
			System.out.println("수정할 대상을 선택하세요?");
			for(int i =0; i<searchResults.size(); i++) {
				Person currentPerson = searchResults.get(i);
				System.out.printf("%d. [%s, %s, %s, %s, %s]\n", i+1, 
						currentPerson.name,
						currentPerson.age,
						currentPerson.phno,
						currentPerson.email,
						(currentPerson instanceof Student ? ((Student)currentPerson).stNumber : ((Teacher)currentPerson).subject));
			}
			
			int selectedIndex = getChkInt(sc) -1;
			
			if(selectedIndex >=0 && selectedIndex < searchResults.size()) {
				Person findPerson = searchResults.get(selectedIndex);
				
				System.out.printf("[현재 나이 : %s] 수정할 나이를 입력해주세요?\n", findPerson.age);
				findPerson.age = getChkInt(sc);
				System.out.printf("[현재 전화번호 : %s] 수정할 전화번호를 입력해주세요?\n", findPerson.phno);
				findPerson.phno = sc.nextLine().trim();
				System.out.printf("[현재 이메일 : %s] 수정할 이메일를 입력해주세요?\n", findPerson.email);
				findPerson.email = sc.nextLine().trim();
				if(findPerson instanceof Student) {
					System.out.printf("[현재 학번 : %s] 수정할 학번을 입력해주세요?\n",((Student)findPerson).stNumber);
					((Student)findPerson).stNumber = sc.nextLine().trim();
				}else{
					System.out.printf("[현재 과목 : %s] 수정할 과목을 입력해주세요?\n",((Teacher)findPerson).subject);
					((Teacher)findPerson).subject = sc.nextLine().trim();
				}
				System.out.printf("[%s가(이) 아래와 같이 수정되었습니다.]\n", findPerson.name);
				findPerson.print();
			}
			else {
				System.out.println("유효하지 않은 선택입니다.");
			}
		}else {
			System.out.println("검색 결과가 없습니다.");
		}
	}///////////////////updatePerson
	//10]삭제용
	private void deletePerson() {
		List<Person> searchResults = searchPerson("삭제");
		if(!searchResults.isEmpty()) {
			System.out.println("삭제할 대상을 선택하세요?");
			for(int i =0; i<searchResults.size(); i++) {
				Person currentPerson = searchResults.get(i);
				System.out.printf("%d. [(%s) 이름 : %s, 나이 : %s, 학번/과목 : %s]\n", i+1,
						(currentPerson instanceof Student ? "학생":"선생님"),
						currentPerson.name,
						currentPerson.age,
						(currentPerson instanceof Student ? ((Student)currentPerson).stNumber : ((Teacher)currentPerson).subject));
			}
			
			int selectedIndex = getChkInt(sc) -1;;
			
			if(selectedIndex >=0 && selectedIndex < searchResults.size()) {
				Person findPerson = searchResults.get(selectedIndex);
				System.out.println("삭제하시겠습니까?\n[예 : 1 | 아니요 : 2]");
				if(Integer.parseInt(sc.nextLine())== 1) {
					person.remove(findPerson);
					System.out.println(String.format("[%s가 삭제되었습니다]", findPerson.name));
				}else {
					System.out.println("[삭제가 취소되었습니다.");
				}
			}
		}
	}////deletePerson
	
	private void savePerson() {// 덮어쓰기... 추가..
		//컬렉션에 객체가 저장되었는지 판단
		if(person.isEmpty()) {
			System.out.println("파일로 저장할 명단이 없어요");
			return;
		}
		//컬렉션에 저장된 객체가 있는 경우
		PrintWriter out = null;
		if(fileName == null) {
			System.out.println("저장할 파일명을 입력해주세요?");
			fileName = sc.nextLine();
			System.out.println("입력하신 ["+fileName+"]으로 저장하시겠습니까?\n[예 : 1 | 아니요 : 2]");
			saveYN = getChkInt(sc);
		}else {
			System.out.println("최근 입력하신 ["+fileName+"] 파일명으로 저장하시겠습니까?\n[예 : 1 | 아니요 : 2]");
			saveYN = getChkInt(sc);
			if(saveYN == 2) {
				System.out.println("저장할 파일명을 입력해주세요?");
				fileName = sc.nextLine();
				System.out.println("입력하신 ["+fileName+"]으로 저장하시겠습니까?\n[예 : 1 | 아니요 : 2]");
				saveYN = getChkInt(sc);
			}
		}
		try {
			if(saveYN == 1) {
				out = new PrintWriter(new FileWriter("src/txtfile/Integration_"+fileName+".txt"));
				//컬렉션에 저장된 데이터를 파일(out)로 출력
				for(Person per:person) out.println(per.get());
				System.out.println("파일이 저장되었습니다.");
			}else {
				System.out.println("파일 저장이 취소되었습니다.");
			}
		}
		catch(IOException e) {
			// 오류 메세지는 관리자모드일때만 보여주는 것도 좋을 것 같음
			System.out.println("파일 저장시 오류입니다."+e.getMessage());
		}
		finally {
			if(out != null) out.close();
		}		
	}////////savePerson
	
	private void loadPerson() {
		BufferedReader br = null;
		Pattern pattern;
		if(!sbFile.isEmpty()) {
			person = new Vector<Person>();
			System.out.println("가져올 파일명을 입력해주세요?\n[파일명 가져오기 취소 : exit]");
			fileName = sc.nextLine();
			if(!fileName.equals("exit")) {
				try {				
					br = new BufferedReader(new FileReader("src/txtfile/"+fileName+".txt"));				
					//정규 표현식 버전용 코드 추가
					if(fileName.startsWith("Integration_")) {
						pattern = Pattern.compile("이름:([가-힣]{2,}),나이:([0-9]{1,3}),전화번호:([0-9\\\\\\\\-]*),이메일:([A-Za-z0-9\\\\\\\\@\\\\\\\\.]*),(과목|학번):(.+)");
					}else {
						pattern = Pattern.compile("([0-9A-Za-z가-힣]{1,}),([0-9]{1,3}),([0-9\\\\-]*),([A-Za-z0-9\\\\@\\\\.]*),([0-9A-Za-z가-힣]*)");									
					}
					
					String line;
					while((line = br.readLine()) != null) {
						//정규표현식 버전
						Matcher matcher = pattern.matcher(line);
						if(matcher.matches()) {
							String name = matcher.group(1);
							int age = Integer.parseInt(matcher.group(2));
							String phno = matcher.group(3);
							String email = matcher.group(4);							
							String extend = matcher.group(5);
							if(fileName.startsWith("Integration_")) {
								if(line.indexOf("학번") != -1) person.add(new Student(name, age, phno, email, extend));
								else person.add(new Teacher(name, age, phno, email, extend));
							}
							else if(fileName.startsWith("Teacher_"))person.add(new Teacher(name, age, phno, email, extend));
							else if(fileName.startsWith("Student_"))person.add(new Student(name, age, phno, email, extend));
						}
					}////while
				} catch (FileNotFoundException e) {
					System.out.println("파일이 존재하지 않아요");
				} catch(IOException e) {
					System.out.println("파일 읽기 시 오류");
				}
				finally {
					try {
						if(br != null) br.close();
					}
					catch(IOException e){System.out.println("파일 닫기 시 오류");}
				}
			}
			else {
				System.out.println("파일 불러오기를 취소합니다.");
			}
		}else {
			System.out.println("==================[불러올 수 있는 파일이 없습니다.]==================");
		}
	}////////loadPerson
	
	
//디렉토리 내 txt파일 보여주기
	private void showFileList() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a h:mm");
		sbFile = new StringBuilder();
		File f = new File("src/txtfile");
		File[] files = f.listFiles();
		
		for(File file : files) {
			//파일명]
			String name = file.getName();
			//최근 수정된 시간]
			String lastModified = dateFormat.format(new Date(file.lastModified()));
			//천단위 콤마
			NumberFormat nf1 = NumberFormat.getInstance(Locale.KOREA);
			if(file.isFile()) {//파일인 경우
				//파일 유형
				String ext = name.substring(name.lastIndexOf(".")+1).toUpperCase();
				//파일 크기]
				int size = (int)(Math.ceil(file.length()/1024.0));
				if(ext.equals("TXT")) {
					sbFile.append(String.format("%-50s%-32s%-14s%sKB\n", name,lastModified,ext,nf1.format(size)));
				}
			}
		}

		if(!sbFile.isEmpty()) {
			printLine(106, '=');
			System.out.println(String.format("%-50s%-32s%-14s%s","이름","수정한 날짜","유형","크기"));
			printLine(106, '=');
			System.out.println(sbFile);
			printLine(106, '=');
		}
	}/////////////showFileList
	
	public static void printLine(int size,char symbol) {
		for(int i=0;i<size;i++) {
			System.out.print(symbol);
		}
		System.out.println();
	}///////////////////////////
}////class