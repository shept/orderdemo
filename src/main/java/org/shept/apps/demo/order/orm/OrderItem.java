package org.shept.apps.demo.order.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.shept.persistence.ModelCreation;

@Entity
public class OrderItem  implements Serializable, ModelCreation{
	/**
	 * 
	 */

private static final long serialVersionUID = 1L;

private Integer id;

private Integer version;

private String name;

private Integer quantity;

private Integer itemId;

private Item item;

private Order order;

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
 * @return the quantity
 */
public Integer getQuantity() {
	return quantity;
}

/**
 * @param quantity the quantity to set
 */
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}

/**
 * @return the item
 */
@ManyToOne()
@ForeignKey(name="fk_orderitem_item")
@JoinColumn(insertable=false, updatable=false, nullable=false)
public Item getItem() {
	return item;
}

/**
 * @param item the item to set
 */
public void setItem(Item item) {
	this.item = item;
}

/**
 * @return the itemId
 */
@Column(name="item_id")
public Integer getItemId() {
	return itemId;
}

/**
 * @param itemId the itemId to set
 */
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}

@Transient
public Integer getItemIdComplete() {
	return itemId;
}

@Transient
public void setItemIdComplete(Integer itemId) {
	if (itemId != null && itemId != 0) {
		setItemId(itemId);
	}	
}

@ManyToOne()
@ForeignKey(name="fk_orderitem_order")
public Order getOrder() {
	return order;
}

public void setOrder(Order order) {
	this.order = order;
}

public boolean isCreationAllowed(Object arg0) {
	OrderItem item = (OrderItem) arg0;
	if (item == null) {
		return false;
	}
	if (item.getQuantity() == null || item.getQuantity() == 0 
		|| item.getItemId() == null || item.getOrder() == null) {
		return false;
	} else {
		return true;
	}
}

@Transient
public boolean isTransient() {
	return getId() == null;
}

/**
 * let's do some chaining here
 * @param order
 */
public void initialize(Order order) {
	this.order = order;
}

/**
 * set a default quantity when initialized
 */
public void initialize(String test) {
	this.quantity = 1;
}

@Transient
public BigDecimal getPrice() {
	if (quantity == null || getItem() == null || getItem().getPrice() == null) {
		return BigDecimal.ZERO;
	}
	return getItem().getPrice();
}

@Transient
public BigDecimal getTotalPrice() {
	if (quantity == null || getItem() == null || getItem().getPrice() == null) {
		return BigDecimal.ZERO;
	}
	return getItem().getPrice().multiply(new BigDecimal(quantity));
}

}
