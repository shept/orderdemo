/**
 * 
 */
package org.shept.apps.demo.order.web.controller.postprocessors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.RedirectView;

/** 
 * @version $$Id: FormCompletionInterceptor.java,v 1.1 2009/11/27 18:53:12 oops.oops Exp $$
 *
 * @author Andi
 *
 */
public class FormCompletionInterceptor extends HandlerInterceptorAdapter {
	
	public static final String ERROR_MSG = "formErrorMessage";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// do not apply model attributes to redirections
		if (modelAndView == null || modelAndView.getView() instanceof RedirectView) {
			return;
		}

		RequestContext rc = new RequestContext(request);
		BindingResult res = null;
		
		for (String name : modelAndView.getModel().keySet()) {
			if (name.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
				res = BindingResultUtils.getBindingResult(modelAndView.getModel(), name.substring(BindingResult.MODEL_KEY_PREFIX.length()));
			}
		}
		
		HashMap<String, String> controlModel = new HashMap<String, String>();
		String errStr = "";
		if (res != null && res.hasErrors()) {
			// errors auflisten
			List errList = res.getAllErrors();
			Iterator it = errList.iterator();
			if (it.hasNext()) {
				ObjectError error = (ObjectError) it.next();
				String myErrCode = error.getCode();
				// check all possible Errorcodes
				for (String eStr : error.getCodes()) {
					String errMsg = rc.getMessage(eStr, "");
					if (errMsg.length() > 0) {
						myErrCode = eStr;
						break;
					}
				}
				errStr = errStr
						+ rc.getMessage(myErrCode, error.getArguments(), error
								.getDefaultMessage());
				// rc.getMessage(error.getCode(), error.rejectedValue,
				// error.getDefaultMessage());
			}
			if (it.hasNext()) {
				errStr = errStr + "  ...";
			}
		}

		controlModel.put(ERROR_MSG, errStr);
//		WebUtils.setSessionAttribute(request, ERROR_MSG, errStr);
		
		modelAndView.addAllObjects(controlModel);
	}
	
	
}
