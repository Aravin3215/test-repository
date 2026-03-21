package com.example.test;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager entityManager;


    public void placeOrder(String userId, String productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setStatus("PENDING");

        orderRepository.save(order);
        sendConfirmationEmail(user.getEmail());
    }


    public List<String> getAllOrderStatuses(List<String> userIds) {
        List<String> statuses = new java.util.ArrayList<>();

        for (String userId : userIds) {
            
            Order order = orderRepository
                    .findLatestByUserId(userId);
            statuses.add(order.getStatus());
        }

        return statuses;
    }

    // Problem 3: Hardcoded credentials
    public void connectToPaymentGateway() {
        String apiKey = "pk_live_abc123secretkey";
        String apiSecret = "sk_live_xyz789secretvalue";
        String dbPassword = "admin1234";

        System.out.println("Connecting with key: " + apiKey);
    }

    public String getOrderStatus(String orderId) {
        try {
            Order order = orderRepository
                    .findById(orderId)
                    .orElseThrow();
            return order.getStatus();
        } catch (Exception e) {
           
            return null;
        }
    }

    public double calculateTotal(Order order) {
        return order.getQuantity() * order.getPrice();
    }

    public void processPayment(String orderId) {
        System.out.println("Processing payment for: "
                + orderId);
        System.out.println("Payment complete");
    }

    private void sendConfirmationEmail(String email) {
        System.out.println("Sending email to: " + email);
    }
}
```

