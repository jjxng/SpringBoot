package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// @Repository 어노테이션을 통해 스프링 컨테이너 시작시 자동으로 빈이 생성
@Repository		// 컨포넌트
public class MyUserDAO
{
	// JDBC 작업을 위해 자동주입 받는다.
	@Autowired
	// 자동으로 오라클 안의 객체 연결
	// application.properties의 오라클 정보 사용
	private JdbcTemplate jdbcTemplate;
	
	// 회원목록
	public List<MyUserDTO> listForBeanPropertyRowMapper()
	{
		String query = "select * from myuser";		// 결과가 여러개
		/*
		 	2개 이상의 레코드를 인출하는 select 쿼리문을 실행할 때 query
		 	메서드를 호출한다. 두 번째 인수인 RowMapper가 인출된 레코드를
		 	DTO에 저장한 후 list에 추가하여 반환하게 된다. 즉, ResultSet을
		 	저장하기 위한 반복적인 작업을 자동으로 처리해준다.
		 */
		List<MyUserDTO> list = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<MyUserDTO>(MyUserDTO.class));
				// MyUserDTO => query의 결과를 넣어줌
//		for (MyUserDTO my : list)
//		{
//			System.out.println(my);
//		}
		return list;
	}
}
