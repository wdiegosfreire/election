package br.com.compassuol.election.services.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.repositories.SubjectRepository;

@Service
public class SubjectAccessModuleService extends SubjectBaseService implements CommonService {
	@Autowired private SubjectRepository subjectRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllSubjects();
	}

	private void findAllSubjects() {
		this.setArtifact("subjectList", this.subjectRepository.findAll());
	}
}