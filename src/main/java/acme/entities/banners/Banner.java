
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Atributos
	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				url;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;
}
