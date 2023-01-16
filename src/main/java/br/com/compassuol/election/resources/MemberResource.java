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
import br.com.compassuol.election.entities.MemberEntity;
import br.com.compassuol.election.services.member.MemberAccessEditionService;
import br.com.compassuol.election.services.member.MemberAccessModuleService;
import br.com.compassuol.election.services.member.MemberExecuteExclusionService;
import br.com.compassuol.election.services.member.MemberExecuteRegistrationService;

@Controller
@RequestMapping(value = "/member")
public class MemberResource {
	@Autowired private MemberAccessModuleService memberAccessModuleService;
	@Autowired private MemberAccessEditionService memberAccessEditionService;
	@Autowired private MemberExecuteExclusionService memberExecuteExclusionService;
	@Autowired private MemberExecuteRegistrationService memberExecuteRegistrationService;


	@GetMapping(value = "/accessModule")
	public String accessModule(Model model) throws BaseException {
		this.memberAccessModuleService.setParams(null);
		model.addAttribute("memberList", this.memberAccessModuleService.execute().get("memberList"));

		return "memberIndex";
	}

	@GetMapping(value = "/accessRegistration")
	public String accessEdition(Model model) throws BaseException {
		model.addAttribute("member", new MemberEntity());

		return "memberForm";
	}

	@PostMapping(value = "/executeRegistration")
	public String executeRegistration(@ModelAttribute("member") MemberEntity member, Model model) throws BaseException {
		this.memberExecuteRegistrationService.setParams(member);
		this.memberExecuteRegistrationService.execute();

		return "redirect:/member/accessModule";
	}

	@GetMapping(value = "/accessEdition/{identity}")
	public String executeEdition(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		MemberEntity member = new MemberEntity();
		member.setIdentity(identity);

		this.memberAccessEditionService.setParams(member);
		model.addAttribute("member", this.memberAccessEditionService.execute().get("member"));

		return "memberForm";
	}

	@GetMapping(value = "/executeExclusion/{identity}")
	public String executeExclusion(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		MemberEntity member = new MemberEntity();
		member.setIdentity(identity);
		
		this.memberExecuteExclusionService.setParams(member);
		model.addAttribute("member", this.memberExecuteExclusionService.execute().get("member"));
		
		return "redirect:/member/accessModule";
	}
}