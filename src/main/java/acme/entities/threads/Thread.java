
package acme.entities.threads;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thread extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;
	//Attributes

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationDate;

	//Derived attributes

	//Relationships

}
