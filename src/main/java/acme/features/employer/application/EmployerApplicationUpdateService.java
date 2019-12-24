
package acme.features.employer.application;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.customization.Customization;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		Application result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String status = entity.getStatus();
		String justification = entity.getJustification().trim();
		Boolean justificationIfRejected = justification.isEmpty() && status.equals("rejected");
		errors.state(request, !justificationIfRejected, "justification", "employer.application.justificationIfRejected");

		//Comprueba el spam
		String[] justificationArray = justification.split(" ");

		if (justificationArray.length != 0) {
			Customization customisation = this.repository.findCustomization();

			String spamWords = customisation.getSpam();
			String[] spamArray = spamWords.split(",");
			Double threshold = customisation.getThreshold();

			List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());

			Integer numSpamJustification = (int) IntStream.range(0, justificationArray.length).boxed().map(x -> justificationArray[x].trim()).filter(i -> spamList.contains(i)).count();

			boolean isFreeOfSpamJustification = 100 * numSpamJustification / justificationArray.length < threshold;
			errors.state(request, isFreeOfSpamJustification, "justification", "employer.application.spamWords");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		// se actualiza el estado cuando
		Date newMoment;
		newMoment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(newMoment);

		this.repository.save(entity);
	}

}
