package com.helk.customer;

import com.helk.amqp.RabbitMQMessageProducer;
import com.helk.clients.fraud.FraudCheckResponse;
import com.helk.clients.fraud.FraudClient;
import com.helk.clients.notification.NotificationClient;
import com.helk.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer producer) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        // Todo: check if email already exists
        // Todo: check if email is valid

        customerRepository.saveAndFlush(customer);

        // Checking if fraudster using the fraud service
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


        if (!Objects.isNull(fraudCheckResponse) && Boolean.TRUE.equals(fraudCheckResponse.isFraudster())) {
            throw new IllegalStateException("Customer is a fraudster");
        }

        // Sending notification // Todo: make it async
        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), "Welcome to Helk, " + customer.getFirstName());

        producer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");


    }
}
