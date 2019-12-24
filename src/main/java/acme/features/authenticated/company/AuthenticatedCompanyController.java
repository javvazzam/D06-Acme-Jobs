
package acme.features.authenticated.company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companies.Company;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/company/")
public class AuthenticatedCompanyController extends AbstractController<Authenticated, Company> {

	@Autowired
	private AuthenticatedCompanyListService	listService;

	@Autowired
	private AuthenticatedCompanyShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
