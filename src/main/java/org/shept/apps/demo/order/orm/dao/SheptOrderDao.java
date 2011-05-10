/**
 * 
 */
package org.shept.apps.demo.order.orm.dao;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;

import org.shept.apps.demo.order.orm.Item;
import org.shept.apps.demo.order.orm.OrderItem;
import org.shept.org.springframework.beans.support.PageableList;
import org.shept.org.springframework.beans.support.Refreshable;
import org.shept.org.springframework.orm.hibernate3.support.HibernateDaoSupportExtended;
import org.shept.persistence.ModelCreation;
import org.shept.persistence.provider.DaoUtils;


/**
 *  
 * @version $Rev: 73 $
 * @author Andreas Hahn
 *
 */
public class SheptOrderDao extends HibernateDaoSupportExtended implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	/**
	 * do not serialize / deserialize when parent objects require serialization
	 * 
	 * @throws ObjectStreamException
	 */
	protected Object writeReplace() throws ObjectStreamException{
		return null;
	}
	 
	/**
	 * do not serialize / deserialize when parent objects require serialization
	 * 
	 * @throws ObjectStreamException
	 */
	protected Object readResolve() throws ObjectStreamException {
		return null;
	}
	
	public void saveOrderItems(PageableList<OrderItem> pageable) {
		List<OrderItem> ois = pageable.getPageList();
		ModelCreation modelCreation = (ModelCreation) pageable.getNewModelTemplate();
		for (OrderItem oi : ois) {
			if (oi.getItemId() != null && oi.getItem() == null) {
				oi.setItem((Item) getHibernateTemplateExtended().get(Item.class, oi.getItemId()));
			}
			boolean newModel = DaoUtils.isNewModel(this, oi);
			if (newModel) {
				if (modelCreation == null || modelCreation.isCreationAllowed(oi)) {
					this.getHibernateTemplate().save(oi);											
				}
			} else {
				// this MUST be a saveOrUpdate because we also may have user supplied keys, so update is not sufficient in these cases
				this.getHibernateTemplate().saveOrUpdate(oi);				
			}
		}
	}

}
