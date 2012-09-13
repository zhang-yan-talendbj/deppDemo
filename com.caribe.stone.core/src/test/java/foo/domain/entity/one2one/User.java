package foo.domain.entity.one2one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import foo.domain.entity.base.IdEntity;

@Entity
@Table(name="T_USER")
public class User  {

	@Column
	private String userName;
	@Column
	private String email;
	// String name() default "";
	// boolean unique() default false;
	// boolean nullable() default true;
	// boolean insertable() default true;
	// boolean updatable() default true;
	// String columnDefinition() default "";
	// String table() default "";
	// int length() default 255;
	// int precision() default 0;
	// int scale() default 0;
	@Column
	private String password;
	
	// Class targetEntity() default void.class; 默认标注当前实体
	// targetEntity=Customer.class
	// CascadeType[] cascade() default {};级联样式类型，对当前实体进行操作时策略
	// FetchType fetch() default FetchType.EAGER;实体加载策略
	// boolean optional() default true; 是否能够为空
	// String mappedBy() default "";
	// String name() default "";
	// String referencedColumnName() default "";
	// boolean unique() default false;
	// boolean nullable() default true;
	// boolean insertable() default true;
	// boolean updatable() default true;
	// String columnDefinition() default "";
	// String table() default "";
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="person_id")
	private Person person;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
