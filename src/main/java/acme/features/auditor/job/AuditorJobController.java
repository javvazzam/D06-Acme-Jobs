
package acme.features.auditor.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/job/")
public class AuditorJobController extends AbstractController<Auditor, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorJobListMineService		listMineService;

	@Autowired
	private AuditorJobListNotMineService	listNotMineService;

	@Autowired
	private AuditorJobShowService			showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_NOT_MINE, BasicCommand.LIST, this.listNotMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
