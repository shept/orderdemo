/**
 * 
 */
package org.shept.apps.demo.order.web.controller.filters;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.shept.persistence.ModelCreation;
import org.shept.persistence.provider.hibernate.HibernateCriteriaDefinition;
import org.shept.persistence.provider.hibernate.HibernateCriteriaFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.SortDefinition;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *  
 * @version $Revision: 73 $
 * @author Andreas Hahn
 *
 */
public class DefaultCriteriaFilter extends HibernateCriteriaFilter implements HibernateCriteriaDefinition, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @return a default criteria definition by simply return an empty entity object
	 */
	@Override
	public DetachedCriteria getCriteria(SortDefinition sortDefinition) {
		return super.getCriteria(sortDefinition);
	}

	/**
	 * @return a null ModelCreation object meaning that new objects can't be created by default
	 */
	@Override
	public ModelCreation getNewModelTemplate() {
		return null;
	}

}
