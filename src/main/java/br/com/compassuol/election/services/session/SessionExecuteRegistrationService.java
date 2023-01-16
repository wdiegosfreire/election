package br.com.compassuol.election.services.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.exceptions.RequiredFieldNotFoundException;
import br.com.compassuol.election.repositories.SessionRepository;

@Service
public class SessionExecuteRegistrationService extends SessionBaseService implements CommonService {
	@Autowired private SessionRepository sessionRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveSession();
		this.findAllSessions();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("sessionRegistered", this.sessionParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.sessionParam.getStartDate() == null)
			errorList.add("Please, enter start date.");

		if (this.sessionParam.getEndDate() == null)
			errorList.add("Please, enter end date.");

		if (this.sessionParam.getDuration() == null)
			errorList.add("Please, enter session duration.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveSession() throws BaseException {
		this.sessionRepository.save(this.sessionParam);
	}

	private void findAllSessions() {
		this.setArtifact("sessionList", this.sessionRepository.findAll());
	}
}