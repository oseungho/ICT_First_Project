package console.academyDB;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import common.utils.CommonUtils;
import console.academy.AcademyLogicOSH;

public class ICAcademyApp {
	public static void main(String[] args) throws Exception {

		//컬렉션 사용]
		System.out.println("모드를 선택하세요? (DB모드 : 1 | 파일모드 : 2)");
		Scanner sc = new Scanner(System.in);
		int modeno = sc.nextInt();
		if(modeno == 1) {
			System.out.println("[DB Version]");
			ICLogin icl = new ICLogin();			
			if(icl.DBLogin()) {
				ICAcademyLogicOSH logic = new ICAcademyLogicOSH();
				while(true) {
					//1.메인 메뉴 출력
					logic.printMainMenu();
					//2.메인메뉴 번호 입력받기
					int mainMenu=logic.getMenuNumber();
					//3. 메인메뉴에 따른 분기
					logic.seperateMainMenu(mainMenu);
				}
			}
			System.out.println("잘못된 계정 정보입니다. 시스템을 종료합니다.");
		}else {
			System.out.println("[File Version]");
			AcademyLogicOSH logic = new AcademyLogicOSH();
			while(true) {
				//1.메인 메뉴 출력
				logic.printMainMenu();
				//2.메인메뉴 번호 입력받기
				int mainMenu=logic.getMenuNumber();
				//3. 메인메뉴에 따른 분기
				logic.seperateMainMenu(mainMenu);
			}
		}
	}////main
}///////class

