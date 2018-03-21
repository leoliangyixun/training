/**
 * 
 */
package com.hujiang.notifycenter.wechat;

/**
 * @author yangkai
 *
 */
public interface Command <T> {

    T execute();
    
  }