package com.study.springboot;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
/*
 	어노테이션을 통한 폼값 검증을 위해서는 추가적인 의존설정(디펜던시)이
 	필요하다.
 */
// getter/setter 설정을 위해 @Data 어노테이션을 부착한다.
@Data
public class ContentDto
{
	private int id;
	// 폼값이 null일 때 메세지를 출력한다.
	@NotNull(message="writer is null.")
	
	// 빈값일 때 출력한다.
	@NotEmpty(message="writer is empty.")
	
	// 입력값의 길이를 최소 ~최대로 지정한다. 해당 범위를 벗어나면 메세지 출력
	@Size(min=3, max=10, message="writer min 3, max 10.")
	private String writer;
	
	@NotNull(message="content is null.")
	@NotEmpty(message="content is empty.")
	private String content;
}
