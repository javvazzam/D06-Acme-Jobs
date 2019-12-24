
package acme.entities.customization;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customization extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				spam;

	@NotNull
	@Range(min = 0, max = 100, message = "threshold is a percentage, this number must be in range 0-100")
	private Double				threshold;
}
