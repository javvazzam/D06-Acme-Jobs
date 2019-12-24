
package acme.entities.participants;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Participant extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Thread				thread;

}
