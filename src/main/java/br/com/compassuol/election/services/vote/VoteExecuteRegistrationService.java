package br.com.compassuol.election.services.vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.exceptions.RequiredFieldNotFoundException;
import br.com.compassuol.election.repositories.VoteRepository;

@Service
public class VoteExecuteRegistrationService extends VoteBaseService implements CommonService {
	@Autowired private VoteRepository voteRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveVote();
		this.findAllVotes();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("voteRegistered", this.voteParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.voteParam.getOption() == null)
			errorList.add("Please, enter a vote.");

		if (this.voteParam.getSession() == null || this.voteParam.getSession().getIdentity() == null)
			errorList.add("Please, select a session.");

		if (this.voteParam.getMember() == null || this.voteParam.getMember().getIdentity() == null)
			errorList.add("Please, select a member.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveVote() throws BaseException {
		this.voteRepository.save(this.voteParam);
	}

	private void findAllVotes() {
		this.setArtifact("voteList", this.voteRepository.findAll());
	}
}