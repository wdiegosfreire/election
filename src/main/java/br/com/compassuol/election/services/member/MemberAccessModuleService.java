package br.com.compassuol.election.services.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.repositories.MemberRepository;

@Service
public class MemberAccessModuleService extends MemberBaseService implements CommonService {
	@Autowired private MemberRepository memberRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllMembers();
	}

	private void findAllMembers() {
		this.setArtifact("memberList", this.memberRepository.findAll());
	}
}