package kr.or.mrhi.sixClass;

import java.util.Objects;

public class PhoneBook {
	private String phoneNumber;//��ȭ��ȣ
	private String name;//�̸�
	private String gender;//����
	private String jop;//����
	private String birthday;//����
	private int age;//����
	
	public PhoneBook(String phoneNumber, String name, String gender, String jop, String birthday, int age) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.gender = gender;
		this.jop = jop;
		this.birthday = birthday;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PhoneBook) {
			PhoneBook book = (PhoneBook)obj;
			return this.phoneNumber.equals(book.getPhoneNumber());
		}
			
		return false;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJop() {
		return jop;
	}

	public void setJop(String jop) {
		this.jop = jop;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		String year = this.birthday.substring(0,4);
		String month = this.birthday.substring(4,6);
		String day = this.birthday.substring(6);
		String strBirthday =year+"��"+month+"��"+day+"��";
		return  phoneNumber + "\t" + name + "\t" + gender + "\t" + jop+ "\t   " + strBirthday + "\t   " + age+"��";
	}
	
	
}
