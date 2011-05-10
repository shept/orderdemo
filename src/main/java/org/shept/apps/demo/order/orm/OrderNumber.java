package org.shept.apps.demo.order.orm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderNumber  implements Serializable {
	/**
	 * 
	 */

private static final long serialVersionUID = 1L;

private Integer id;

@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}


}
