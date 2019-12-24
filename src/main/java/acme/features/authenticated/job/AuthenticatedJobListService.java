
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedJobListService implements AbstractListService<Authenticated, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		request.unbind(entity, model, "referenceNumber", "title", "deadline");
	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		assert request != null;

		Collection<Job> result;
		result = this.repository.findManyAll();

		return result;
	}
}
