/**
 * 
 */
package com.training.yk.model.processor;

import java.util.List;

/**
 * @author yangkai
 *
 */
public class Message {

    public String initiator;
    public Integer initiatorID;
    public List<Integer> receiverIDs;
    public List<String> receivers;
    public String token;
    public String topic;
    
	public Message() {
		
	}

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public Integer getInitiatorID() {
        return initiatorID;
    }

    public void setInitiatorID(Integer initiatorID) {
        this.initiatorID = initiatorID;
    }

    public List<Integer> getReceiverIDs() {
        return receiverIDs;
    }

    public void setReceiverIDs(List<Integer> receiverIDs) {
        this.receiverIDs = receiverIDs;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


	

}
