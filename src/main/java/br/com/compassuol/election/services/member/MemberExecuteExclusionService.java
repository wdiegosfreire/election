package br.com.compassuol.election.services.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.MemberEntity;
import br.com.compassuol.election.exceptions.DataForExclusionNotFoundException;
import br.com.compassuol.election.repositories.MemberRepository;

@Service
public class MemberExecuteExclusionService extends MemberBaseService implements CommonService {
	@Autowired private MemberRepository memberRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.deleteMember();
		this.findAllMembers();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("memberRegistered", this.memberParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForExclusionNotFoundException {
		MemberEntity member = this.memberRepository.findByIdentity(this.memberParam.getIdentity()).get();

		if (member == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteMember() throws BaseException {
		this.memberRepository.delete(this.memberParam);
	}

	private void findAllMembers() {
		this.setArtifact("memberList", this.memberRepository.findAll());
	}
}