package com.study.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 검색된 클래스를 빈으로 등록할 때, 클래스의 첫 글자를 소문자로 바꾼
// 이름을 빈의 이름으로 사용한다.
@Component
public class Member
{
	// 멤버변수에 "홍길동"이라는 초기값을 부여한다.
	// 이때 setter를 통해 값이 설정된다.
	@Value("홍길동")
	private String name;
	@Value("도사")
	private String nickname;
	
	/*
 		객체타입의 멤버변수는 자동으로 주입받는다. 이때, @Qualifier
 		어노테이션이 있으면 이름까지 지정하여 주입받게 된다. 만약
 		부착하지 않으면 해당 타입으로 생성된 빈이 있는지 찾은 후
 		주입받는다.
	*/
	@Autowired
	@Qualifier("printerA")
	private Printer printer;
	
	public Member() { }

	public Member(String name, String nickname, Printer printer)
	{
		super();
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}

	// 인수 생성자
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public Printer getPrinter()
	{
		return printer;
	}

	public void setPrinter(Printer printer)
	{
		this.printer = printer;
	}

	public void print()
	{
		printer.print("Hello " + name + " : " + nickname);
	}
}
