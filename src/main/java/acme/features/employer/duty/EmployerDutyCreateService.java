
package acme.features.employer.duty;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccionDuty = "../duty/create?id=" + request.getModel().getInteger("id");
		model.setAttribute("direccionDuty", direccionDuty);

		request.unbind(entity, model, "title", "description", "timeAmount");
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;

		Duty result;

		Integer jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);

		result = new Duty();
		result.setJob(job);
		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Comprobamos que las duties creadas no sumen un total mayor a 100%
		int jobId;
		Boolean sumUp;

		jobId = request.getModel().getInteger("id");
		Double suma = this.repository.sumTimeAmountDutyByJob(jobId);

		if (suma != null) {
			Double sumaDuties = suma + entity.getTimeAmount();
			sumUp = sumaDuties <= 100;
		} else {
			sumUp = entity.getTimeAmount() <= 100;
		}
		errors.state(request, sumUp, "timeAmount", "employer.job.cannotCreateDuty");

		//Comprueba el spam
		String title = entity.getTitle();
		String[] titleArray = title.split(" ");
		String description = entity.getDescription();
		String[] descriptionArray = description.split(" ");

		Customization customisation = this.repository.findCustomization();

		String spamWords = customisation.getSpam();
		String[] spamArray = spamWords.split(",");
		Double threshold = customisation.getThreshold();

		List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());

		Integer numSpamTitle = (int) IntStream.range(0, titleArray.length).boxed().map(x -> titleArray[x].trim()).filter(i -> spamList.contains(i)).count();
		Integer numSpamDescription = (int) IntStream.range(0, descriptionArray.length).boxed().map(x -> descriptionArray[x].trim()).filter(i -> spamList.contains(i)).count();

		boolean isFreeOfSpamTitle = 100 * numSpamTitle / titleArray.length < threshold;
		boolean isFreeOfSpamDescription = 100 * numSpamDescription / descriptionArray.length < threshold;
		errors.state(request, isFreeOfSpamTitle, "title", "employer.duty.spamWords");
		errors.state(request, isFreeOfSpamDescription, "description", "employer.duty.spamWords");
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
