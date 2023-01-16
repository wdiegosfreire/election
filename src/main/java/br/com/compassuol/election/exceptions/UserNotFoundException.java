package br.com.compassuol.election.exceptions;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.election.commons.exceptions.HttpStatusNotFound;

public class UserNotFoundException extends BaseException implements HttpStatusNotFound {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("User Not Fount", "The email entered was not found in the database.");
	}
}