package console.academy;

public class Teacher extends Person {
	//[멤버변수(필드)]
	String subject;//새롭게 확장한 멤버
	//[인자 생성자]
	public Teacher(String name, int age, String phno, String email, String subject) {
		super(name, age, phno, email);
		this.subject=subject;
	}
	//[멤버 메소드]
	@Override
	String get() {		
		return String.format("%s,과목:%s",super.get(),subject);
	}
	@Override
	void print() {		
		System.out.println(get());
	}
	
}
