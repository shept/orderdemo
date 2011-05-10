/**
 * 
 */
package org.shept.apps.demo.order.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.shept.apps.demo.order.orm.Item;
import org.shept.apps.demo.order.orm.dao.SheptOrderDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gerno
 *
 */
@Controller
@RequestMapping("/autoComplete")
public class AutoCompleteController {


	protected final Log logger = LogFactory.getLog(getClass());
	
	private SheptOrderDao dao;
	
	@RequestMapping(value="/items", method = RequestMethod.GET)
	public void log(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("inputId") String inputId, 
			@RequestParam("text") String text) {
		
//		String queryString =" from Item i where i.name ilike '" + text + "%'";
//		List li = dao.getHibernateTemplateExtended().find(queryString);

//		text = URLDecoder.decode(text);
		DetachedCriteria crit = DetachedCriteria.forClass(Item.class);
		crit.add(Restrictions.ilike("name", "%" + text + "%"));
		crit.addOrder(Order.asc("name"));
		List li = dao.getHibernateTemplateExtended().findByCriteria(crit, -1, 20);
		
		JSONArray arrayObj = new JSONArray();
		Iterator iter = li.iterator();
		
		while (iter.hasNext()) {
			Item it = (Item) iter.next(); 
			String id = it.getId().toString();
			String name = it.getName();
			JSONObject object=new JSONObject();
			object.put(id,name);
			arrayObj.add(object);
		}
		
		try {
			arrayObj.write(response.getWriter());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
