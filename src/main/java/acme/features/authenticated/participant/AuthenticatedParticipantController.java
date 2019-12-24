
package acme.features.authenticated.participant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.participants.Participant;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/participant/")
public class AuthenticatedParticipantController extends AbstractController<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedParticipantListService		listService;

	@Autowired
	private AuthenticatedParticipantCreateService	createService;

	@Autowired
	private AuthenticatedParticipantDeleteService	deleteService;

	@Autowired
	private AuthenticatedParticipantShowService		showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
