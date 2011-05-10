package org.shept.apps.demo.order.web.controller.postprocessors;
/**
 *
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.shept.apps.demo.order.orm.Item;
import org.shept.apps.demo.order.orm.Unit;
import org.shept.apps.demo.order.orm.Vatrate;
import org.shept.apps.demo.order.orm.dao.SheptOrderDao;
import org.shept.org.springframework.web.bind.support.ComponentPostprocessor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version $$Id: $$
 *
 * @author Andi
 *
 */
public class CommonPostProcessor extends WebApplicationObjectSupport implements
ComponentPostprocessor {

/* (non-Javadoc)
* @see org.shept.org.springframework.web.bind.support.ComponentPostprocessor#postHandle(org.springframework.web.context.request.WebRequest, org.springframework.web.servlet.ModelAndView, java.lang.String)
*/
public void postHandle(WebRequest request, ModelAndView mv,
String componentPath) {

if (mv != null) {
	mv.addObject("addressTypes", getAddressTypes(getMessageSourceAccessor()));
	mv.addObject("allItems", getAllItems(getMessageSourceAccessor()));
	mv.addObject("allUnits", getAllUnits(getMessageSourceAccessor()));
	mv.addObject("allVatrates", getAllVatrates(getMessageSourceAccessor()));
	}
}

private Map<Integer, String> getAddressTypes(MessageSourceAccessor accessor ) {
	Map <Integer, String> modes = new LinkedHashMap <Integer, String>();
	modes.put(0, "default address");
	modes.put(1, "shipping address");
	modes.put(2, "legal address");
	return modes;
	}

private SheptOrderDao dao;

@Resource
public void setDao(SheptOrderDao dao) {
 this.dao = dao;
}

private List<Item> getAllItems(MessageSourceAccessor accessor ) {
	return dao.getHibernateTemplate().loadAll(Item.class);
}
private List<Unit> getAllUnits(MessageSourceAccessor accessor ) {
	return dao.getHibernateTemplate().loadAll(Unit.class);
}
private List<Vatrate> getAllVatrates(MessageSourceAccessor accessor ) {
	List<Vatrate> vatrates = dao.getHibernateTemplate().loadAll(Vatrate.class);
	return vatrates;
}
}