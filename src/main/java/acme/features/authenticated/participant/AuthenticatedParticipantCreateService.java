
package acme.features.authenticated.participant;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipantCreateService implements AbstractCreateService<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated user;

		int username = request.getModel().getInteger("usuarioelegido");
		user = this.repository.findOneAutheticatedById(username);
		entity.setUser(user);

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int idThread = request.getModel().getInteger("threadid");

		String direccionParticipant = "../participant/create?threadid=" + idThread;
		model.setAttribute("direccionParticipant", direccionParticipant);

		Collection<Authenticated> usuariosEnThread = this.repository.findManyAutheticatedByThread(idThread);
		Collection<Authenticated> usuariosTotales = this.repository.findManyAutheticated();
		Collection<Authenticated> usuarios = usuariosTotales.stream().filter(u -> !usuariosEnThread.contains(u)).collect(Collectors.toList());

		model.setAttribute("usuarios", usuarios);
		//model.setAttribute("username", entity.getUser().getUserAccount().getUsername());
		model.setAttribute("usuarioelegido", "");
		request.unbind(entity, model);

	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		Participant result;
		result = new Participant();
		int idThread = request.getModel().getInteger("threadid");
		Thread t = this.repository.findOneThreadById(idThread);
		result.setThread(t);

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {

		this.repository.save(entity);

	}

}
