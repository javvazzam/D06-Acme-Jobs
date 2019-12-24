
package acme.features.provider.solicitudes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Provider;
import acme.entities.solicitudes.Solicitud;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderSolicitudCreateService implements AbstractCreateService<Provider, Solicitud> {

	@Autowired
	ProviderSolicitudRepository repository;


	@Override
	public boolean authorise(final Request<Solicitud> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Solicitud> request, final Solicitud entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Solicitud> request, final Solicitud entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "deadline", "text", "reward");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Solicitud instantiate(final Request<Solicitud> request) {
		assert request != null;

		Solicitud result;
		result = new Solicitud();

		return result;
	}

	@Override
	public void validate(final Request<Solicitud> request, final Solicitud entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isDuplicated, isAccepted, isCurrencyEuro/* , isRewardNegative */;

		// BUSCA DUPLICADOS
		// isDuplicated == true -> salta un error
		isDuplicated = this.repository.findOneByTicker(entity.getTicker()) != null;
		errors.state(request, !isDuplicated, "ticker", "provider.solicitud.must-be-different-ticker");

		// BUSCA QUE EL CHECKBOX SE HAYA SELECCIONADO
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.solicitud.must-accept");

		// BUSCA QUE LA FECHA SEA FUTURA
		Calendar calendar;
		Date minimumDeadline;
		//Solicitud existing;

		if (entity.getDeadline() != null && !errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			//calendar.add(Calendar.DAY_OF_MONTH, 10);		// debe establecerse al menos 10 días desde el momento actual
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "provider.solicitud.must-be-future");
		} /*
			 * else if (entity.getDeadline() == null) {
			 * errors.state(request, false, "deadline", "provider.solicitud.must-be-filled");
			 * }
			 */

		// CURRENCY -> EUR
		if (entity.getReward() != null) {
			isCurrencyEuro = entity.getReward().getCurrency().equals("EUR") || entity.getReward().getCurrency().equals("€");
			//isRewardNegative = entity.getReward().getAmount().compareTo(0.) >= 0;
			errors.state(request, isCurrencyEuro, "reward", "provider.solicitud.correct-currency");
			//errors.state(request, isRewardNegative, "reward", "provider.solicitud.negative-reward");
		} /*
			 * else {
			 * errors.state(request, false, "reward", "provider.solicitud.must-be-filled-accordingly");
			 * }
			 */

	}

	@Override
	public void create(final Request<Solicitud> request, final Solicitud entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
