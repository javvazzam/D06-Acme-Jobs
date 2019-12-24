/*
 * AuthenticatedThreadCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.thread;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.participants.Participant;
import acme.entities.threads.Thread;
import acme.features.authenticated.participant.AuthenticatedParticipantRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedThreadCreateService implements AbstractCreateService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedThreadRepository		repository;

	@Autowired
	private AuthenticatedParticipantRepository	repositoryParticipant;

	// AbstractCreateService<Authenticated, Thread> ---------------------------


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isFreeOfSpamTitle;

		String title = entity.getTitle();
		String[] titleArray = title.split(" ");

		Customization customisation = this.repository.findCustomization();

		String spamWords = customisation.getSpam();
		String[] spamArray = spamWords.split(",");
		Double threshold = customisation.getThreshold();

		List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());

		Integer numSpamTitle = (int) IntStream.range(0, titleArray.length).boxed().map(x -> titleArray[x].trim()).filter(i -> spamList.contains(i)).count();

		if (numSpamTitle != 0) {
			isFreeOfSpamTitle = 100 * numSpamTitle / titleArray.length < threshold;
		} else {
			isFreeOfSpamTitle = true;
		}
		errors.state(request, isFreeOfSpamTitle, "title", "authenticated.thread.spamWords");
	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate");
	}

	@Override
	public Thread instantiate(final Request<Thread> request) {
		assert request != null;

		Date creationDate;
		creationDate = new Date(System.currentTimeMillis() - 1);

		Thread result;
		result = new Thread();
		result.setCreationDate(creationDate);

		return result;
	}

	@Override
	public void create(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		Participant participant = new Participant();
		participant.setThread(entity);
		Integer principalId = request.getPrincipal().getAccountId();
		Authenticated authenticated = this.repository.findOneAuthenticatedByUserAccountId(principalId);
		participant.setUser(authenticated);

		this.repository.save(entity);

		this.repositoryParticipant.save(participant);
	}

}
