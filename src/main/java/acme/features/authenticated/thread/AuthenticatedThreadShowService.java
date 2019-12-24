
package acme.features.authenticated.thread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
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
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccion = "../message/list_by_thread?id=" + entity.getId();
		model.setAttribute("direccion", direccion);
		request.unbind(entity, model, "title", "creationDate");

		String direccion2 = "../message/create?id=" + entity.getId();
		model.setAttribute("threadCreateMessage", direccion2);

		int idThread = entity.getId();
		String direccionAnadirUsuario = "../participant/create?threadid=" + idThread;
		model.setAttribute("direccionAnadirUsuario", direccionAnadirUsuario);

		String direccionListarUsuario = "../participant/list?threadid=" + idThread;
		model.setAttribute("direccionListarUsuario", direccionListarUsuario);
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneThreadById(id);
		//		result.getMessages().size();

		return result;
	}
}
