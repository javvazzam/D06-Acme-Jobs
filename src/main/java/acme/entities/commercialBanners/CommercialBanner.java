
package acme.entities.commercialBanners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.banners.Banner;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	// Serialisation identifier
	private static final long serialVersionUID = 1L;

	// Atributos

	@NotBlank
	@CreditCardNumber
	private String creditCard;
}
