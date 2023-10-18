package com.study.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator
{
	@Override
	public boolean supports(Class<?> arg0) {
		// 검증할 객체의 클래스 타입 정의
		return ContentDto.class.isAssignableFrom(arg0);
	}
	
	/*
		폼값 검증을 진행하기 위한 메서드로 여기서는 ValidationUtils
		클래스를 통한 검증 방법을 사용하고 있다.
	 */
	@Override		
	public void validate(Object obj, Errors errors) {
		
		ContentDto dto = (ContentDto)obj;
		
//		String sWriter = dto.getWriter();
//		if (sWriter == null || sWriter.trim().isEmpty())
//		{
//			System.out.println("Writer id null or empty");
//			errors.rejectValue("writer", "trouble", "에러남");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty.");
		String sWriter = dto.getWriter();
		if (sWriter.length() < 3)
		{
			errors.rejectValue("writer", "writer is too short.");
		}
		
//		String sContent = dto.getContent();
//		if (sContent == null || sContent.trim().isEmpty())
//		{
//			System.out.println("Content id null or empty");
//			errors.rejectValue("Content", "trouble");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty.");
		
	}
}
