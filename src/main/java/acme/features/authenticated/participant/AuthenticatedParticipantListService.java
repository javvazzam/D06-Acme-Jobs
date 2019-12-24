
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListService implements AbstractListService<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		//		Collection<Participant> result;
		//		int idThread;
		//		idThread = request.getModel().getInteger("threadid");
		//		result = this.repository.findManyUsersByThread(idThread);
		//		List<Integer> idsParticipants = result.stream().map(x -> x.getUser().getUserAccount().getId()).collect(Collectors.toList());
		//
		//		int principalId = request.getPrincipal().getAccountId();
		//
		//		return idsParticipants.contains(principalId);
		return true;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("username", entity.getUser().getUserAccount().getUsername());
		request.unbind(entity, model, "user");
	}

	@Override
	public Collection<Participant> findMany(final Request<Participant> request) {
		assert request != null;
		Collection<Participant> result;
		int idThread;
		idThread = request.getModel().getInteger("threadid");
		result = this.repository.findManyUsersByThread(idThread);
		return result;
	}
}
