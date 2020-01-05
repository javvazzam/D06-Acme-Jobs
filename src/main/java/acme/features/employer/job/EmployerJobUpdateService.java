
package acme.features.employer.job;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

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

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		if (entity.isFinalMode()) {
			model.setAttribute("status", "Published");
		} else {
			model.setAttribute("status", "Draft");
		}

		request.unbind(entity, model, "referenceNumber", "title", "deadline", "salary", "moreInfo", "status", "descriptor");
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

		int jobId;
		jobId = entity.getId();

		//Comprueba si el job está ya publicado
		errors.state(request, !entity.isFinalMode(), "status", "employer.job.isAlreadyPublished");

		//Comprueba que los duties sumen un 100% y que el descriptor no esté vacío si se quiere publicar el job

		Double suma = this.repository.sumTimeAmountDutyByJob(jobId);
		Boolean sumUp;
		String description = request.getModel().getAttribute("description").toString();
		if (request.getModel().getAttribute("status").equals("Published")) {
			if (suma != null) {
				sumUp = suma == 100;
				//Error de la suma de los duties

			} else {
				sumUp = false;
			}
			errors.state(request, sumUp, "status", "employer.job.dutiesNotSumUp");
			//Error del descriptor
			errors.state(request, !description.isEmpty(), "description", "employer.job.descriptorIsEmpty");
			//Comprueba el spam
			String referenceNumber = entity.getReferenceNumber();
			String[] referenceNumberArray = referenceNumber.split(" ");
			String title = entity.getTitle();
			String[] titleArray = title.split(" ");
			String descriptionSpam = entity.getDescription();
			String[] descriptionArray = descriptionSpam.split(" ");

			Customization customisation = this.repository.findCustomization();

			String spamWords = customisation.getSpam();
			String[] spamArray = spamWords.split(",");
			Double threshold = customisation.getThreshold();

			List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());

			Integer numSpamReferenceNumber = (int) IntStream.range(0, referenceNumberArray.length).boxed().map(x -> referenceNumberArray[x].trim()).filter(i -> spamList.contains(i)).count();
			Integer numSpamTitle = (int) IntStream.range(0, titleArray.length).boxed().map(x -> titleArray[x].trim()).filter(i -> spamList.contains(i)).count();
			Integer numSpamDescription = (int) IntStream.range(0, descriptionArray.length).boxed().map(x -> descriptionArray[x].trim()).filter(i -> spamList.contains(i)).count();

			boolean isFreeOfSpamReferenceNumber = true;
			boolean isFreeOfSpamTitle = true;
			boolean isFreeOfSpamDescription = true;

			if (numSpamReferenceNumber != 0) {
				isFreeOfSpamReferenceNumber = 100 * numSpamReferenceNumber / referenceNumberArray.length < threshold;
			}
			if (numSpamTitle != 0) {
				isFreeOfSpamTitle = 100 * numSpamTitle / titleArray.length < threshold;
			}
			if (numSpamDescription != 0) {
				isFreeOfSpamDescription = 100 * numSpamDescription / descriptionArray.length < threshold;
			}
			errors.state(request, isFreeOfSpamReferenceNumber, "referenceNumber", "employer.job.spamWords");
			errors.state(request, isFreeOfSpamTitle, "title", "employer.job.spamWords");
			errors.state(request, isFreeOfSpamDescription, "description", "employer.job.spamWords");
		}

		//Comprueba que status es "Published" o "Draft"
		Boolean statusCorrect = request.getModel().getAttribute("status").equals("Published") || request.getModel().getAttribute("status").equals("Draft");
		errors.state(request, statusCorrect, "status", "employer.job.statusNotCorrect");

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		if (request.getModel().getAttribute("status").equals("Published")) {
			entity.setFinalMode(true);
		} else {
			entity.setFinalMode(false);
		}
		this.repository.save(entity);
	}

}
