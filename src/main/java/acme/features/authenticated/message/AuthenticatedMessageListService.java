
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


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
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findManyByThreadId(id);

		return result;
	}
}
