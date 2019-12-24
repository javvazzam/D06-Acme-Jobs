
package acme.entities.duties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Duty extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;
	//Attributes

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	@Range(min = 0, max = 100, message = "Percentage must be between zero and one hundred.")
	private Double				timeAmount;

	//Derived attributes

	//Relationships

	@NotNull
	@Valid
	@ManyToOne
	private Job					job;

}
