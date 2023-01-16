package br.com.compassuol.election.exceptions;

import java.util.List;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.election.commons.exceptions.HttpStatusInternalServerError;

public class EmailAlreadyRegisteredException extends BaseException implements HttpStatusInternalServerError {
	private static final long serialVersionUID = 1L;

	public EmailAlreadyRegisteredException() {
		super("Email Already Registered", "The email informed already registered on database.");
	}

	public EmailAlreadyRegisteredException(String summary, List<String> messageList) {
		super(summary, messageList);
	}
}