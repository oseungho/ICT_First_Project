package console.academy;

import java.io.Serializable;

public class Person implements Serializable{
	
	//필드
	public String name;
	public int age;
	public String phno;
	public String email;
	//[기본 생성자]
	public Person() {}
	//[인자 생성자]
	public Person(String name, int age, String phno, String email) {		
		this.name = name;
		this.age = age;
		this.phno = phno;
		this.email = email;
	}
	//[멤버 메소드]
	String get() {
		return String.format("이름:%s,나이:%s,전화번호:%s,이메일:%s",name,age,phno,email);
	}
	void print() {
		System.out.println(get());
	}

}
