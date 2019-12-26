
package acme.features.authenticated.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorized;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuthorizedShowService implements AbstractShowService<Authenticated, Authorized> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuthorizedRepository repository;


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
	public Authorized findOne(final Request<Authorized> request) {
		assert request != null;

		Authorized result;
		int id;

		id = request.getPrincipal().getAccountId();
		result = this.repository.findOneAuthorizedByUserAccountId(id);

		return result;
	}
}
