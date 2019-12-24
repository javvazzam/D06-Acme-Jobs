
package acme.features.provider.solicitudes;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Provider;
import acme.entities.solicitudes.Solicitud;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ProviderSolicitudListService implements AbstractListService<Provider, Solicitud> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ProviderSolicitudRepository repository;


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

		request.unbind(entity, model, "title", "moment", "deadline", "reward");
	}

	@Override
	public Collection<Solicitud> findMany(final Request<Solicitud> request) {
		assert request != null;

		Collection<Solicitud> result;

		result = this.repository.findManyAll();
		return result;
	}
}
