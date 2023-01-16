package br.com.compassuol.election.services.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.exceptions.RequiredFieldNotFoundException;
import br.com.compassuol.election.repositories.SubjectRepository;

@Service
public class SubjectExecuteRegistrationService extends SubjectBaseService implements CommonService {
	@Autowired private SubjectRepository subjectRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveSubject();
		this.findAllSubjects();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("locationRegistered", this.subjectParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.subjectParam.getTitle() == null || this.subjectParam.getTitle().equals(""))
			errorList.add("Please, enter title.");

		if (this.subjectParam.getDescription() == null || this.subjectParam.getDescription().equals(""))
			errorList.add("Please, enter description.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveSubject() throws BaseException {
		this.subjectRepository.save(this.subjectParam);
	}

	private void findAllSubjects() {
		this.setArtifact("subjectList", this.subjectRepository.findAll());
	}
}