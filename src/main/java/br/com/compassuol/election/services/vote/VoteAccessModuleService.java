package br.com.compassuol.election.services.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.repositories.VoteRepository;

@Service
public class VoteAccessModuleService extends VoteBaseService implements CommonService {
	@Autowired private VoteRepository voteRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllVotes();
	}

	private void findAllVotes() {
		this.setArtifact("voteList", this.voteRepository.findAll());
	}
}