
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
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
		int countUser;

		Principal principal;
		int principalId;

		messageId = request.getModel().getInteger("id");
		Message m = this.repository.findOneMessageById(messageId);
		threadId = m.getThread().getId();

		principal = request.getPrincipal();
		principalId = principal.getAccountId();
		countUser = this.repository.countAuthenticatedByThreadId(principalId, threadId);

		result = countUser != 0;			// si suma 1 significa que dicho thread pertenece a dicho Authenticated

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
