package hello.rabbitMq.controller;

import hello.DTO.RequestDto;
import hello.DTO.ResponseDto;
import hello.rabbitMq.RabbitMqConfig;
import hello.rabbitMq.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/rabbit")
public class RabbitExampleController {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    @Autowired
    public RabbitExampleController(Receiver receiver,
                              RabbitTemplate rabbitTemplate
    ) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    //    curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"message": "johnny"}' "http://localhost:8080/rabbit/test_rabbitmq"
    @PostMapping("/test_rabbitmq")
    @ResponseBody
    public ResponseDto testRabbit(@RequestBody RequestDto testDTO)
            throws InterruptedException {
        rabbitTemplate
                .convertAndSend(
                        RabbitMqConfig.topicExchangeName,
                        "foo.bar.baz",
                        testDTO.getMessage());
        receiver.getLatch().await(2, TimeUnit.MILLISECONDS);

        return new ResponseDto("Thanks for the message");
    }
}
