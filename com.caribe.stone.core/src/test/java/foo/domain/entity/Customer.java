package foo.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import foo.domain.entity.base.IdEntity;

@Entity
@Table(name = "customer")
// String name() default "";
// String catalog() default "";
// String schema() default "";
// UniqueConstraint[] uniqueConstraints() default {};
public class Customer   implements Serializable {

	public Customer() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8341319375174461587L;

	@Column
	private String name;
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

	// Class targetEntity() default void.class; 默认标注当前实体
	// targetEntity=Customer.class
	// CascadeType[] cascade() default {};级联样式类型，对当前实体进行操作时策略
	// FetchType fetch() default FetchType.EAGER;实体加载策略
	// boolean optional() default true; 是否能够为空
	// String mappedBy() default "";
	@OneToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "address_id", unique = true, nullable = false, updatable = false)
	private Address address;
	// String name() default "";
	// String referencedColumnName() default "";
	// boolean unique() default false;
	// boolean nullable() default true;
	// boolean insertable() default true;
	// boolean updatable() default true;
	// String columnDefinition() default "";
	// String table() default "";
//	@Basic(fetch = FetchType.LAZY)
	@Column
	private String lazyProperty;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getLazyProperty() {
		return lazyProperty;
	}

	public void setLazyProperty(String lazyProperty) {
		this.lazyProperty = lazyProperty;
	}
}
