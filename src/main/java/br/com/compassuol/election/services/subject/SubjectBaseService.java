package br.com.compassuol.election.services.subject;

import br.com.compassuol.common.services.BaseService;
import br.com.compassuol.election.entities.SubjectEntity;

public abstract class SubjectBaseService extends BaseService {
	protected SubjectEntity subjectParam;

	public void setParams(SubjectEntity subjectEntity) {
		this.subjectParam = subjectEntity;
	}
}