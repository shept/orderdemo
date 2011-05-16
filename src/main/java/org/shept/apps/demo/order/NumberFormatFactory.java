/**
 * 
 */
package org.shept.apps.demo.order;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.shept.org.springframework.beans.CustomEditorHolder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 * @author Andreas Hahn
 *
 */
public class NumberFormatFactory implements FactoryBean<CustomEditorHolder> {
	
	private String pattern = "#,##0.00" ;
	
	private Class<?> editClass = BigDecimal.class;
	
	private String locale = "us";
	
	public CustomEditorHolder getObject() throws Exception {
		
		Locale currencyLocale = new Locale(locale);
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(currencyLocale);
//		symbols.setInternationalCurrencySymbol("€");
		NumberFormat nf = new DecimalFormat(pattern, symbols);
		CustomNumberEditor editor = new CustomNumberEditor(editClass, nf, true);
		return new CustomEditorHolder(editor, editClass);
	}

	public Class<?> getObjectType() {
		return CustomEditorHolder.class;
	}

	public boolean isSingleton() {
		return false;
	}

	/**
	 * @return the editClass
	 */
	public Class<?> getEditClass() {
		return editClass;
	}

	/**
	 * @param editClass the editClass to set
	 */
	public void setEditClass(Class<?> editClass) {
		this.editClass = editClass;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}


}
