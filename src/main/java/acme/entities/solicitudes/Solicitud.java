
package acme.entities.solicitudes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline"), @Index(columnList = "ticker")
})
public class Solicitud extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;
	//Attributes

	@Column(unique = true)
	@Length(min = 11, max = 11)
	@NotBlank
	@Pattern(regexp = "^R[A-Z]{4}-[0-9]{5}$")
	private String				ticker;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				text;

	@NotNull
	@Valid
	private Money				reward;

	//Derived attributes

	//Relationships
}
