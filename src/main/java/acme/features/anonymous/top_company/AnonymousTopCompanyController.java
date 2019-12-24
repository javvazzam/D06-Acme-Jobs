
package acme.features.anonymous.top_company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companies.Company;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/top_company/")
public class AnonymousTopCompanyController extends AbstractController<Anonymous, Company> {

	@Autowired
	private AnonymousTopCompanyListService	listService;

	@Autowired
	private AnonymousTopCompanyShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
