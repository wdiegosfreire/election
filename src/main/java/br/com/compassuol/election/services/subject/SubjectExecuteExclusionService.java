package br.com.compassuol.election.services.subject;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.SubjectEntity;
import br.com.compassuol.election.exceptions.DataForExclusionNotFoundException;
import br.com.compassuol.election.repositories.SubjectRepository;

@Service
public class SubjectExecuteExclusionService extends SubjectBaseService implements CommonService {
	@Autowired private SubjectRepository subjectRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.deleteLocation();
		this.findAllLocations();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("locationRegistered", this.subjectParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForExclusionNotFoundException {
		SubjectEntity subject = this.subjectRepository.findByIdentity(this.subjectParam.getIdentity()).get();

		if (subject == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteLocation() throws BaseException {
		this.subjectRepository.delete(this.subjectParam);
	}

	private void findAllLocations() {
		this.setArtifact("subjectList", this.subjectRepository.findAll());
	}
}