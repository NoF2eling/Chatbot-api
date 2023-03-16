package com.nof.chatbot.api.domain.zsxq.model.aggregates;

import com.nof.chatbot.api.domain.zsxq.model.res.RespData;

/**
*未回答问题的聚合信息
*@author Nof
*@date 2023-03-15 21:03:12
*/

public class UnansweredQuestionsAggregates {
    private boolean successed;

    private RespData resp_data;

    public boolean isSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

    @Override
    public String toString() {
        return "UnansweredQuestionsAggregates{" +
                "successed=" + successed +
                ", resp_data=" + resp_data +
                '}';
    }
}
