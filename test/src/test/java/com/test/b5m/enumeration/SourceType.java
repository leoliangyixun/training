/**
 * 
 */
package com.test.b5m.enumeration;

/**
 * @description: Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年2月29日 Modification History: Date Author Version Discription
 *              ----------------------------------------------------------------
 *              ------------------- 2016年2月29日 jiqingchuan 1.0 Why & What is
 *              modified
 */
public enum SourceType {
	GRAB(0, "淘宝/天猫等通过HTTP接口生成返利订单"),
	CPS(1, "第三方联盟CPS订单");
	private int value;
	private String descr;

	private SourceType(int value, String descr) {
		this.value = value;
		this.descr = descr;
	}

	public static SourceType parse(int value) {
		for (SourceType type : values()) {
			if (type.value == value) {
				return type;
			}
		}
		return null;
	}

	public int value() {
		return value;
	}

	public String descr() {
		return descr;
	}
}
