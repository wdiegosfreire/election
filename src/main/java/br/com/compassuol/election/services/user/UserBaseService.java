package br.com.compassuol.election.services.user;

import br.com.compassuol.common.services.BaseService;
import br.com.compassuol.election.entities.UserEntity;

public abstract class UserBaseService extends BaseService {
	protected UserEntity userParam;

	public void setEntity(UserEntity userEntity) {
		this.userParam = userEntity;
	}
}