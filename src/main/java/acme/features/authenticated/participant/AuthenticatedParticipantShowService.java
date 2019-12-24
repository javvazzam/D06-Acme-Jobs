
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipantShowService implements AbstractShowService<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		String username = entity.getUser().getUserAccount().getUsername();
		model.setAttribute("username", username);
		request.unbind(entity, model, "user");

	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
