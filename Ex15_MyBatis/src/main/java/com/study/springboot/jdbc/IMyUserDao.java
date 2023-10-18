package com.study.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

/*
 	컨트롤러와 매퍼(XML파일) 사이에서 매개역할을 하는 서비스
 	인터페이스로 JdbcTemplate에서는 @Service를 사용하지만 마이바티스는
 	@Mapper를 사용한다.
 */
@Mapper
public interface IMyUserDao
{
	ArrayList<MyUserDTO> getUser();
}
