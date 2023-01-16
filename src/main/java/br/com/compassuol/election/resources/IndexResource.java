package br.com.compassuol.election.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compassuol.common.exceptions.BaseException;

@Controller
@RequestMapping()
public class IndexResource {
	@GetMapping(value = "/index")
	public String executeAuthentication(Model model) throws BaseException {
		
		return "index";
	}
}