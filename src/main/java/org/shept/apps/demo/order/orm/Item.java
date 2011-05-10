package org.shept.apps.demo.order.orm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.shept.persistence.ModelCreation;
import org.springframework.util.StringUtils;

@Entity
public class Item  implements Serializable, ModelCreation{
	/**
	 * 
	 */

private static final long serialVersionUID = 1L;

private Integer id;

private Integer version;

private String name;

private String itemnumber;

private Integer price;

private List<OrderItem> orderItems = new ArrayList<OrderItem>();

private Double quantity;

private Unit unit;

private Vatrate vatrate;

private Integer unit_id;

private Integer vatrate_id;

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
 * @return the name
 */
public String getName() {
return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
this.name = name;
}

/**
 * @return the itemnumber
 */
public String getItemnumber() {
	return itemnumber;
}

/**
 * @param itemnumber the itemnumber to set
 */
public void setItemnumber(String itemnumber) {
	this.itemnumber = itemnumber;
}

/**
 * @return the price
 */
public Integer getPrice() {
	return price;
}

/**
 * @param price the price to set
 */
public void setPrice(Integer price) {
	this.price = price;
}

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
public List<OrderItem> getOrderItems() {
	return orderItems;
}

public void setOrderItems(List<OrderItem> orderItems) {
	this.orderItems = orderItems;
}

/**
 * @return the quantity
 */
public Double getQuantity() {
	return quantity;
}

/**
 * @param quantity the quantity to set
 */
public void setQuantity(Double quantity) {
	this.quantity = quantity;
}

/**
 * @return the unit
 */
@ManyToOne()
@ForeignKey(name="fk_item_unit")
@JoinColumn(name="unit_id", insertable=false, updatable=false, nullable=false)
public Unit getUnit() {
	return unit;
}

/**
 * @param unit the unit to set
 */
public void setUnit(Unit unit) {
	this.unit = unit;
}

/**
 * @return the vatrate
 */
@ManyToOne()
@ForeignKey(name="fk_item_vatrate")
@JoinColumn(insertable=false, updatable=false, nullable=false)
public Vatrate getVatrate() {
	return vatrate;
}

/**
 * @param vatrate the vatrate to set
 */
public void setVatrate(Vatrate vatrate) {
	this.vatrate = vatrate;
}

/**
 * @return the unit_id
 */
@Column(name="unit_id", nullable=false)
public Integer getUnit_id() {
	return unit_id;
}

/**
 * @param unit_id the unit_id to set
 */
public void setUnit_id(Integer unit_id) {
	this.unit_id = unit_id;
}

/**
 * @return the vatrate_id
 */
@Column(name="vatrate_id", nullable=false)
public Integer getVatrate_id() {
	return vatrate_id;
}

/**
 * @param vatrate_id the vatrate_id to set
 */
public void setVatrate_id(Integer vatrate_id) {
	this.vatrate_id = vatrate_id;
}


public boolean isCreationAllowed(Object arg0) {
	Item item = (Item) arg0;
	if ( ! StringUtils.hasText(item.getName())) return false;
	if (StringUtils.hasText(name) && name.equalsIgnoreCase(item.getName())) {
		return false;
	}
	return true;
}

@Transient
public boolean isTransient() {
	return getId() == null;
}




}
