
package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorDutyListService implements AbstractListService<Auditor, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuditorDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
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
