/**
 * 
 */
package org.shept.apps.demo.order.web.controller.postprocessors;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.shept.apps.demo.order.orm.Order;
import org.shept.apps.demo.order.orm.OrderItem;
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
public class CalculateOrderItemsPostProcessor implements ComponentPostprocessor {
	
	private SheptOrderDao dao;

	/* (non-Javadoc)
	 * @see org.shept.org.springframework.web.bind.support.ComponentPostprocessor#postHandle(org.springframework.web.context.request.WebRequest, org.springframework.web.servlet.ModelAndView, java.lang.String)
	 */
	public void postHandle(WebRequest request, ModelAndView modelAndView,
			String componentPath) {
		PageableList<OrderItem> ois = (PageableList<OrderItem>) ComponentUtils.getComponent(modelAndView, componentPath);
		BigDecimal sum = BigDecimal.ZERO;
		Long count = 0L;
		if (ois.getSource().size() > 0) {
			OrderItem oi = ois.getSource().get(0);
			Order order = oi.getOrder();
			
			List res = dao.getHibernateTemplateExtended().findByNamedQueryAndNamedParam("qryOrderSum", "orderId", order.getId());
			if (res.size() > 0 ){
				sum = (BigDecimal) res.get(0);
			}

			res = dao.getHibernateTemplateExtended().findByNamedQueryAndNamedParam("qryOrderCount", "orderId", order.getId());
			if (res.size() > 0 ){
				count = (Long) res.get(0);
			}
		}
		modelAndView.addObject("sum", sum);
		modelAndView.addObject("count", count);
	}

	/**
	 * @param dao the dao to set
	 */
	@Resource
	public void setDao(SheptOrderDao dao) {
		this.dao = dao;
	}

}
