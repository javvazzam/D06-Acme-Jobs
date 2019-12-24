
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;
		Challenge result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChallengeById(id);
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// BUSCA QUE LA FECHA SEA FUTURA
		Calendar calendar;
		Date minimumDeadline;

		boolean isGoldRewardEuro, isSilverRewardEuro, isBronzeRewardEuro, isGoldGreaterSilver, isSilverGreaterBronze;

		if (entity.getDeadline() != null && !errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.challenges.incorrect-date");
		} else if (entity.getDeadline() == null) {
			errors.state(request, false, "deadline", "administrator.challenge.must-be-filled");
		}

		if (entity.getGoldReward() != null) {
			// CURRENCY -> EUR
			isGoldRewardEuro = entity.getGoldReward().getCurrency().equals("EUR") || entity.getGoldReward().getCurrency().equals("€");
			errors.state(request, isGoldRewardEuro, "goldReward", "administrator.challenges.incorrect-currency");
		} else {
			errors.state(request, false, "goldReward", "administrator.challenge.must-be-filled-accordingly");
		}

		if (entity.getBronzeReward() != null) {
			// CURRENCY -> EUR
			isBronzeRewardEuro = entity.getBronzeReward().getCurrency().equals("EUR") || entity.getBronzeReward().getCurrency().equals("€");
			errors.state(request, isBronzeRewardEuro, "bronzeReward", "administrator.challenges.incorrect-currency");
		} else {
			errors.state(request, false, "bronzeReward", "administrator.challenge.must-be-filled-accordingly");
		}

		if (entity.getSilverReward() != null) {
			// CURRENCY -> EUR
			isSilverRewardEuro = entity.getSilverReward().getCurrency().equals("EUR") || entity.getSilverReward().getCurrency().equals("€");
			errors.state(request, isSilverRewardEuro, "silverReward", "administrator.challenges.incorrect-currency");
		} else {
			errors.state(request, false, "silverReward", "administrator.challenge.must-be-filled-accordingly");
		}

		//ADECUATED REWARDS
		if (entity.getGoldReward() != null && entity.getSilverReward() != null) {
			isGoldGreaterSilver = entity.getGoldReward().getAmount() > entity.getSilverReward().getAmount();
			errors.state(request, isGoldGreaterSilver, "goldReward", "administrator.challenge.gold-greater-silver");
		}

		//ADECUATED REWARDS
		if (entity.getBronzeReward() != null && entity.getSilverReward() != null) {
			isSilverGreaterBronze = entity.getSilverReward().getAmount() > entity.getBronzeReward().getAmount();
			errors.state(request, isSilverGreaterBronze, "silverReward", "administrator.challenge.silver-greater-bronze");
		}
	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
