
package acme.features.authenticated.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
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
