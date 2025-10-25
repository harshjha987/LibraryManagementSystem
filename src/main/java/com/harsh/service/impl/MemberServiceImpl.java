package com.harsh.service.impl;

import com.harsh.dto.MemberDto;
import com.harsh.entity.Member;
import com.harsh.exceptions.MemberNotFoundException;
import com.harsh.repository.MemberRepository;
import com.harsh.service.MemberService;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService {

	private MemberRepository memberRepo;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private MemberDto convertToDto(Member member) {
		MemberDto dto = new MemberDto();
		dto.setId(member.getId());
		dto.setName(member.getName());
		dto.setEmail(member.getEmail());
		dto.setPhoneNumber(member.getPhoneNumber());
		dto.setAddress(member.getAddress());
		dto.setActive(member.isActive());
		// Don't send password in response
		return dto;
	}

	public MemberServiceImpl(MemberRepository memberRepo) {
		super();
		this.memberRepo = memberRepo;
	}

	@Override
	public MemberDto addMember(MemberDto memberDto) {
		if (memberRepo.existsByEmail(memberDto.getEmail())) {
			throw new MemberNotFoundException("Email already exists!"); // you can replace this with custom exception
																		// later
		}
		Member member = new Member();
		member.setName(memberDto.getName());
		member.setEmail(memberDto.getEmail());
		member.setPhoneNumber(memberDto.getPhoneNumber());
		member.setAddress(memberDto.getAddress());
		member.setActive(true); // default active
		member.setPassword(passwordEncoder.encode(memberDto.getPassword())); // encrypt password

		Member savedMember = memberRepo.save(member);
		return convertToDto(savedMember);
	}
	
	@Override
	public List<MemberDto>getAllMembers(){
		return memberRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
	}

	@Override
	public MemberDto getMemberById(Long id) {
		Member member = memberRepo.findById(id)
				.orElseThrow(() -> new MemberNotFoundException("Member does not exist with this id"));

		return convertToDto(member);
	}
	
	@Override
	public void deleteMember(Long id) {
		Member member = memberRepo.findById(id)
				.orElseThrow(() -> new MemberNotFoundException("Member does not exist with this id"));
		memberRepo.delete(member);
		
	}

}
