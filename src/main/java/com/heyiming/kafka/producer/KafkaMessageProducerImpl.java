package com.heyiming.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.producer.Producer;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
@Slf4j
public class KafkaMessageProducerImpl implements KafkaMessageProducer {

	private Producer stringProducer = null;

	@Value("#{environment['kafka.topic']}")
	private String TOPIC;

	@Value("#{environment['kafka.bootstrap.servers']}")
	private String BOOT_STRAP_SERVERS;

	@PostConstruct
	public void init() {
		Properties kafkaProperties = new Properties();
		kafkaProperties.put("bootstrap.servers", BOOT_STRAP_SERVERS);
		kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		stringProducer = new KafkaProducer<String, String>(kafkaProperties);
	}

	@Override
	public void sendStringMessage(String message) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, message);
		try {
			stringProducer.send(record).get();
			log.info("send success");
		} catch (Exception e) {
			log.error("kafka send string message error. {}", e.getMessage());
		}
	}
}
