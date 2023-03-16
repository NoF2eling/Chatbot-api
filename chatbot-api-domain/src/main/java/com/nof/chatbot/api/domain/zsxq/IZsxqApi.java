package com.nof.chatbot.api.domain.zsxq;

import com.nof.chatbot.api.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {

    UnansweredQuestionsAggregates queryUnansweredQuestions(String groupId, String cookie) throws IOException;

    boolean answer(String groupID,String cookie,String topicId,String text) throws IOException;

}
