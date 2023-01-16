package br.com.compassuol.election.services.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.VoteEntity;
import br.com.compassuol.election.exceptions.DataForEditionNotFoundException;
import br.com.compassuol.election.repositories.VoteRepository;

@Service
public class VoteAccessEditionService extends VoteBaseService implements CommonService {
	@Autowired private VoteRepository voteRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
	}

	private void findById() throws DataForEditionNotFoundException {
		VoteEntity vote = this.voteRepository.findByIdentity(this.voteParam.getIdentity()).get();

		if (vote == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("vote", vote);
	}
}