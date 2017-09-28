package by.intexsoft.auction.model;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public class AbstractEntity extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 8293080385374044030L;
}
