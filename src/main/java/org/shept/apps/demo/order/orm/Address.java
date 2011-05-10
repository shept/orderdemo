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
public class Address implements Serializable, ModelCreation {
	/**
	 * 
	 */

private static final long serialVersionUID = 1L;

private Integer id;
private Integer version;

private Integer addressType;
private String country;
private String city;
private String zipcode;
private String street;
private String number;

private Customer customer;

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

public Integer getAddressType() {
	return addressType;
}

public void setAddressType(Integer addressType) {
	this.addressType = addressType;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getZipcode() {
	return zipcode;
}

public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

@ManyToOne()
@ForeignKey(name="fk_address_customer")
public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public boolean isCreationAllowed(Object arg0) {
	Address address = (Address) arg0;
	if ( ! StringUtils.hasText(address.getCity())) return false;
	if (StringUtils.hasText(city) && city.equalsIgnoreCase(address.getCity())) {
		return false;
	}
	return true;
}

@Transient
public boolean isTransient() {
	return getId() == null;
}

/**
 * let's do some chaining here
 * @param person
 */
public void initialize(Customer customer) {
	this.customer = customer;
}
}
