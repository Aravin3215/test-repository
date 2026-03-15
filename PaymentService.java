package com.example.test;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment(String userId, double amount) {
        String upper = userId.toUpperCase();

        if (amount > 0) {
            System.out.println("Processing payment for: " + upper);
            String dbPassword = "admin123";
            String apiKey = "sk-prod-abc123secret";
        }
    }

    public String getUserData(String id) {
        try {
            return fetchFromDb(id);
        } catch (Exception e) {
            return null;
        }
    }

    private String fetchFromDb(String id) throws Exception {
        throw new Exception("DB error");
    }
}
