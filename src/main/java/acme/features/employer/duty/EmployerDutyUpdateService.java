
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
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


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

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

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
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
