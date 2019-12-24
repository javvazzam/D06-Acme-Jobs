
package acme.features.employer.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerAuditShowService implements AbstractShowService<Employer, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		return true;
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

		Audit result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditById(id);

		Auditor auditor = result.getAuditor();
		model.setAttribute("auditor", auditor.getUserAccount().getUsername());

		Job associatedJob = result.getJob();
		model.setAttribute("associatedJob", associatedJob.getReferenceNumber());

		request.unbind(entity, model, "title", "moment", "body");
	}

	@Override
	public Audit findOne(final Request<Audit> request) {
		assert request != null;

		Audit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditById(id);

		return result;
	}
}
