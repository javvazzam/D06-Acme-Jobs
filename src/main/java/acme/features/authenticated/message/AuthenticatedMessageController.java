
package acme.features.authenticated.message;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.Message;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message/")
public class AuthenticatedMessageController extends AbstractController<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageListService		listService;

	@Autowired
	private AuthenticatedMessageCreateService	createService;

	@Autowired
	private AuthenticatedMessageShowService		showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_BY_THREAD, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
