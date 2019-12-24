
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutyListService implements AbstractListService<Authenticated, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		Integer idJob;
		Job job;

		idJob = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(idJob);

		result = job.isFinalMode();

		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "timeAmount");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		Collection<Duty> result;

		Integer id = request.getModel().getInteger("id");
		result = this.repository.findManyAllByJob(id);
		return result;
	}
}
