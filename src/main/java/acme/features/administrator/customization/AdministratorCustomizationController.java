
package acme.features.administrator.customization;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.customization.Customization;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/customization/")
public class AdministratorCustomizationController extends AbstractController<Administrator, Customization> {

	@Autowired
	private AdministratorCustomizationListService	listService;

	@Autowired
	private AdministratorCustomizationShowService	showService;

	@Autowired
	private AdministratorCustomizationUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
