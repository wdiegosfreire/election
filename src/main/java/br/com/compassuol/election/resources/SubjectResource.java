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
import br.com.compassuol.election.entities.SubjectEntity;
import br.com.compassuol.election.services.subject.SubjectAccessEditionService;
import br.com.compassuol.election.services.subject.SubjectAccessModuleService;
import br.com.compassuol.election.services.subject.SubjectExecuteExclusionService;
import br.com.compassuol.election.services.subject.SubjectExecuteRegistrationService;

@Controller
@RequestMapping(value = "/subject")
public class SubjectResource {
	@Autowired private SubjectAccessModuleService subjectAccessModuleService;
	@Autowired private SubjectAccessEditionService subjectAccessEditionService;
	@Autowired private SubjectExecuteRegistrationService subjectExecuteRegistrationService;

	@Autowired private SubjectExecuteExclusionService subjectExecuteExclusionService;

	@GetMapping(value = "/accessModule")
	public String accessModule(Model model) throws BaseException {
		this.subjectAccessModuleService.setParams(null);
		model.addAttribute("subjectList", this.subjectAccessModuleService.execute().get("subjectList"));

		return "subjectIndex";
	}

	@GetMapping(value = "/accessRegistration")
	public String accessEdition(Model model) throws BaseException {
		model.addAttribute("subject", new SubjectEntity());

		return "subjectForm";
	}

	@PostMapping(value = "/executeRegistration")
	public String executeRegistration(@ModelAttribute("subject") SubjectEntity subject, Model model) throws BaseException {
		this.subjectExecuteRegistrationService.setParams(subject);
		this.subjectExecuteRegistrationService.execute();

		return "redirect:/subject/accessModule";
	}

	@GetMapping(value = "/accessEdition/{identity}")
	public String executeEdition(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		SubjectEntity subject = new SubjectEntity();
		subject.setIdentity(identity);

		this.subjectAccessEditionService.setParams(subject);
		model.addAttribute("subject", this.subjectAccessEditionService.execute().get("subject"));

		return "subjectForm";
	}

	@GetMapping(value = "/executeExclusion/{identity}")
	public String executeExclusion(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		SubjectEntity subject = new SubjectEntity();
		subject.setIdentity(identity);
		
		this.subjectExecuteExclusionService.setParams(subject);
		model.addAttribute("subject", this.subjectExecuteExclusionService.execute().get("subject"));
		
		return "redirect:/subject/accessModule";
	}
}