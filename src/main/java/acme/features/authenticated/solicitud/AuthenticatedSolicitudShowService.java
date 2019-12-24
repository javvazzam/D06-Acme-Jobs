
package acme.features.authenticated.solicitud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.solicitudes.Solicitud;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSolicitudShowService implements AbstractShowService<Authenticated, Solicitud> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSolicitudRepository repository;


	@Override
	public boolean authorise(final Request<Solicitud> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Solicitud> request, final Solicitud entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "moment", "deadline", "text", "reward");
	}

	@Override
	public Solicitud findOne(final Request<Solicitud> request) {
		assert request != null;

		Solicitud result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
