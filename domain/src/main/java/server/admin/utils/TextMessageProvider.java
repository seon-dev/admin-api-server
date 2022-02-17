package server.admin.utils;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TextMessageProvider {
    private final DefaultMessageService messageService;

    @Value("${message.sender-number}")
    private String senderNumber;

    public TextMessageProvider(
            @Value("${message.api-key}")
                    String apiKey,
            @Value("${message.secret-key}")
                    String secretKey
    ) {
        this.messageService = NurigoApp.INSTANCE.initialize(
                apiKey,
                secretKey,
                "https://api.coolsms.co.kr"
        );
    }

    public SingleMessageSentResponse sendOne(
            String sendTo,
            String sendText
    ) {
        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(sendTo);
        message.setText(
                String.format("[PLAV] %s", sendText)
        );

        SingleMessageSentResponse response = this.messageService.sendOne(
                new SingleMessageSendingRequest(message)
        );
        log.info(String.valueOf(response));
        System.out.println(response);

        return response;
    }
}
