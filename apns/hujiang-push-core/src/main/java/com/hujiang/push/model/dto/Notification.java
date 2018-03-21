package com.hujiang.push.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Notification implements Serializable {

    @JSONField(name = "batch_id")
    private String batchId;

}
