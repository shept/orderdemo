/**
 * 
 */
package org.shept.apps.demo.order.web.controller.filters;

import java.io.Serializable;

import org.shept.apps.demo.order.orm.Address;
import org.shept.apps.demo.order.orm.Customer;
import org.shept.persistence.ModelCreation;


/**
 *  
 * @version $Rev: 74 $
 * @author Andreas Hahn
 *
 */
public class AddressFilter extends DefaultCriteriaFilter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ModelCreation getNewModelTemplate() {
		return null;
	}

}
