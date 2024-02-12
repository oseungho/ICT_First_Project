package console.academy;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

public class AcademyApp {
	
	public static void main(String[] args) throws IOException{	
		//컬렉션 사용]
		AcademyLogicOSH logic = new AcademyLogicOSH();
		while(true) {
			//1.메인 메뉴 출력
			logic.printMainMenu();
			//2.메인메뉴 번호 입력받기
			int mainMenu=logic.getMenuNumber();
			//3. 메인메뉴에 따른 분기
			logic.seperateMainMenu(mainMenu);
		}
	}////main

}///////class
