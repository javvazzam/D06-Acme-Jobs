
package acme.features.administrator.company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companies.Company;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/company/")
public class AdministratorCompanyController extends AbstractController<Administrator, Company> {

	@Autowired
	private AdministratorCompanyListService		listService;

	@Autowired
	private AdministratorCompanyShowService		showService;

	@Autowired
	private AdministratorCompanyCreateService	createService;

	@Autowired
	private AdministratorCompanyUpdateService	updateService;

	@Autowired
	private AdministratorCompanyDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
