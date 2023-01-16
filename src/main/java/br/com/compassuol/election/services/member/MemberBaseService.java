package br.com.compassuol.election.services.member;

import br.com.compassuol.common.services.BaseService;
import br.com.compassuol.election.entities.MemberEntity;

public abstract class MemberBaseService extends BaseService {
	protected MemberEntity memberParam;

	public void setParams(MemberEntity memberEntity) {
		this.memberParam = memberEntity;
	}
}