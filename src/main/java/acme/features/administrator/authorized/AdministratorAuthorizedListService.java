
package acme.features.administrator.authorized;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorized;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuthorizedListService implements AbstractListService<Administrator, Authorized> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorAuthorizedRepository repository;


	@Override
	public boolean authorise(final Request<Authorized> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Authorized> request, final Authorized entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.isAccepted()) {
			model.setAttribute("status", "Accepted");
		} else {
			model.setAttribute("status", "Pending");
		}

		request.unbind(entity, model, "body");
	}

	@Override
	public Collection<Authorized> findMany(final Request<Authorized> request) {
		assert request != null;

		Collection<Authorized> result;

		result = this.repository.findManyAll();
		return result;
	}
}
