
package acme.features.worker.application;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors /* "moment", "worker" , "job" */);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int idJob = request.getModel().getInteger("id");
		String direccionJob = "../application/create?id=" + idJob;
		model.setAttribute("direccionJob", direccionJob);
		model.setAttribute("passAttempt", "");
		request.unbind(entity, model, "reference", "status", "moment", "job", "worker");
		request.unbind(entity, model, "statement", "skills", "qualifications");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		String status = "pending";

		Principal principal = request.getPrincipal();
		int workerId = principal.getAccountId();
		Worker worker = this.repository.findOneWorkerByUserAccountId(workerId);

		int idJob = request.getModel().getInteger("id");
		Job job = this.repository.findJobById(idJob);
		String qualifications = worker.getQualifications();
		String skills = worker.getSkills();
		result = new Application();
		result.setMoment(moment);
		result.setStatus(status);
		result.setWorker(worker);
		result.setJob(job);
		result.setSkills(skills);
		result.setQualifications(qualifications);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		GregorianCalendar calendar = new GregorianCalendar();
		Date minimumDeadline = calendar.getTime();

		errors.state(request, entity.getJob().getDeadline().after(minimumDeadline), "referenceJob", "worker.application.tryingToApplyPastJob");

		errors.state(request, entity.getJob().isFinalMode(), "referenceJob", "worker.application.tryingToApplyNotPublished");
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
