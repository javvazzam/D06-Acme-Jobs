
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		int dutyId;
		Duty duty;
		Job job;
		Employer employer;
		Principal principal;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneById(dutyId);
		job = duty.getJob();
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.isFinalMode() || !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		boolean notFinalMode;
		boolean iAmPrincipal;
		int dutyId;
		Duty duty;
		Job job;
		Employer employer;
		Principal principal;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneById(dutyId);
		job = duty.getJob();
		employer = job.getEmployer();
		principal = request.getPrincipal();
		iAmPrincipal = employer.getUserAccount().getId() == principal.getAccountId();
		notFinalMode = /* job.isFinalMode() || */!job.isFinalMode() && iAmPrincipal;
		model.setAttribute("notFinalMode", notFinalMode);
		model.setAttribute("iAmPrincipal", iAmPrincipal);

		request.unbind(entity, model, "title", "description", "timeAmount");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
