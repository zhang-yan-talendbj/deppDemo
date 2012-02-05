package foo.domain.entity.one2one;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import foo.domain.entity.base.IdEntity;

@Entity
@Table(name = "T_PERSON")
public class Person extends IdEntity {
	@Column
	private String name;
	@Column
	private Date birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
