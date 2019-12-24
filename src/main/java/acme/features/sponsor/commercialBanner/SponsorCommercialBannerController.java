
package acme.features.sponsor.commercialBanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.commercialBanners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/commercial-banner/")
public class SponsorCommercialBannerController extends AbstractController<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerListMineService	listMineService;

	@Autowired
	private SponsorCommercialBannerShowService		showService;

	@Autowired
	private SponsorCommercialBannerCreateService	createService;

	@Autowired
	private SponsorCommercialBannerUpdateService	updateService;

	@Autowired
	private SponsorCommercialBannerDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
