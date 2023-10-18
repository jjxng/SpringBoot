package com.study.springboot.dto;

import lombok.Data;

// 회계 장부
@Data
public class Transaction1Dto
{
	private String consumerId;
	private int amount;
}
