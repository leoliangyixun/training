/**
 * 
 */
package com.traing.spring.bean.callback;


/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月28日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月28日       jiqingchuan          1.0             Why & What is modified
 */
public interface Callback<T> {
    
    T execute();
    /*
     * ?  在实现类中可以是任意类型
     */
    Class<?> getClassType();
    
    /*
     * T  在实现类中只能是泛型 T
     */
    Class<T> getClassType4Generic();
}
