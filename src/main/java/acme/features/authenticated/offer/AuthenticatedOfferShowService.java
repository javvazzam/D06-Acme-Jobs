
package acme.features.authenticated.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOfferShowService implements AbstractShowService<Authenticated, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "moment", "deadline", "text", "min", "max");
	}

	@Override
	public Offer findOne(final Request<Offer> request) {
		assert request != null;

		Offer result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
