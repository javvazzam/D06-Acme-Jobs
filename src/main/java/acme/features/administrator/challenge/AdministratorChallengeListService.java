
package acme.features.administrator.challenge;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorChallengeListService implements AbstractListService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
	}

	@Override
	public Collection<Challenge> findMany(final Request<Challenge> request) {
		assert request != null;

		Collection<Challenge> result;

		result = this.repository.findManyChallenges();
		return result;
	}

}
