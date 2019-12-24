
package acme.features.employer.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.audits.Audit;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/audit/")
public class EmployerAuditController extends AbstractController<Employer, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerAuditListService	listService;

	@Autowired
	private EmployerAuditShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
