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
	// mysql DRIVER URL ���� �Ǿ��Ѵ�.
	public static final Scanner scan = new Scanner(System.in);
	public static final int INSERT = 1, SEARCH = 2, DELETE = 3, UPDATE = 4, SHOWTB = 5, FINISH = 6;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		boolean flag = false;
		int selcetNumber = 0;
		boolean numberInputContitue = false;
		while (!flag) {
			// �Ŵ���¹� ��ȣ����
			selcetNumber = displayMenu();

			switch (selcetNumber) {
			case INSERT:
				phoneBookInsert();// ��ȭ��ȣ�� �Է��ϱ�
				break;
			case SEARCH:
				phoneBookSearch();// ��ȭ��Ϻ� �˻��ϱ�
				break;
			case DELETE:
				phoneBookDelete();// ��ȭ��Ϻ� �����ϱ�
				break;
			case UPDATE:
				phoneBookUpdate();// ��ȭ��Ϻ� �����ϱ�
				break;
			case SHOWTB:
				phoneBookSelect();// ��ȭ��Ϻ� ����ϱ�
				break;
			case FINISH:
				flag = true;
				break;
			default:
				System.out.println("���ڹ����ʰ� �ٽ��Է¿����");
				break;
			}// end of switch
		}
		System.out.println("���α׷�����");
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
				System.out.println("��ȭ��ȣ�� �Է�");
				inputSearchData = scan.nextLine();
				searchNumber = PHONE;
				break;
			case Name:
				System.out.println("�̸� �Է�");
				inputSearchData = scan.nextLine();
				searchNumber = Name;
				break;
			case GENDER:
				System.out.println("���� �Է�");
				inputSearchData = scan.nextLine();
				searchNumber = GENDER;
				break;
			case EXIT:
				//�Լ�����
				return;
			default:
				System.out.println("�˻���ȣ������ ������ϴ�. (�ٽ��Է�)");
				return;
			}
			flag =true;
			list=DBControler.phoneBookSearchTBL(inputSearchData,searchNumber);
			if(list == null) {
				System.out.println("�˻������߻�");
				return;
			}
			for(PhoneBook pb:list) {
				System.out.println(pb);
			}
		}
		

	}
	// ��ȭ��Ϻ� �����ϱ�

	private static void phoneBookUpdate() {
		System.out.print("������ ��ȭ��ȣ���� �Է� >>");
		String phoneNumber = scan.nextLine();
		System.out.print("������ �̸��� �Է� >>");
		String name = scan.nextLine();
		
		int count=DBControler.phoneBookUpdateTBL(phoneNumber,name);
		if(count !=0) {
			System.out.println(name+"���� �����Ǿ����ϴ�.");
		}else {
			System.out.println(name+"���� ���� ���еǾ����ϴ�.");
		}
		

	}

	// ��ȭ��Ϻ� �����ϱ�
	private static void phoneBookDelete() {
		System.out.print("������ �̸� �Է�>>");
		String name=scan.nextLine();
		
		int count =DBControler.phoneBookDeleteTBL(name);
		if(count !=0) {
			System.out.println(name+"���� �����Ǿ����ϴ�.");
		}else {
			System.out.println(name+"���� ���� ���еǾ����ϴ�.");
		}
		

	}

	// ��ȭ��Ϻ� �˻��ϱ�
	private static void phoneBookSelect() {
		List<PhoneBook> list = new ArrayList<PhoneBook>();
		list=DBControler.phoneBookSelectTBL();
		if(list.size()<=0) {
			System.out.println("����ҵ����Ͱ� �����ϴ�.");
			return;
		}
		for(PhoneBook pb:list) {
			System.out.println(pb.toString());
		}
		
	}

	// ��ȭ��ȣ�� �Է��ϱ�
	private static void phoneBookInsert() {
		String phoneNumber = null;
		String name = null;
		String gender = null;
		String jop = null;
		String birthday = null;
		
		int age = 0;
		while (true) {
			System.out.print("��ȭ��ȣ��(000-0000-0000) �Է��ϼ���>>");
			phoneNumber = scan.nextLine();
			if (phoneNumber.length() != 13) {
				System.out.println("��ȭ��Ϻθ� 13�ڸ��� �����ּ���");
				continue;
			} 
			
			if(phoneNumber.equals("010-5151-5151")) {
				System.out.println("�����ϴ� ����ȣ");
				continue;
			}
			break;
		}
		while (true) {
			System.out.print("�̸��Է�>>");
			name = scan.nextLine();
			if (name.length() < 2 || name.length() > 7) {
				System.out.println("�̸��� �ٽ��Է����ּ���");
			} else {
				break;
			}
		}
		while (true) {
			System.out.print("����(����,����)�Է�>>");
			gender = scan.nextLine();
			if (gender.equals("����") || gender.equals("����")) {

				break;
			} else {
				System.out.println("������ �ٽ��Է����ּ���");
			}
		}
		while (true) {
			System.out.print("����(20���ڹ̸�)�Է�>>");
			jop = scan.nextLine();
			if (jop.length() > 20 || jop.length() == 0) {
				System.out.println("������ �ٽ��Է����ּ���");
			} else {
				break;
			}
		}
		while (true) {
			System.out.print("�������(19980721)�Է�>>");
			birthday = scan.nextLine();
			if (birthday.length() != 8) {
				System.out.println("��������� �ٽ��Է����ּ���");
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
			System.out.println("���Լ��� " + phoneBook.getName());
		} else {
			System.out.println("���Խ��� " + phoneBook.getName());

		}
		

	}

	private static int displayMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		while (!flag) {

			System.out.println("************************************");
			System.out.println("\n1.�Է�  2.��ȸ  3.����  4.���� 5.��� 6.���� \n");
			System.out.println("************************************");
			System.out.print("��ȣ����>>");
			try {
				// ��ȣ����
				selcetNumber = Integer.parseInt(scan.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("�����Է¿��");
				continue;
			} catch (Exception e) {
				System.out.println("�����Է¿�� ���Է¿��");
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
			System.out.println("\n�˻��� 1.��ȭ��Ϻ� 2.�̸� 3.���� 4.����>> \n");
			System.out.println("**********************************************");
			System.out.print("��ȣ����>>");
			try {
				// ��ȣ����
				selcetNumber = Integer.parseInt(scan.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("�����Է¿��");
				continue;
			} catch (Exception e) {
				System.out.println("�����Է¿�� ���Է¿��");
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
				System.out.println("�����ϴ� �ڵ�����ȣ�Դϴ�.");
				return true;
			}
			return false;
		}
		

	
}
