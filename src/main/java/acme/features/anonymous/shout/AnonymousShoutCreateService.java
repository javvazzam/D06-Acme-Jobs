
package acme.features.anonymous.shout;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	//Internal state
	@Autowired
	AnonymousShoutRepository repository;


	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("Jhon Doe");
		result.setText("Lorem Ipsum!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
