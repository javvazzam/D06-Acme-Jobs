
package acme.features.auditor.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditCreateService implements AbstractCreateService<Auditor, Audit> {

	@Autowired
	AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;
		Principal principal = request.getPrincipal();

		int auditorId = principal.getAccountId();

		Auditor auditor = this.repository.findOneAuditorByUserAccountId(auditorId);

		Integer jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);

		return auditor != null && job.isFinalMode();
	}

	@Override
	public void bind(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccionAudit = "../audit/create?id=" + request.getModel().getInteger("id");
		model.setAttribute("direccionAudit", direccionAudit);

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		request.unbind(entity, model, "title", "body");
	}

	@Override
	public Audit instantiate(final Request<Audit> request) {
		assert request != null;

		Audit result;
		Principal principal;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		principal = request.getPrincipal();
		Integer auditorId = principal.getAccountId();
		Auditor auditor = this.repository.findOneAuditorByUserAccountId(auditorId);
		Integer jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);

		result = new Audit();
		result.setJob(job);
		result.setAuditor(auditor);
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Audit> request, final Audit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
