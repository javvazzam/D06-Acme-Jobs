
package acme.features.anonymous.top_investor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investors.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/top_investors/")
public class AnonymousTopInvestorController extends AbstractController<Anonymous, Investor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousTopInvestorListService	listService;

	@Autowired
	private AnonymousTopInvestorShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
