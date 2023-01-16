package br.com.compassuol.election.services.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.SessionEntity;
import br.com.compassuol.election.exceptions.DataForEditionNotFoundException;
import br.com.compassuol.election.repositories.SessionRepository;

@Service
public class SessionAccessEditionService extends SessionBaseService implements CommonService {
	@Autowired private SessionRepository sessionRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
	}

	private void findById() throws DataForEditionNotFoundException {
		SessionEntity session = this.sessionRepository.findByIdentity(this.sessionParam.getIdentity()).get();

		if (session == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("session", session);
	}
}