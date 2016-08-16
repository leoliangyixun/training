package com.pajk.frame.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 支持分页的mybatis自动生成工具插件
 * 
 * @author 丹青生
 *
 * @date 2015-9-2
 */
public class PaginationPlugin extends PluginAdapter {
	
	private String typeName;
	
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if(super.getProperties() == null){
			System.out.println("请在plugin节点增加子节点以指定有效的分页对象全路径名,如:<property name=\"pagination\" value=\"com.b5m.frame.entity.Pagination\"/>");
			throw new RuntimeException("请指定分页对象全路径名");
		}
		String pagination = super.getProperties().getProperty("pagination");
		if(pagination == null || pagination.trim().length() == 0){
			throw new RuntimeException("请指定有效的分页对象全路径名");
		}
		System.out.println("分页对象:" + pagination);
		addField(topLevelClass, introspectedTable, new FullyQualifiedJavaType(pagination));
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
	
	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", typeName + " != null"));
		isNotNullElement.addElement(new TextElement("limit ${" + typeName +  ".getPosition()} , ${" + typeName + ".getPageSize()}"));
		element.addElement(isNotNullElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}
	
	private void addField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, FullyQualifiedJavaType type) {
		typeName = type.getShortName();
		String firstChar = "" + typeName.charAt(0);
		typeName = typeName.replaceFirst(firstChar, firstChar.toLowerCase());
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(type);
		field.setName(typeName);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = typeName.charAt(0);
		String camel = Character.toUpperCase(c) + typeName.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(type, typeName));
		method.addBodyLine("this." + typeName + "=" + typeName + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(type);
		method.setName("get" + camel);
		method.addBodyLine("return " + typeName + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		topLevelClass.addImportedType(type);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

}


