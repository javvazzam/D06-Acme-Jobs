
package acme.features.authenticated.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		Boolean result;
		int threadId;
		int messageId;
		List<Authenticated> usuariosThread;

		messageId = request.getModel().getInteger("id");
		Message m = this.repository.findOneMessageById(messageId);
		threadId = m.getThread().getId();
		usuariosThread = this.repository.findManyAuthenticatedByThreadId(threadId);
		result = usuariosThread.stream().map(u -> u.getUserAccount().getId()).anyMatch(i -> request.getPrincipal().getAccountId() == i);

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Message m1 = this.repository.findOneMessageById(request.getModel().getInteger("id"));
		model.setAttribute("user", m1.getUser().getUserAccount().getUsername());
		request.unbind(entity, model, "title", "moment", "tags", "body");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageById(id);

		return result;
	}
}
