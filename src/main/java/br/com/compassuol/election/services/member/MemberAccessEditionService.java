package br.com.compassuol.election.services.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.MemberEntity;
import br.com.compassuol.election.exceptions.DataForEditionNotFoundException;
import br.com.compassuol.election.repositories.MemberRepository;

@Service
public class MemberAccessEditionService extends MemberBaseService implements CommonService {
	@Autowired private MemberRepository memberRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
	}

	private void findById() throws DataForEditionNotFoundException {
		MemberEntity member = this.memberRepository.findByIdentity(this.memberParam.getIdentity()).get();

		if (member == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("member", member);
	}
}