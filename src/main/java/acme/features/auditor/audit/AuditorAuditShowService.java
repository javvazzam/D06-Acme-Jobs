
package acme.features.auditor.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditShowService implements AbstractShowService<Auditor, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int auditorId = principal.getAccountId();

		int idAudit = request.getModel().getInteger("id");
		Audit audit = this.repository.findOneAuditById(idAudit);

		Auditor auditor = this.repository.findOneAuditorByUserAccountId(auditorId);

		boolean draftPrincipal = !audit.isFinalMode() && !audit.getAuditor().equals(auditor);

		return auditor != null && audit.getJob().isFinalMode() && !draftPrincipal;
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

		boolean isPublished = result.isFinalMode();
		model.setAttribute("isPublished", isPublished);

		Auditor auditor = result.getAuditor();
		model.setAttribute("associatedAuditor", auditor.getUserAccount().getUsername());

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
