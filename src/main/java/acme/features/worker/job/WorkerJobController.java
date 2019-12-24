
package acme.features.worker.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/worker/job/")
public class WorkerJobController extends AbstractController<Worker, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private WorkerJobListService	listService;

	@Autowired
	private WorkerJobShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
