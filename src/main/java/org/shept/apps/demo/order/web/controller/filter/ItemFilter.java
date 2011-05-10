/**
 * 
 */
package org.shept.apps.demo.order.web.controller.filter;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.shept.apps.demo.order.orm.Item;
import org.shept.persistence.ModelCreation;
import org.springframework.beans.support.SortDefinition;
import org.springframework.util.StringUtils;



/**
 *  
 * @version $Rev: 74 $
 * @author Andreas Hahn
 *
 */
public class ItemFilter extends DefaultCriteriaFilter implements Serializable {
	
	private String name;

	private String itemnumber;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	  * @see org.shept.apps.demo.web.controller.filter.DefaultCriteriaFilter#getCriteria(org.springframework.beans.support.SortDefinition)
	  */
	@Override
	public DetachedCriteria getCriteria(SortDefinition sortDefinition) {
	  DetachedCriteria crit = super.getCriteria(sortDefinition);
		 if (StringUtils.hasText(name)) {
			   crit.add(Restrictions.ilike("name", "%"+name+"%"));
		 }
		 if (StringUtils.hasText(itemnumber)) {
			   crit.add(Restrictions.ilike("itemnumber", "%"+itemnumber+"%"));
		 }
	 return crit;
	}	
	
	
	@Override
	public ModelCreation getNewModelTemplate() {
		return new Item();
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

}
