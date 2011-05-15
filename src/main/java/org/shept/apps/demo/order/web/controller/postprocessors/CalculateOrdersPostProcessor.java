/**
 * 
 */
package org.shept.apps.demo.order.web.controller.postprocessors;

import java.util.List;

import javax.annotation.Resource;

import org.shept.apps.demo.order.orm.Order;
import org.shept.apps.demo.order.orm.dao.SheptOrderDao;
import org.shept.org.springframework.beans.support.PageableList;
import org.shept.org.springframework.web.bind.support.ComponentPostprocessor;
import org.shept.org.springframework.web.servlet.mvc.delegation.ComponentUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gerno
 *
 */
public class CalculateOrdersPostProcessor implements ComponentPostprocessor {
	
	private SheptOrderDao dao;

	/* (non-Javadoc)
	 * @see org.shept.org.springframework.web.bind.support.ComponentPostprocessor#postHandle(org.springframework.web.context.request.WebRequest, org.springframework.web.servlet.ModelAndView, java.lang.String)
	 */
	public void postHandle(WebRequest request, ModelAndView modelAndView,
			String componentPath) {
		PageableList<Order> ois = (PageableList<Order>) ComponentUtils.getComponent(modelAndView, componentPath);
		if (ois.getSource().size() > 0) {
			Order order = ois.getSource().get(0);
			
			List sums = dao.getHibernateTemplateExtended().findByNamedQueryAndNamedParam(
					"qryOrderSum4Customer", "customerId", order.getCustomer().getId());
			List counts = dao.getHibernateTemplateExtended().findByNamedQueryAndNamedParam(
					"qryOrderCount4Customer", "customerId", order.getCustomer().getId());
			modelAndView.addObject("sums", sums);
			modelAndView.addObject("counts", counts);
		}
	}

	/**
	 * @param dao the dao to set
	 */
	@Resource
	public void setDao(SheptOrderDao dao) {
		this.dao = dao;
	}

}
