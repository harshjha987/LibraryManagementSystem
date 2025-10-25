package com.harsh.service;

import java.util.List;

import com.harsh.dto.MemberDto;

public interface MemberService {
	
	MemberDto addMember(MemberDto memberDto);

    List<MemberDto> getAllMembers();

    MemberDto getMemberById(Long id);

    void deleteMember(Long id);

}
