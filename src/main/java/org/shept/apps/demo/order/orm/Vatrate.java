package org.shept.apps.demo.order.orm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.shept.persistence.ModelCreation;
import org.springframework.util.StringUtils;

@Entity
public class Vatrate implements Serializable, ModelCreation {
	/**
	 * 
	 */

private static final long serialVersionUID = 1L;

private Integer id;
private Integer version;

private Integer rate;

@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

@Version
public Integer getVersion() {
	return version;
}

public void setVersion(Integer version) {
	this.version = version;
}

/**
 * @return the rate
 */
public Integer getRate() {
	return rate;
}

/**
 * @param rate the rate to set
 */
public void setRate(Integer rate) {
	this.rate = rate;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public boolean isCreationAllowed(Object arg0) {
	return true;
}

@Transient
public boolean isTransient() {
	return getId() == null;
}


}
