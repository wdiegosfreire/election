package br.com.compassuol.election.services.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.SubjectEntity;
import br.com.compassuol.election.exceptions.DataForEditionNotFoundException;
import br.com.compassuol.election.repositories.SubjectRepository;

@Service
public class SubjectAccessEditionService extends SubjectBaseService implements CommonService {
	@Autowired private SubjectRepository subjectRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
	}

	private void findById() throws DataForEditionNotFoundException {
		SubjectEntity subject = this.subjectRepository.findByIdentity(this.subjectParam.getIdentity()).get();

		if (subject == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("subject", subject);
	}
}