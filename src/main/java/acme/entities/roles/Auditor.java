
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditor extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				firm;

	@NotBlank
	private String				statement;

	private boolean				request;

	@NotBlank
	private String				body;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
