/*
 * AuthenticatedMessageCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.message;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository repository;

	// AbstractCreateService<Authenticated, Message> ---------------------------


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		Boolean result;
		int threadId;
		List<Authenticated> usuariosThread;

		threadId = request.getModel().getInteger("id");
		usuariosThread = this.repository.findManyAuthenticatedByThreadId(threadId);
		result = usuariosThread.stream().map(u -> u.getUserAccount().getId()).anyMatch(i -> request.getPrincipal().getAccountId() == i);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.must-accept");

		String title = entity.getTitle();
		String[] titleArray = title.split(" ");
		String tags = entity.getTags();
		String[] tagsArray = tags.split(" ");
		String body = entity.getBody();
		String[] bodyArray = body.split(" ");

		Customization customisation = this.repository.findCustomization();

		String spamWords = customisation.getSpam();
		String[] spamArray = spamWords.split(",");
		Double threshold = customisation.getThreshold();

		List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());

		Integer numSpamTitle = (int) IntStream.range(0, titleArray.length).boxed().map(x -> titleArray[x].trim()).filter(i -> spamList.contains(i)).count();
		Integer numSpamTags = (int) IntStream.range(0, tagsArray.length).boxed().map(x -> tagsArray[x].trim()).filter(i -> spamList.contains(i)).count();
		Integer numSpamBody = (int) IntStream.range(0, bodyArray.length).boxed().map(x -> bodyArray[x].trim()).filter(i -> spamList.contains(i)).count();
		boolean isFreeOfSpamTitle = true;
		boolean isFreeOfSpamTags = true;
		boolean isFreeOfSpamBody = true;

		if (numSpamTitle != 0) {
			isFreeOfSpamTitle = 100 * numSpamTitle / titleArray.length < threshold;
		}
		if (numSpamTags != 0) {
			isFreeOfSpamTags = 100 * numSpamTags / tagsArray.length < threshold;
			;
		}
		if (numSpamBody != 0) {
			isFreeOfSpamBody = 100 * numSpamBody / bodyArray.length < threshold;
			;
		}

		errors.state(request, isFreeOfSpamTitle, "title", "authenticated.message.spamWords");
		errors.state(request, isFreeOfSpamTags, "tags", "authenticated.message.spamWords");
		errors.state(request, isFreeOfSpamBody, "body", "authenticated.message.spamWords");
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

		int idThread = request.getModel().getInteger("id");
		String direccionThread = "../message/create?id=" + idThread;
		model.setAttribute("direccionThread", direccionThread);

		request.unbind(entity, model, "title", "moment", "tags", "body", "user", "thread");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		Principal principal = request.getPrincipal();
		int authenticatedId = principal.getAccountId();
		Authenticated authenticated = this.repository.findOneAuthenticatedByUserAccountId(authenticatedId);

		int idthread = request.getModel().getInteger("id");
		Thread thread = this.repository.findThreadById(idthread);

		Message result;
		result = new Message();
		result.setMoment(moment);
		result.setUser(authenticated);
		result.setThread(thread);

		return result;
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
