package br.com.compassuol.election.exceptions;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.election.commons.exceptions.HttpStatusNotFound;

public class DataForEditionNotFoundException extends BaseException implements HttpStatusNotFound {
	private static final long serialVersionUID = 1L;

	public DataForEditionNotFoundException() {
		super("Data Not Fount", "Unable to identify record to be edited.");
	}
}