
package acme.features.auditor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int auditorId = principal.getAccountId();

		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);

		Auditor auditor = this.repository.findOneAuditorByUserAccountId(auditorId);

		boolean autorize = auditor.isRequest();

		return autorize && job.isFinalMode();
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

		String direccion3 = "../audit/create?id=" + entity.getId();
		model.setAttribute("jobCreateAudit", direccion3);

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
