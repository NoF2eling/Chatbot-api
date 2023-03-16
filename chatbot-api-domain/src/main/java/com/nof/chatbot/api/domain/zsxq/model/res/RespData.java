package com.nof.chatbot.api.domain.zsxq.model.res;

import com.nof.chatbot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "RespData{" +
                "topics=" + topics +
                '}';
    }
}