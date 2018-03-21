package com.pajk.frame.mybatis.plugin;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.JavaBeansUtil;

/**
 * 支持分页的mybatis自动生成工具插件
 * 
 * @author 丹青生
 *
 * @date 2015-9-2
 */
public class UpdateTimePlugin extends PluginAdapter {
	
	protected String columnName;
	protected String fieldName;
	
	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		processUpdateTime(element);
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		processUpdateTime(element);
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}
	
	protected void processUpdateTime(XmlElement element) {
		List<Element> list = element.getElements();
		for (int i = 0; i < list.size(); i++) {
			Element e = list.get(i);
			if(e instanceof TextElement){
				String text = ((TextElement) e).getContent();
				if(text.contains(fieldName)){ // insert values
					e = this.buildNewElement(list, i);
					element.getElements().remove(i);
					element.getElements().add(i, e);
					break;
				}else if(text.contains(columnName)){ // insert fields
					int begin = text.indexOf(columnName);
					if(text.charAt(begin - 2) == ','){
						text = text.replace(", " + columnName, "");
					}else {
						text = text.replace(columnName, "");
						String preContent = ((TextElement) list.get(i - 1)).getContent();
						preContent = preContent.substring(0, preContent.length() - 2);
						list.remove(i - 1);
						list.add(i - 1, new TextElement(preContent));
					}
					e = new TextElement(text);
					element.getElements().remove(i);
					element.getElements().add(i, e);
				}
			}else if(e instanceof XmlElement){
				XmlElement xmlElement = (XmlElement) e;
				if("if".equals(xmlElement.getName())){ // insertSelective
					for (Attribute attribute : xmlElement.getAttributes()) {
						if("test".equals(attribute.getName()) && attribute.getValue().contains(fieldName)){
							e = this.buildNewElement(xmlElement.getElements(), 0);
							element.getElements().remove(i);
							break;
						}
					}
				}else {
					processUpdateTime(xmlElement);
				}
			}
		}
	}
	
	protected Element buildNewElement(List<Element> list, int i) {
		TextElement element = (TextElement) list.get(i);
		String content = element.getContent();
		int index = content.indexOf(fieldName);
		if(index != -1){
			int begin = content.subSequence(0, index).toString().lastIndexOf("#{");
			int end = content.indexOf("}", index);
			if(begin >= 2 && content.charAt(begin - 2) == ','){
				begin -= 2;
			}else if(i > 0){
				String preContent = ((TextElement) list.get(i - 1)).getContent();
				preContent = preContent.substring(0, preContent.length() - 2);
				list.remove(i - 1);
				list.add(i - 1, new TextElement(preContent));
			}
			content = content.replace(content.subSequence(begin, end + 1), "");
			element = new TextElement(content);
		}
		return element;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		removeUpdateTimeField(element);
		return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		removeUpdateTimeField(element);
		return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		removeUpdateTimeField(element);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		removeUpdateTimeField(element);
		return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		removeUpdateTimeField(element);
		return super.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		removeUpdateTimeField(element);
		return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	protected void removeUpdateTimeField(XmlElement element) {
		List<Element> list = element.getElements();
		for (int i = 0; i < list.size(); i++) {
			Element e = list.get(i);
			if(e instanceof TextElement){
				String text = ((TextElement) e).getContent();
				if(text.contains(fieldName)){ // update
					element.getElements().remove(i);
					if(!text.endsWith(",")){
						text = ((TextElement) element.getElements().get(i-=1)).getContent();
						text = text.substring(0, text.length() - 1);
						element.getElements().remove(i);
						element.getElements().add(i, new TextElement(text));
					}
					break;
				}
			}else if(e instanceof XmlElement){
				XmlElement xmlElement = (XmlElement) e;
				if("if".equals(xmlElement.getName())){ // updateSelective
					for (Attribute attribute : xmlElement.getAttributes()) {
						if("test".equals(attribute.getName()) && attribute.getValue().contains(fieldName)){
							element.getElements().remove(i);
							break;
						}
					}
				}else {
					removeUpdateTimeField(xmlElement);
				}
			}
		}
	}

	@Override
	public boolean validate(List<String> warnings) {
		columnName = super.getProperties().getProperty("columnName");
		if(StringUtils.isEmpty(columnName)){
			columnName = "update_time";
		}
		fieldName = JavaBeansUtil.getCamelCaseString(columnName, false);
		return true;
	}
	
}


