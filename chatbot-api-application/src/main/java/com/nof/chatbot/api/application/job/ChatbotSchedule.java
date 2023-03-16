package com.nof.chatbot.api.application.job;

import com.alibaba.fastjson.JSON;
import com.nof.chatbot.api.domain.ai.IOpenAI;
import com.nof.chatbot.api.domain.zsxq.IZsxqApi;
import com.nof.chatbot.api.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import com.nof.chatbot.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);


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


    @Scheduled(cron = "0 0/1 * * * ? ")
    public void run() {
        try {
            //防风控
            if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }

            //夜晚不工作
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作！");
                return;
            }

            // 1. 检索问题
            UnansweredQuestionsAggregates unansweredQuestionsAggregates = zsxqApi.queryUnansweredQuestions(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unansweredQuestionsAggregates));
            List<Topics> topics = unansweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到问题");
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(openAiKey, topic.getTalk().getText().trim());
            // 3. 问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getTalk().getText(), answer, status);
        } catch (Exception e) {
            logger.error("自动回答问题异常", e);
        }
    }
}
