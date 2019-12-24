
package acme.features.worker.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.duties.Duty;
import acme.entities.roles.Worker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/worker/duty/")
public class WorkerDutyController extends AbstractController<Worker, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private WorkerDutyListService	listService;

	@Autowired
	private WorkerDutyShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_BY_JOB, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
