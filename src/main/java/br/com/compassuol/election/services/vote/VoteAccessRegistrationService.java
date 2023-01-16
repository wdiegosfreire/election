package br.com.compassuol.election.services.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.repositories.MemberRepository;
import br.com.compassuol.election.repositories.SessionRepository;

@Service
public class VoteAccessRegistrationService extends VoteBaseService implements CommonService {
	@Autowired private MemberRepository memberRepository;
	@Autowired private SessionRepository sessionRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findSessionByIdentity();
		this.findAllMembers();
	}

	private void findSessionByIdentity() {
		this.setArtifact("sessionEntity", this.sessionRepository.findById(this.voteParam.getSession().getIdentity()).get());
	}

	private void findAllMembers() {
		this.setArtifact("memberList", this.memberRepository.findAll());
	}
}