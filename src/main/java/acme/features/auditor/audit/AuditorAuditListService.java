
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditListService implements AbstractListService<Auditor, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
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
		Principal principal;

		principal = request.getPrincipal();
		int auditorUserAccountId = principal.getAccountId();
		int jobId = request.getModel().getInteger("id");
		result = this.repository.findManyAuditsReferedToJob2(jobId, auditorUserAccountId);

		return result;
	}
}
