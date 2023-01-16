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
import br.com.compassuol.election.entities.VoteEntity;
import br.com.compassuol.election.services.vote.VoteAccessEditionService;
import br.com.compassuol.election.services.vote.VoteAccessModuleService;
import br.com.compassuol.election.services.vote.VoteAccessRegistrationService;
import br.com.compassuol.election.services.vote.VoteExecuteExclusionService;
import br.com.compassuol.election.services.vote.VoteExecuteRegistrationService;

@Controller
@RequestMapping(value = "/vote")
public class VoteResource {
	@Autowired private VoteAccessModuleService voteAccessModuleService;
	@Autowired private VoteAccessEditionService voteAccessEditionService;
	@Autowired private VoteExecuteExclusionService voteExecuteExclusionService;
	@Autowired private VoteAccessRegistrationService voteAccessRegistrationService;
	@Autowired private VoteExecuteRegistrationService voteExecuteRegistrationService;

	@GetMapping(value = "/accessModule")
	public String accessModule(Model model) throws BaseException {
		this.voteAccessModuleService.setParams(null);
		model.addAttribute("voteList", this.voteAccessModuleService.execute().get("voteList"));

		return "voteIndex";
	}

	@GetMapping(value = "/accessRegistration/{identity}")
	public String accessEdition(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		VoteEntity voteInput = new VoteEntity();
		voteInput.setSession(new SessionEntity());
		voteInput.getSession().setIdentity(identity);

		this.voteAccessRegistrationService.setParams(voteInput);

		VoteEntity voteOutput = new VoteEntity();
		voteOutput.setSession((SessionEntity) this.voteAccessRegistrationService.execute().get("sessionEntity"));

		model.addAttribute("vote", voteOutput);
		model.addAttribute("memberList", this.voteAccessRegistrationService.execute().get("memberList"));

		return "voteForm";
	}

	@PostMapping(value = "/executeRegistration")
	public String executeRegistration(@ModelAttribute("vote") VoteEntity vote, Model model) throws BaseException {
		this.voteExecuteRegistrationService.setParams(vote);
		this.voteExecuteRegistrationService.execute();

		return "redirect:/session/accessModule";
	}

	@GetMapping(value = "/accessEdition/{identity}")
	public String executeEdition(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		VoteEntity vote = new VoteEntity();
		vote.setIdentity(identity);

		this.voteAccessEditionService.setParams(vote);
		model.addAttribute("vote", this.voteAccessEditionService.execute().get("vote"));

		return "voteForm";
	}

	@GetMapping(value = "/executeExclusion/{identity}")
	public String executeExclusion(@PathVariable(value = "identity") long identity, Model model) throws BaseException {
		VoteEntity vote = new VoteEntity();
		vote.setIdentity(identity);
		
		this.voteExecuteExclusionService.setParams(vote);
		model.addAttribute("vote", this.voteExecuteExclusionService.execute().get("vote"));
		
		return "redirect:/vote/accessModule";
	}
}