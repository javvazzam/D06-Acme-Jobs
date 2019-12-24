
package acme.features.authenticated.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedJobShowService implements AbstractShowService<Authenticated, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		boolean deadlineFuture;
		Date deadline = job.getDeadline();
		deadlineFuture = deadline.getTime() > System.currentTimeMillis();
		result = (job.isFinalMode() || !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId()) && deadlineFuture;

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccion = "../audit/list?id=" + entity.getId();
		model.setAttribute("auditList", direccion);

		String direccion2 = "../duty/list_by_job?id=" + entity.getId();
		model.setAttribute("duties", direccion2);

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		request.unbind(entity, model, "referenceNumber", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}
}
