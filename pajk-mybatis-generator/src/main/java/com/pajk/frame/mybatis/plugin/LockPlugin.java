package com.pajk.frame.mybatis.plugin;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class LockPlugin extends PluginAdapter {

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement forUpdate = new XmlElement("if");
        forUpdate.addAttribute(new Attribute("test", "lock != null"));
        forUpdate.addElement(new TextElement("for update"));
        element.addElement(forUpdate);
        return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

}


