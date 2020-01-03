
package acme.features.worker.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class WorkerAuditListService implements AbstractListService<Worker, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	WorkerAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);

		result = job.isFinalMode();

		return result;
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public Collection<Audit> findMany(final Request<Audit> request) {
		assert request != null;

		Collection<Audit> result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findManyAuditsReferedToJob2(id);

		return result;
	}
}
