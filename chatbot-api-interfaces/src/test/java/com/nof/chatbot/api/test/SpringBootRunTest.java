package com.nof.chatbot.api.test;


import com.alibaba.fastjson.JSON;
import com.nof.chatbot.api.domain.ai.IOpenAI;
import com.nof.chatbot.api.domain.ai.service.OpenAI;
import com.nof.chatbot.api.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import com.nof.chatbot.api.domain.zsxq.model.vo.Topics;
import com.nof.chatbot.api.domain.zsxq.IZsxqApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    Logger logger= LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${chatbot-api.openAIKey}")
    private String openAiKey;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void testZsxqApi() throws IOException {
        UnansweredQuestionsAggregates unansweredQuestionsAggregates = zsxqApi.queryUnansweredQuestions(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unansweredQuestionsAggregates));
        List<Topics> topics = unansweredQuestionsAggregates.getResp_data().getTopics();
        Topics topic = topics.get(0);
        String topic_id = topic.getTopic_id();
        String text = topic.getTalk().getText();
        logger.info("topic_id:{} text:{}",topic_id,text);
        //回答问题
        zsxqApi.answer(groupId,cookie,topic_id,text);
    }

    @Test
    public void testOpenAI() throws IOException{
        String response = openAI.doChatGPT(openAiKey, "帮我写一个java冒泡排序");
        logger.info("测试结果：{}", response);
    }


}
