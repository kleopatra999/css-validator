// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2014/WD-filter-effects-1-20141125/#LightingColorProperty
 */
public class CssLightingColor extends org.w3c.css.properties.css.CssLightingColor {

	/**
	 * Create a new CssLightingColor
	 */
	public CssLightingColor() {
		value = initial;
	}

	/**
	 * Creates a new CssLightingColor
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssLightingColor(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();
		CssValue val = expression.getValue();

		if (inherit.equals(val)) {
			value = inherit;
			expression.next();
		} else {
			try {
				CssColor tcolor = new CssColor(ac, expression, check);
				// instead of using getColor, we get the value directly
				// as we can have idents
				value = tcolor.color;
			} catch (InvalidParamException e) {
				throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
			}
		}
	}

	public CssLightingColor(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

