package com.example.springai.t01client;

import com.example.springai.constant.AiConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author luminion
 * @since 1.0.0
 */
@SpringBootTest
@Slf4j
public class ChatClient13ChatTest {

    @Autowired
    @Qualifier(AiConst.BAIYUNSHAN_API)
    private OpenAiApi baiyunshanApi;
    @Autowired
    @Qualifier(AiConst.ALI_QWEN_PLUS)
    private OpenAiChatModel chatModel;

    @Test
    void createCustomChatModel(){
        // Simple prompt for both models
        String prompt = "请只回答你的底层模型提供商和模型名，不要扮演任何角色，不要介绍自己是助手品牌?";
//        String prompt = "请只回答你的底层模型提供商和模型名，不要扮演任何角色，不要介绍自己是助手品牌, 并回答以下问题:树上 10 只鸟，开枪打死 1 只，如果这只鸟是被静音狙击枪打死且被胶水粘在树枝上，旁边还有一只聋子鸟，请问树上还有几只鸟？";

        OpenAiChatModel minMax = chatModel
                .mutate() // 创建一个配置完全一样的新chatModel
                .openAiApi(baiyunshanApi)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model(AiConst.BAIYUNSHAN_URL_MINMAX) //指定模型
                        .temperature(0.5) // 随机度
                        .build()
                )
                .build();
        

        // 使用阿里百炼的模型
        String response = ChatClient.builder(minMax).build().prompt(prompt).call().content();
        log.info("response:\n{}\n", response);

    }

}
