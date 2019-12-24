
package acme.features.authenticated.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.audits.Audit;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/audit/")
public class AuthenticatedAuditController extends AbstractController<Authenticated, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditListService	listService;

	@Autowired
	private AuthenticatedAuditShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
