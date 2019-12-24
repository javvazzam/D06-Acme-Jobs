
package acme.features.authenticated.solicitud;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.solicitudes.Solicitud;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/solicitud/")
public class AuthenticatedSolicitudController extends AbstractController<Authenticated, Solicitud> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSolicitudListService	listService;

	@Autowired
	private AuthenticatedSolicitudShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
