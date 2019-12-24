
package acme.features.worker.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.audits.Audit;
import acme.entities.roles.Worker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/worker/audit/")
public class WorkerAuditController extends AbstractController<Worker, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private WorkerAuditListService	listService;

	@Autowired
	private WorkerAuditShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
