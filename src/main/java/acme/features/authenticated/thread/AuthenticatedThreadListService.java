
package acme.features.authenticated.thread;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedThreadListService implements AbstractListService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate");
	}

	@Override
	public Collection<Thread> findMany(final Request<Thread> request) {
		assert request != null;

		Collection<Thread> result;
		Collection<Participant> participants;

		Principal principal = request.getPrincipal();
		participants = this.repository.findManyByUserId(principal.getActiveRoleId());
		result = participants.stream().map(p -> p.getThread()).collect(Collectors.toList());

		return result;
	}
}
