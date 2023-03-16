package com.nof.chatbot.api.domain.zsxq.model.req;
/**
*请求问答接口信息
*@author Nof
*@date 2023-03-15 21:03:03
*/

public class AnswerReq {
    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
