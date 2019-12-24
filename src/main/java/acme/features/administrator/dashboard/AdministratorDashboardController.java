
package acme.features.administrator.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.forms.Dashboard;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/dashboard/")
public class AdministratorDashboardController extends AbstractController<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorDashboardShowService showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {

		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
