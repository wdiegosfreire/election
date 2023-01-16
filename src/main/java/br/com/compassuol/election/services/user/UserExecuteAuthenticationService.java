package br.com.compassuol.election.services.user;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.common.services.CommonService;
import br.com.compassuol.election.entities.UserEntity;
import br.com.compassuol.election.exceptions.UserNotFoundException;
import br.com.compassuol.election.exceptions.UserUnauthorizedException;
import br.com.compassuol.election.repositories.UserRepository;

@Service
public class UserExecuteAuthenticationService extends UserBaseService implements CommonService {
	private UserEntity userAuthenticated;

	@Autowired private UserRepository userRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findUserByEmail();
		this.checkIfPasswordIsCorrect();
		this.generateSessionToken();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("userAuthenticated", this.userAuthenticated);
		return super.returnBusinessData();
	}

	private void findUserByEmail() throws BaseException {
		this.userAuthenticated = this.userRepository.findByEmail(this.userParam.getEmail()).orElse(null);

		if (this.userAuthenticated == null || this.userAuthenticated.getIdentity() == null || this.userAuthenticated.getEmail() == null)
			throw new UserNotFoundException();
	}

	private void checkIfPasswordIsCorrect() throws BaseException {
		if (!this.userAuthenticated.getPassword().equals(this.userParam.getPassword()))
			throw new UserUnauthorizedException();
	}

	private void generateSessionToken() {
		this.setSessionToken(
			JWT.create()
			.withSubject(this.userAuthenticated.getEmail())
			.withExpiresAt(new Date(System.currentTimeMillis() + 3000000))
			.withClaim("userIdentity", this.userAuthenticated.getIdentity())
			.sign(Algorithm.HMAC512(System.getenv("SISFIN_BACKEND_JWT_SECRET")))
		);
	}
}