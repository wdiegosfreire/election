package br.com.compassuol.election.services.user;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.election.configs.TestConfig;
import br.com.compassuol.election.entities.UserEntity;
import br.com.compassuol.election.exceptions.UserNotFoundException;
import br.com.compassuol.election.repositories.UserRepository;
import br.com.compassuol.election.services.user.UserExecuteSearchService;

@DisplayName("UserExecuteSearchServiceTest")
public class UserExecuteSearchServiceTest extends TestConfig {
	@Autowired private UserExecuteSearchService userExecuteSearchService;

	@MockBean private UserRepository userRepository;

	@Test
	@DisplayName("Must execute search correctly")
	public void mustExecuteSearchCorrectly() throws BaseException {
		UserEntity userMock = Mockito.mock(UserEntity.class);
		Mockito.when(userMock.getIdentity()).thenReturn(1L);

		Mockito.when(this.userRepository.findByIdentity(ArgumentMatchers.eq(userMock.getIdentity()))).thenReturn(Optional.of(userMock));
		this.userExecuteSearchService.setEntity(userMock);

		this.userExecuteSearchService.execute();
	}

	@Test
	@DisplayName("Must execute search and throw UserNotFoundException")
	public void mustExecuteSearchAndThrowUserNotFoundException() throws BaseException {
		UserEntity userMock = Mockito.mock(UserEntity.class);
		Mockito.when(userMock.getIdentity()).thenReturn(1L);
		
		Mockito.when(this.userRepository.findByIdentity(ArgumentMatchers.eq(userMock.getIdentity()))).thenReturn(Optional.empty());
		this.userExecuteSearchService.setEntity(userMock);

		Assertions.assertThrows(
			UserNotFoundException.class,
			() -> this.userExecuteSearchService.execute()
		);
	}
}