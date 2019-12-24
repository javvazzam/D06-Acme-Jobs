
package acme.features.anonymous.company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companies.Company;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/company/")
public class AnonymousCompanyController extends AbstractController<Anonymous, Company> {

	@Autowired
	private AnonymousCompanyListService	listService;

	@Autowired
	private AnonymousCompanyShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
