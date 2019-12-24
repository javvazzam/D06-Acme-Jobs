
package acme.entities.investors;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars"), @Index(columnList = "sector")
})
public class Investor extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Atributos
	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				statement;

	@Range(min = 0, max = 5, message = "The stars must be in range 0 to 5")
	@Digits(integer = 1, fraction = 1, message = "Stars must contain only one integer and one decimal number")
	private Double				stars;
}
