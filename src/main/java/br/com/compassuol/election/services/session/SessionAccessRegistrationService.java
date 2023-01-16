package br.com.compassuol.election.services.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.repositories.MemberRepository;
import br.com.compassuol.election.repositories.SubjectRepository;

@Service
public class SessionAccessRegistrationService extends SessionBaseService implements CommonService {
	@Autowired private MemberRepository memberRepository;
	@Autowired private SubjectRepository subjectRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllSubjects();
		this.findAllMembers();
	}

	private void findAllSubjects() {
		this.setArtifact("subjectList", this.subjectRepository.findAll());
	}

	private void findAllMembers() {
		this.setArtifact("memberList", this.memberRepository.findAll());
	}
}