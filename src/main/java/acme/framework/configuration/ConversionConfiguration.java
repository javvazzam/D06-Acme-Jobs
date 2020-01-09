/*
 * ConversionConfiguration.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.framework.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import acme.framework.utilities.LocalisedDateFormatter;
import acme.framework.utilities.LocalisedDoubleFormatter;
import acme.framework.utilities.LocalisedMoneyFormatter;

@Configuration
public class ConversionConfiguration implements WebMvcConfigurer {

	// Beans ------------------------------------------------------------------

	@Override
	public void addFormatters(final FormatterRegistry registry) {
		LocalisedDateFormatter dateFormatter;
		LocalisedMoneyFormatter moneyFormatter;
		LocalisedDoubleFormatter doubleFormatter;

		dateFormatter = new LocalisedDateFormatter();
		registry.addFormatter(dateFormatter);

		moneyFormatter = new LocalisedMoneyFormatter();
		registry.addFormatter(moneyFormatter);

		doubleFormatter = new LocalisedDoubleFormatter();
		registry.addFormatter(doubleFormatter);
	}

}
