
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
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
		int countUser;
		int threadId;

		Principal principal;
		int principalId;

		threadId = request.getModel().getInteger("id");

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
