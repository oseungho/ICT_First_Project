package console.academyDB;

import java.util.Scanner;

public class ErrorCatch {
	Scanner sc = new Scanner(System.in);

//1] 숫자 유효성 검사 메소드
	
	//직접 입력받은 값 체크
	public int getChkInt(Scanner sc) {
		int intno;
		while(1==1) {
			try {
				intno = Integer.parseInt(sc.nextLine().trim());break;
			}catch(NumberFormatException e) {
				System.out.println("[경고]올바른 값을 입력해주세요!");
			}
		}////while
		return intno;
	}//////////getChkInt
	
	//String 값 체크
	public int getChkInt(String value) {
		int valueInt=0;
		try {
			valueInt = Integer.parseInt(value.trim());
		}catch(NumberFormatException e) {
			while(1==1) {
				System.out.println("[Error Message] 숫자로만 입력해주세요.");
				try {
					valueInt = Integer.parseInt(sc.nextLine().trim());break;
				}catch(NumberFormatException ea) {}
			}
		}
		return valueInt;
	}
	
//2] 각 컬럼 Error check
	public void getChkErrorCode(int errorCode, int searchno) {
		if(errorCode == 1) {//PK중복
			System.out.println("[Error Message] 이미 존재하는 교과번호입니다.");
		}else if(errorCode == 904){
			System.out.println("[Error Message] 다른 Data의 코드번호입니다. 삭제를 취소합니다."); //SQLSyntaxErrorException
		}else if(errorCode == 936) {
			System.out.println("[Error Message] 존재하지 않는 코드번호입니다. 삭제를 취소합니다.");
		}else if(errorCode == 1400) {
			if(searchno == 1 || searchno == 2) {
				System.out.println("[Error Message] 이름에 빈값은 들어갈 수 없습니다.");
			}else {
				System.out.println("[Error Message] 교과명에 빈값은 들어갈 수 없습니다.");						
			}
		}else if(errorCode == 1722){
			System.out.println("[Error Message] 숫자만 입력이 가능합니다.");
		}else if(errorCode == 2291){
			System.out.println("[Error Message] 과목이 개설되지 않았습니다. 교과를 먼저 개설해주세요.");
		}else if(errorCode == 12899){
			System.out.println("[Error Message] 입력값이 너무 커서 입력이 취소됩니다.");
		}else if(errorCode == 17104){
			System.out.println("[Error Message] 올바른 코드값이 아닙니다.");
		}
	}////
	
//3] Null값 검사 메소드
	public String getChkNull(String value, String colname) {
		String searchdata = value;
		while(1==1) {
			if(searchdata.isEmpty()) {
				System.out.println("[Error Message] "+colname+"에 빈 값은 들어갈 수 없습니다. 다시 입력해주세요?");
				searchdata = sc.nextLine().trim();continue;
			}
			break;
		}
		return searchdata;
	}////getChkNull
}
