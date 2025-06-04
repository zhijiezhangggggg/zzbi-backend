package com.yupi.springbootinit.manager;

import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest;
import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionResult;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于对接 AI 平台
 */
@Service
public class AIManager {
    @Value("${doubao.client.api_key}")
    private String api_key;

    @Value("${doubao.client.bot_id}")
    private String bot_id;

    public String doChat(String message) {
        ArkService service = ArkService.builder().apiKey(api_key).build();

        System.out.println("\n----- standard request -----");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content(message).build();
        messages.add(userMessage);

        BotChatCompletionRequest chatCompletionRequest = BotChatCompletionRequest.builder()
                // 将<YOUR_BOT_ID>替换为您的应用ID
                .model(bot_id)
                .messages(messages)
                .build();

        BotChatCompletionResult chatCompletionResult =  service.createBotChatCompletion(chatCompletionRequest);
        final String[] cases = {""};
        chatCompletionResult.getChoices().forEach(
                choice -> cases[0] = choice.getMessage().getContent().toString()
        );

        return cases[0];
    }

}
