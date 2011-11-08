package foo.domain.entity.one2Many;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import foo.domain.entity.base.IdEntity;

@Entity
@Table(name = "T_BOX")
public class Box extends IdEntity {
	@Column
	private Long boxNumber;
	@Column
	private Integer size;
	@OneToMany(mappedBy = "box", cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Collection<Stuff> stuff;

	public Long getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(Long boxNumber) {
		this.boxNumber = boxNumber;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Collection<Stuff> getStuff() {
		return stuff;
	}

	public void setStuff(Collection<Stuff> stuff) {
		this.stuff = stuff;
	}

}
