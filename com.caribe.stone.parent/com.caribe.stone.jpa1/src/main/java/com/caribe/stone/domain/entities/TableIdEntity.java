package com.caribe.stone.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

@MappedSuperclass
public abstract class TableIdEntity {

	private Long id;
	
/**
 * TableName:ID_GEN 
 *	GEN_NAME  	GEN_VAL  
 *Addr_Gen     	10100
 */
    @TableGenerator(name="Address_Gen",
            table="ID_GEN",
            pkColumnName="GEN_NAME",
            valueColumnName="GEN_VAL",
            pkColumnValue="Addr_Gen",
            initialValue=10000,
            allocationSize=100)
    @Id @GeneratedValue(strategy=GenerationType.TABLE,
                        generator= "Address_Gen")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
