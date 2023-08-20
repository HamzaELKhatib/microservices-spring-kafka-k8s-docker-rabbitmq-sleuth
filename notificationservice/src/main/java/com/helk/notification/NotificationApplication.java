package com.helk.notification;

import com.helk.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.helk.notification",
                "com.helk.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.helk.clients")
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    // Testing RabbitMQ dashboard
    /*
    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig) {
        return args -> producer.publish(new Person("Hassaaan", 18), notificationConfig.getInternalExchange(), notificationConfig.getInternalNotificationRoutingKey());
    }

    record Person(String name, int age) {}
    */
}
