package br.com.compassuol.election.services.session;

import br.com.compassuol.common.services.BaseService;
import br.com.compassuol.election.entities.SessionEntity;

public abstract class SessionBaseService extends BaseService {
	protected SessionEntity sessionParam;

	public void setParams(SessionEntity sessionEntity) {
		this.sessionParam = sessionEntity;
	}
}