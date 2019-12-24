
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "moment", "deadline", "text", "min", "max");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isDuplicated, isAccepted, isInRange, isMinCurrencyEuro, isMaxCurrencyEuro/* , isMinNegative, isMaxNegative */;

		// BUSCA DUPLICADOS
		isDuplicated = this.repository.findOneByTicker(entity.getTicker()) != null;
		errors.state(request, !isDuplicated, "ticker", "consumer.offer.must-be-different-ticker");

		// BUSCA QUE EL CHECKBOX SE HAYA SELECCIONADO
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "consumer.offer.must-accept");

		// BUSCA QUE LA FECHA SEA FUTURA
		Calendar calendar;
		Date minimumDeadline;

		if (entity.getDeadline() != null && !errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			//calendar.add(Calendar.MILLISECOND, 10);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "consumer.offer.must-be-future");
		} /*
			 * else if (entity.getDeadline() == null) {
			 * errors.state(request, false, "deadline", "consumer.offer.must-be-filled");
			 * }
			 */
		// CANTIDAD MÁXIMA DE RANGE SUPERIOR A LA INFERIOR
		if (entity.getMin() != null && entity.getMax() != null) {
			isInRange = entity.getMin().getAmount() <= entity.getMax().getAmount();
			errors.state(request, isInRange, "max", "consumer.offer.correct-range");
			errors.state(request, isInRange, "min", "consumer.offer.correct-range");
		}

		// CURRENCY -> EUR
		if (entity.getMin() != null) {
			isMinCurrencyEuro = entity.getMin().getCurrency().equals("EUR") || entity.getMin().getCurrency().equals("€");
			//isMinNegative = entity.getMin().getAmount().compareTo(0.) >= 0;
			errors.state(request, isMinCurrencyEuro, "min", "consumer.offer.correct-currency");
			//errors.state(request, isMinNegative, "min", "consumer.offer.negative-reward");
		} /*
			 * else {
			 * errors.state(request, false, "min", "consumer.offer.must-be-filled-accordingly");
			 * }
			 */
		if (entity.getMax() != null) {
			// CURRENCY -> EUR
			isMaxCurrencyEuro = entity.getMax().getCurrency().equals("EUR") || entity.getMax().getCurrency().equals("€");
			//isMaxNegative = entity.getMax().getAmount().compareTo(0.) >= 0;
			errors.state(request, isMaxCurrencyEuro, "max", "consumer.offer.correct-currency");
			//errors.state(request, isMaxNegative, "max", "consumer.offer.negative-reward");
		} /*
			 * else {
			 * errors.state(request, false, "max", "consumer.offer.must-be-filled-accordingly");
			 * }
			 */
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
