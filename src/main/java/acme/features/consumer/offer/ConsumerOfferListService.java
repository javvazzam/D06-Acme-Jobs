
package acme.features.consumer.offer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ConsumerOfferListService implements AbstractListService<Consumer, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ConsumerOfferRepository repository;


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

		request.unbind(entity, model, "title", "moment", "deadline");
	}

	@Override
	public Collection<Offer> findMany(final Request<Offer> request) {
		assert request != null;

		Collection<Offer> result;

		result = this.repository.findManyAll();
		return result;
	}
}
