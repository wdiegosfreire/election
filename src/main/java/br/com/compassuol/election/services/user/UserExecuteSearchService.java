package br.com.compassuol.election.services.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.UserEntity;
import br.com.compassuol.election.exceptions.UserNotFoundException;
import br.com.compassuol.election.repositories.UserRepository;

@Service
public class UserExecuteSearchService extends UserBaseService implements CommonService {
	private UserEntity userAuthenticated;

	@Autowired
	private final UserRepository userRepository;

	public UserExecuteSearchService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findUserByIdentity();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("userAuthenticated", this.userAuthenticated);
		return super.returnBusinessData();
	}

	private void findUserByIdentity() throws BaseException {
		this.userAuthenticated = this.userRepository.findByIdentity(this.userParam.getIdentity()).orElse(null);

		if (this.userAuthenticated == null)
			throw new UserNotFoundException();
	}
}