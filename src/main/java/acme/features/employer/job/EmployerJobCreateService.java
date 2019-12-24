
package acme.features.employer.job;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
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

		request.unbind(entity, model, "referenceNumber", "title", "deadline", "finalMode");
		request.unbind(entity, model, "salary", "moreInfo", "description", "employer");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Job result;

		Principal principal = request.getPrincipal();
		int employerId = principal.getAccountId();
		Employer employer = this.repository.findOneEmployerByUserAccountId(employerId);

		result = new Job();
		result.setEmployer(employer);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		GregorianCalendar calendar = new GregorianCalendar();
		Date minimumDeadline = calendar.getTime();
		if (entity.getDeadline() != null) {
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "employer.job.tryingToCreatePastJob");
		}
		//Comprueba el spam
		/**
		 * String referenceNumber = entity.getReferenceNumber();
		 * String[] referenceNumberArray = referenceNumber.split(" ");
		 * String title = entity.getTitle();
		 * String[] titleArray = title.split(" ");
		 * String description = entity.getDescription();
		 * String[] descriptionArray = description.split(" ");
		 *
		 * Customization customisation = this.repository.findCustomization();
		 *
		 * String spamWords = customisation.getSpam();
		 * String[] spamArray = spamWords.split(",");
		 * Double threshold = customisation.getThreshold();
		 *
		 * List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());
		 *
		 * Integer numSpamReferenceNumber = (int) IntStream.range(0, referenceNumberArray.length).boxed().map(x -> referenceNumberArray[x].trim()).filter(i -> spamList.contains(i)).count();
		 * Integer numSpamTitle = (int) IntStream.range(0, titleArray.length).boxed().map(x -> titleArray[x].trim()).filter(i -> spamList.contains(i)).count();
		 * Integer numSpamDescription = (int) IntStream.range(0, descriptionArray.length).boxed().map(x -> descriptionArray[x].trim()).filter(i -> spamList.contains(i)).count();
		 *
		 * boolean isFreeOfSpamReferenceNumber = 100 * numSpamReferenceNumber / referenceNumberArray.length < threshold;
		 * boolean isFreeOfSpamTitle = 100 * numSpamTitle / titleArray.length < threshold;
		 * boolean isFreeOfSpamDescription = 100 * numSpamDescription / descriptionArray.length < threshold;
		 * errors.state(request, isFreeOfSpamReferenceNumber, "referenceNumber", "employer.job.spamWords");
		 * errors.state(request, isFreeOfSpamTitle, "title", "employer.job.spamWords");
		 * errors.state(request, isFreeOfSpamDescription, "description", "employer.job.spamWords");
		 **/
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
