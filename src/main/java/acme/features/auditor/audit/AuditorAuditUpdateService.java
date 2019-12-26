
package acme.features.auditor.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditUpdateService implements AbstractUpdateService<Auditor, Audit> {

	@Autowired
	AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;
		Principal principal = request.getPrincipal();

		int auditorId = principal.getAccountId();

		int idAudit = request.getModel().getInteger("id");
		Audit audit = this.repository.findOneAuditById(idAudit);

		Auditor auditor = this.repository.findOneAuditorByUserAccountId(auditorId);

		return auditor != null && audit.getJob().isFinalMode() && !audit.isFinalMode() && audit.getAuditor().equals(auditor);
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

		String direccionAudit = "../audit/update?id=" + request.getModel().getInteger("id");
		model.setAttribute("direccionAudit", direccionAudit);

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		request.unbind(entity, model, "title", "body", "job", "auditor");
	}

	@Override
	public void validate(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Comprueba que status es "Published" o "Draft"
		Boolean statusCorrect = request.getModel().getAttribute("status").equals("Published") || request.getModel().getAttribute("status").equals("Draft");
		errors.state(request, statusCorrect, "status", "auditor.job.audit.statusNotCorrect");

	}

	@Override
	public void update(final Request<Audit> request, final Audit entity) {
		assert request != null;
		assert entity != null;

		boolean finalMode;
		String status = request.getModel().getAttribute("status").toString();
		if (status.equals("Published")) {
			finalMode = true;
		} else {
			finalMode = false;
		}
		entity.setFinalMode(finalMode);

		// se actualiza el estado cuando
		Date newMoment;
		newMoment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(newMoment);

		this.repository.save(entity);
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
