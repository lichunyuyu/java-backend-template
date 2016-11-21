
package com.fxmms.common.qo;

/**
 * 操作符枚�?
 * 
 * @author billy
 */
public enum Operator {
	/**
	 * 相等运算�?
	 */
	EQ("="),

	/**
	 * 不等运算�?
	 */
	NE("!="),

	/**
	 * 大于等于运算�?
	 */
	GE(">="),

	/**
	 * 大于运算�?
	 */
	GT(">"),

	/**
	 * 小于等于运算�?
	 */
	LE("<="),

	/**
	 * 小于运算�?
	 */
	LT("<"),

	/**
	 * BETWEEN运算�?
	 */
	BETWEEN("BETWEEN"),

	/**
	 * NOT BETWEEN运算�?
	 */
	NOT_BETWEEN("NOT BETWEEN"),

	/**
	 * LIKE运算�?
	 */
	LIKE("LIKE"),

	/**
	 * NOT LIKE运算�?
	 */
	NOT_LIKE("NOT LIKE"),

	/**
	 * IN运算�?
	 */
	IN("IN"),

	/**
	 * NOT IN运算�?
	 */
	NOT_IN("NOT IN"),

	/**
	 * IS NULL运算�?
	 */
	IS_NULL("IS NULL"),

	/**
	 * IS NOT NULL运算�?
	 */
	IS_NOT_NULL("IS NOT NULL");

	private String sign;

	private Operator(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}
}
