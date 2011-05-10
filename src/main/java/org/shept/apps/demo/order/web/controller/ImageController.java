package org.shept.apps.demo.order.web.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shept.apps.demo.order.orm.Customer;
import org.shept.apps.demo.order.orm.dao.SheptOrderDao;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ImageController extends AbstractController {

	private SheptOrderDao dao;
	
	   @Override
	   protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		  
		  int id = ServletRequestUtils.getRequiredIntParameter(req, "id");
	      resp.setContentType("image/jpeg");
		  Customer foundPerson = (Customer) dao.getHibernateTemplateExtended().get(Customer.class, id);
		  if (foundPerson != null) {
		      resp.getOutputStream().write(foundPerson.getImage());
		  }
	      return null;
	   }

	/**
	 * @param dao the dao to set
	 */
	@Resource
	public void setDao(SheptOrderDao dao) {
		this.dao = dao;
	}
}
