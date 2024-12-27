package com.fullstack.service;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
    public int otp() {
        // Generate and return an OTP (example logic)
        return (int) (Math.random() * 9000) + 1000; // Generates a 4-digit OTP
    }
}
