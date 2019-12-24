
package acme.features.provider.solicitudes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Provider;
import acme.entities.solicitudes.Solicitud;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/solicitud/")
public class ProviderSolicitudController extends AbstractController<Provider, Solicitud> {

	@Autowired
	private ProviderSolicitudCreateService	createService;

	@Autowired
	private ProviderSolicitudListService	listService;

	@Autowired
	private ProviderSolicitudShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
