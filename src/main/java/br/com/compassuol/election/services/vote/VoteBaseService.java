package br.com.compassuol.election.services.vote;

import br.com.compassuol.common.services.BaseService;
import br.com.compassuol.election.entities.VoteEntity;

public abstract class VoteBaseService extends BaseService {
	protected VoteEntity voteParam;

	public void setParams(VoteEntity voteEntity) {
		this.voteParam = voteEntity;
	}
}