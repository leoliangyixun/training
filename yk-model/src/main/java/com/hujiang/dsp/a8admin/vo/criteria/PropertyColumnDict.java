/**
 * 
 */
package com.hujiang.dsp.a8admin.vo.criteria;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkai
 *
 */
public class PropertyColumnDict {
    private static final Map<String, PropertyColumnDict> PROPERTIES = new HashMap<String, PropertyColumnDict>();

    public static PropertyColumnDict CREATE_TIME = new PropertyColumnDict("createTime", "create_time");
    public static PropertyColumnDict MODIFY_TIME = new PropertyColumnDict("modifyTime", "modifyTime");
    public static PropertyColumnDict NAME = new PropertyColumnDict("name", "name");
    public static PropertyColumnDict STATUS = new PropertyColumnDict("status", "status");

    private String property;
    private String column;

    private PropertyColumnDict(String property, String column) {
        this.property = property;
        this.column = column;
        PROPERTIES.put(property, this);
    }

    public static PropertyColumnDict find(String property) {
        PropertyColumnDict dict = PROPERTIES.get(property);
        if (dict == null) return CREATE_TIME;
        return dict;
    }
    

    public String property(){
        return this.property;
    }

 
    public String column(){
        return this.column;
    }

    @Override
    public String toString() {
        return column();
    }

}
