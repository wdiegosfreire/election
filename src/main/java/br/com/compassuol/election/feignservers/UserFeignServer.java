package br.com.compassuol.election.feignservers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.election.entities.UserEntity;
import br.com.compassuol.election.repositories.UserRepository;

@RestController
@RequestMapping(value = "/userfeignserver")
public class UserFeignServer {
	@Autowired private UserRepository userRepository;

	@GetMapping(value = "/{token}")
	public UserEntity validateToken(@PathVariable String token) throws BaseException {
		DecodedJWT decodedJwt = null ;

		try {
			decodedJwt = JWT.require(Algorithm.HMAC512(System.getenv("SISFIN_BACKEND_JWT_SECRET"))).build().verify(token);
		}
		catch (TokenExpiredException e) {
			throw new BaseException("The Token has expired.");
		}
		catch (JWTDecodeException e) {
			throw new BaseException("The input is not a valid base 64 encoded string.");
		}
		catch (Exception e) {
			throw new BaseException("Exception not recognized.");
		}

		this.findUserByIdentity(Long.parseLong(decodedJwt.getClaim("userIdentity").toString()));
		
		return this.findUserByIdentity(Long.parseLong(decodedJwt.getClaim("userIdentity").toString()));
	}

	private UserEntity findUserByIdentity(long identity) throws BaseException {
		UserEntity user = new UserEntity();
		user.setIdentity(identity);

		return this.userRepository.findByIdentity(identity).get();
	}
}