package com.alexgilleran.icesoap.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

/**
 * Indicates a field within an IceSoap POJO that is to be automatically set from
 * an XML object - accepts an absolute or relative XPath to map the field to an
 * XML text element. Note that this can take the form of an absolute XPath or a
 * relative XPath - relative XPaths are relative to the XPath defined in the
 * {@link XMLObject} annotation of the class the annotated field belongs to.
 * 
 * @author Alex Gilleran
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XMLField {
	/** The XPath of the field, as a String. */
	String value();

	/**
	 * The format to use for parsing this field, if it's a date - in the same
	 * format as is used for {@link SimpleDateFormat}. Defaults to ISO date
	 * (yyyy-MM-dd).
	 */
	String dateFormat() default ("yyyy-MM-dd");
}