
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;
		Collection<Application> applications;
		int numberApplications;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		applications = this.repository.findApplicationsByJob(jobId);
		numberApplications = applications.size();

		return result && numberApplications == 0;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

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

		String direccion3 = "../duty/create?id=" + entity.getId();
		model.setAttribute("jobCreateDuty", direccion3);

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		boolean result;
		boolean iAmPrincipal;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = entity.getId();
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		iAmPrincipal = employer.getUserAccount().getId() == principal.getAccountId();
		result = !job.isFinalMode() && iAmPrincipal;
		model.setAttribute("result", result);
		model.setAttribute("iAmPrincipal", iAmPrincipal);

		Collection<Application> a = this.repository.findApplicationsByJob(entity.getId());
		model.setAttribute("jobapplied", a.size() == 0);

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

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<Application> a = this.repository.findApplicationsByJob(entity.getId());

		errors.state(request, a.size() == 0, "referenceNumber", "employer.job.aWorkerHasAppliedForIt");

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		int jobId = entity.getId();

		//this.repository.deleteDutiesByJobId(jobId);

		Collection<Duty> d = this.repository.findDutyByJob(jobId);
		d.stream().forEach(x -> this.repository.delete(x));
		this.repository.delete(entity);

	}

}
