package kr.or.mrhi.sixClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DataBaseTest {
	// mysql DRIVER URL 정의 되야한다.
	public static final Scanner scan = new Scanner(System.in);
	public static final int INSERT = 1, SEARCH = 2, DELETE = 3, UPDATE = 4, SHOWTB = 5, FINISH = 6;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		boolean flag = false;
		int selcetNumber = 0;
		boolean numberInputContitue = false;
		while (!flag) {
			// 매뉴출력및 번호선택
			selcetNumber = displayMenu();

			switch (selcetNumber) {
			case INSERT:
				phoneBookInsert();// 전화번호부 입력하기
				break;
			case SEARCH:
				phoneBookSearch();// 전화기록부 검색하기
				break;
			case DELETE:
				phoneBookDelete();// 전화기록부 삭제하기
				break;
			case UPDATE:
				phoneBookUpdate();// 전화기록부 수정하기
				break;
			case SHOWTB:
				phoneBookSelect();// 전화기록부 출력하기
				break;
			case FINISH:
				flag = true;
				break;
			default:
				System.out.println("숫자범위초과 다시입력요망망");
				break;
			}// end of switch
		}
		System.out.println("프로그램종료");
	}// end of main

	private static void phoneBookSearch() {
		final int PHONE = 1,Name =2,GENDER=3,EXIT =4;
		List<PhoneBook> list = new ArrayList<PhoneBook>();
		String inputSearchData = null;
		boolean flag = false;
		int searchNumber = 0;
		
		
		while(!flag) {
			
				int selectNumber =displaySearchMenu();
			
			switch (selectNumber) {
			case PHONE:
				System.out.println("전화번호부 입력");
				inputSearchData = scan.nextLine();
				searchNumber = PHONE;
				break;
			case Name:
				System.out.println("이름 입력");
				inputSearchData = scan.nextLine();
				searchNumber = Name;
				break;
			case GENDER:
				System.out.println("성별 입력");
				inputSearchData = scan.nextLine();
				searchNumber = GENDER;
				break;
			case EXIT:
				//함수종료
				return;
			default:
				System.out.println("검색번호범위에 벗어났습니다. (다시입력)");
				return;
			}
			flag =true;
			list=DBControler.phoneBookSearchTBL(inputSearchData,searchNumber);
			if(list == null) {
				System.out.println("검색오류발생");
				return;
			}
			for(PhoneBook pb:list) {
				System.out.println(pb);
			}
		}
		

	}
	// 전화기록부 수정하기

	private static void phoneBookUpdate() {
		System.out.print("수정할 전화번호부을 입력 >>");
		String phoneNumber = scan.nextLine();
		System.out.print("수정할 이름을 입력 >>");
		String name = scan.nextLine();
		
		int count=DBControler.phoneBookUpdateTBL(phoneNumber,name);
		if(count !=0) {
			System.out.println(name+"님이 삭제되었습니다.");
		}else {
			System.out.println(name+"님이 삭제 실패되었습니다.");
		}
		

	}

	// 전화기록부 삭제하기
	private static void phoneBookDelete() {
		System.out.print("삭제할 이름 입력>>");
		String name=scan.nextLine();
		
		int count =DBControler.phoneBookDeleteTBL(name);
		if(count !=0) {
			System.out.println(name+"님이 삭제되었습니다.");
		}else {
			System.out.println(name+"님이 삭제 실패되었습니다.");
		}
		

	}

	// 전화기록부 검색하기
	private static void phoneBookSelect() {
		List<PhoneBook> list = new ArrayList<PhoneBook>();
		list=DBControler.phoneBookSelectTBL();
		if(list.size()<=0) {
			System.out.println("출력할데이터가 없습니다.");
			return;
		}
		for(PhoneBook pb:list) {
			System.out.println(pb.toString());
		}
		
	}

	// 전화번호부 입력하기
	private static void phoneBookInsert() {
		String phoneNumber = null;
		String name = null;
		String gender = null;
		String jop = null;
		String birthday = null;
		
		int age = 0;
		while (true) {
			System.out.print("전화번호를(000-0000-0000) 입력하세요>>");
			phoneNumber = scan.nextLine();
			if (phoneNumber.length() != 13) {
				System.out.println("전화기록부를 13자리를 지켜주세요");
				continue;
			} 
			
			if(phoneNumber.equals("010-5151-5151")) {
				System.out.println("존재하는 폰번호");
				continue;
			}
			break;
		}
		while (true) {
			System.out.print("이름입력>>");
			name = scan.nextLine();
			if (name.length() < 2 || name.length() > 7) {
				System.out.println("이름을 다시입력해주세요");
			} else {
				break;
			}
		}
		while (true) {
			System.out.print("성별(남자,여자)입력>>");
			gender = scan.nextLine();
			if (gender.equals("남자") || gender.equals("여자")) {

				break;
			} else {
				System.out.println("성별을 다시입력해주세요");
			}
		}
		while (true) {
			System.out.print("직업(20글자미만)입력>>");
			jop = scan.nextLine();
			if (jop.length() > 20 || jop.length() == 0) {
				System.out.println("직업을 다시입력해주세요");
			} else {
				break;
			}
		}
		while (true) {
			System.out.print("생년월일(19980721)입력>>");
			birthday = scan.nextLine();
			if (birthday.length() != 8) {
				System.out.println("생년월일을 다시입력해주세요");
			} else {
				int year = Integer.parseInt(birthday.substring(0, 4));
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				age = currentYear - year + 1;
				break;
			}
		}
		PhoneBook phoneBook = new PhoneBook(phoneNumber, name, gender, jop, birthday, age);
		
		int count =DBControler.phoneBookInsert(phoneBook);
		if (count == 1) {
			System.out.println("삽입성공 " + phoneBook.getName());
		} else {
			System.out.println("삽입실패 " + phoneBook.getName());

		}
		

	}

	private static int displayMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		while (!flag) {

			System.out.println("************************************");
			System.out.println("\n1.입력  2.조회  3.삭제  4.수정 5.출력 6.종료 \n");
			System.out.println("************************************");
			System.out.print("번호선택>>");
			try {
				// 번호선택
				selcetNumber = Integer.parseInt(scan.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("숫자입력요망");
				continue;
			} catch (Exception e) {
				System.out.println("숫자입력요망 재입력요망");
				continue;
			}
			break;
		}
		return selcetNumber;

	}
	private static int displaySearchMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		while (!flag) {

			System.out.println("**********************************************");
			System.out.println("\n검색할 1.전화기록부 2.이름 3.성별 4.종료>> \n");
			System.out.println("**********************************************");
			System.out.print("번호선택>>");
			try {
				// 번호선택
				selcetNumber = Integer.parseInt(scan.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("숫자입력요망");
				continue;
			} catch (Exception e) {
				System.out.println("숫자입력요망 재입력요망");
				continue;
			}
			break;
		}
		return selcetNumber;

	}
	private static boolean dupulicatePhoneNumberCheck(String phoneNumber) {
		final int PHONE =1;
		List<PhoneBook> list = new ArrayList<PhoneBook>();
		String inputSearchData = null;
		boolean flag = false;
		int searchNumber = PHONE;
		
		
		
			list=DBControler.phoneBookSearchTBL(inputSearchData,searchNumber);
			if(list.size() > 0) {
				System.out.println("존재하는 핸드폰번호입니다.");
				return true;
			}
			return false;
		}
		

	
}
