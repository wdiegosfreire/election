package br.com.compassuol.election.services.session;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.SessionEntity;
import br.com.compassuol.election.repositories.SessionRepository;
import br.com.compassuol.election.repositories.VoteRepository;

@Service
public class SessionAccessModuleService extends SessionBaseService implements CommonService {
	@Autowired private VoteRepository voteRepository;
	@Autowired private SessionRepository sessionRepository;

	private List<SessionEntity> sessionList;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllSessions();
		this.findAllVotesBySession();

		this.setArtifact("sessionList", this.sessionRepository.findAll());
	}

	private void findAllSessions() {
		this.sessionList = this.sessionRepository.findAll();
	}

	private void findAllVotesBySession() {
		for (SessionEntity session : sessionList) {
			session.setVoteList(this.voteRepository.findBySessionIdentity(session.getIdentity()));
		}
	}
}