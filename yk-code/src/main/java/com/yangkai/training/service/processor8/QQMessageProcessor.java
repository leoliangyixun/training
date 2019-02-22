/**
 * 
 */
package com.training.service.processor8;

import com.training.yk.model.processor.QQMessage;

/**
 * @author yangkai
 *
 */
public class QQMessageProcessor extends AbstractMessageProcessor<QQMessage> {

    public QQMessageProcessor() {

    }

    /*
     * @Override public <T extends Message> void process(T message) {
     * super.process(message); System.out.println("in qq message processor"); }
     */

    @Override
    public void process(QQMessage message) {
        super.process(message);
        System.out.println("in qq message processor");
    }

}
