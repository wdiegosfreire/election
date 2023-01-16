package br.com.compassuol.election.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compassuol.common.exceptions.BaseException;
import br.com.compassuol.election.entities.SessionEntity;
import br.com.compassuol.election.services.session.SessionAccessEditionService;
import br.com.compassuol.election.services.session.SessionAccessModuleService;
import br.com.compassuol.election.services.session.SessionAccessRegistrationService;
import br.com.compassuol.election.services.session.SessionExecuteExclusionService;
import br.com.compassuol.election.services.session.SessionExecuteRegistrationService;

@Controller
@RequestMapping(value = "/session")
public class SessionResource {
	@Autowired private SessionAccessModuleService sessionAccessModuleService;
	@Autowired private SessionAccessEditionService sessionAccessEditionService;
	@Autowired private SessionExecuteExclusionService sessionExecuteExclusionService;
	@Autowired private SessionAccessRegistrationService sessionAccessRegistrationService;
	@Autowired private SessionExecuteRegistrationService sessionExecuteRegistrationService;


	@GetMapping(value = "/accessModule")
	public String accessModule(Model model) throws BaseException {
		this.sessionAccessModuleService.setParams(null);
		model.addAttribute("sessionList", this.sessionAccessModuleService.execute().get("sessionList"));

		return "sessionIndex";
	}

	@GetMapping(value = "/accessRegistration")
	public String accessEdition(Model model) throws BaseException {
		this.sessionAccessRegistrationService.setParams(null);

		model.addAttribute("session", new SessionEntity());
		model.addAttribute("subjectList", this.sessionAccessRegistrationService.execute().get("subjectList"));
		model.addAttribute("memberList", this.sessionAccessRegistrationService.execute().get("memberList"));

		return "sessionForm";
	}

	@PostMapping(value = "/executeRegistration")
	public String executeRegistration(@ModelAttribute("session") SessionEntity session, Model model) throws BaseException {
		this.sessionExecuteRegistrationService.setParams(session);
		this.sessionExecuteRegistrationService.execute();

		return "redirect:/session/accessModule";
	}

	@GetMapping(value = "/accessEdition/{identity}")
	public String executeEdition(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		SessionEntity session = new SessionEntity();
		session.setIdentity(identity);

		this.sessionAccessEditionService.setParams(session);
		model.addAttribute("session", this.sessionAccessEditionService.execute().get("session"));

		return "sessionForm";
	}

	@GetMapping(value = "/executeExclusion/{identity}")
	public String executeExclusion(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		SessionEntity session = new SessionEntity();
		session.setIdentity(identity);
		
		this.sessionExecuteExclusionService.setParams(session);
		model.addAttribute("session", this.sessionExecuteExclusionService.execute().get("session"));
		
		return "redirect:/session/accessModule";
	}
}