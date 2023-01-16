package br.com.compassuol.election.services.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.exceptions.RequiredFieldNotFoundException;
import br.com.compassuol.election.repositories.MemberRepository;

@Service
public class MemberExecuteRegistrationService extends MemberBaseService implements CommonService {
	@Autowired private MemberRepository memberRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveMember();
		this.findAllMembers();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("memberRegistered", this.memberParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.memberParam.getName() == null || this.memberParam.getName().equals(""))
			errorList.add("Please, enter title.");

		if (this.memberParam.getCpf() == null || this.memberParam.getCpf().equals(""))
			errorList.add("Please, enter description.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveMember() throws BaseException {
		this.memberRepository.save(this.memberParam);
	}

	private void findAllMembers() {
		this.setArtifact("memberList", this.memberRepository.findAll());
	}
}