
package acme.features.authenticated.sponsor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/sponsor/")
public class AuthenticatedSponsorController extends AbstractController<Authenticated, Sponsor> {

	@Autowired
	private AuthenticatedSponsorCreateService	createService;

	@Autowired
	private AuthenticatedSponsorUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
