package com.heyiming.kafka.controller;

import com.heyiming.kafka.controller.vo.SendMessageVO;
import com.heyiming.kafka.producer.KafkaMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/message")
public class SendMessageController {

	@Autowired
	private KafkaMessageProducer kafkaMessageProducer;

	@RequestMapping(path = "/string/send", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void sendStringMessage(@RequestBody SendMessageVO sendMessageVO){
		log.info("get message send request. request message: {}", sendMessageVO.getMessage());
		kafkaMessageProducer.sendStringMessage(sendMessageVO.getMessage());
	}
}
