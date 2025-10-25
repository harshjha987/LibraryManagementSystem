package com.harsh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.dto.MemberDto;
import com.harsh.service.MemberService;


@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
	
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@PostMapping("/register")
	
	public ResponseEntity<MemberDto> addMember(@RequestBody MemberDto memberDto){
		MemberDto newMember = memberService.addMember(memberDto);
		return new ResponseEntity<>(newMember,HttpStatus.CREATED);
	}
	
	

}
