package br.com.compassuol.election.services.session;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.SessionEntity;
import br.com.compassuol.election.exceptions.DataForExclusionNotFoundException;
import br.com.compassuol.election.repositories.SessionRepository;

@Service
public class SessionExecuteExclusionService extends SessionBaseService implements CommonService {
	@Autowired private SessionRepository sessionRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.deleteSession();
		this.findAllSessions();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("sessionRegistered", this.sessionParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForExclusionNotFoundException {
		SessionEntity session = this.sessionRepository.findByIdentity(this.sessionParam.getIdentity()).get();

		if (session == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteSession() throws BaseException {
		this.sessionRepository.delete(this.sessionParam);
	}

	private void findAllSessions() {
		this.setArtifact("sessionList", this.sessionRepository.findAll());
	}
}