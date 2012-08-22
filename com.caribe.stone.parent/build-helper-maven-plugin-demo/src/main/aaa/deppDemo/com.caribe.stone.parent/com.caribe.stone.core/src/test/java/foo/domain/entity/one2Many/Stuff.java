package foo.domain.entity.one2Many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import foo.domain.entity.base.IdEntity;

@Entity
@Table(name = "T_STUFF")
public class Stuff extends IdEntity{
	@Column
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="box_id")
	private Box box;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box b) {
		this.box = b;
	}

}
