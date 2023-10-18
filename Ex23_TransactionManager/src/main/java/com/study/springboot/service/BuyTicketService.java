package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.springboot.dao.ITransaction1Dao;
import com.study.springboot.dao.ITransaction2Dao;

@Service
public class BuyTicketService implements IBuyTicketService
{
	// Jdbc 연동을 위한 자동주입
	@Autowired	
	ITransaction1Dao transaction1;
	@Autowired
	ITransaction2Dao transaction2;
	
	// 트랜젝션 처리를 위한 자동 주입
	@Autowired	// 스프링 안에 기본적으로 제공하는 것
	PlatformTransactionManager transactionManager;
	@Autowired
	TransactionDefinition definition;
	// definition : 기본 설정 값을 
	@Override
	public int buy(String consumerId, int amount, String error) {
		
		TransactionStatus status = transactionManager.getTransaction(definition);
		try
		{
			transaction1.pay(consumerId, amount);
			
			if (error.equals("1")) { int n = 10 / 0;}
			
			transaction2.pay(consumerId, amount);
			
			// 여리고 롤백을 함
			transactionManager.commit(status);
			return 1;
		} catch (Exception e)
		{
			System.out.println("[PlatformTransactionManager] Rollback");
			// 에러가 나면 transaction1 안에 들어와있던 데이터도
			// 롤백이 되어 없어짐.
			transactionManager.rollback(status);
			return 0;
		}
	}
}
