package br.com.compassuol.election.services.vote;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.VoteEntity;
import br.com.compassuol.election.exceptions.DataForExclusionNotFoundException;
import br.com.compassuol.election.repositories.VoteRepository;

@Service
public class VoteExecuteExclusionService extends VoteBaseService implements CommonService {
	@Autowired private VoteRepository voteRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.deleteVote();
		this.findAllVotes();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("voteRegistered", this.voteParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForExclusionNotFoundException {
		VoteEntity vote = this.voteRepository.findByIdentity(this.voteParam.getIdentity()).get();

		if (vote == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteVote() throws BaseException {
		this.voteRepository.delete(this.voteParam);
	}

	private void findAllVotes() {
		this.setArtifact("voteList", this.voteRepository.findAll());
	}
}