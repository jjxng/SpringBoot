package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ITransaction1Dao;
import com.study.springboot.dao.ITransaction2Dao;

// @Service 어노테이션을 지정하면 이클래스를 반으로 사용한다라는 의미
@Service
public class BuyTicketService implements IBuyTicketService
{
	// NEW는 사용하지 않고 자동으로 대입
	@Autowired	// 자동 주입을 받아 자동으로 대입
	ITransaction1Dao transaction1;
	@Autowired
	ITransaction2Dao transaction2;
	
	@Override
	public int buy(String consumerId, int amount, String error) {

		/*
		 	상황 : 현장에서는 표가 발행 되었는데 전산에 등록이 안
		 		될 때
		 */
		try
		{
			// dao 변수인 transaction1에서 pay() 메서드를 호출
			transaction1.pay(consumerId, amount);	// 현장 판매처 상황
			
			// 의도적 에러 발생
			if (error.equals("1")) { int n = 10 / 0;}
			// dao 변수인 transaction2에서 pay() 메서드를 호출
			transaction2.pay(consumerId, amount);	// 회계장부 상황
			/*
			 	에러1이 들어오면 이 테이블에는 저장이 안 됨.
			 	이런 일이 생기면 안되므로 트렌젝션이 필요함.
			 	transaction1부터 저장이 안돼도 롤백을 해야 함.
			 	
			 */
			return 1;
		} catch (Exception e)
		{
			return 0;
		}
	}
}
