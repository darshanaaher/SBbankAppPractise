package com.fullstack.service;


import com.fullstack.model.Customer;
import com.fullstack.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OtpService otpService;


    public Customer signUp(Customer customer){
        return customerRepository.save(customer);
    }

    public boolean signIn(String custEmail, String custPass){
        Customer customer= customerRepository.findByCustEmailAndCustPass(custEmail, custPass);
        return customer !=null;
    }

    public Customer deposit(int custId, double amount){
        Customer customer= customerRepository.findByCustId(custId).orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custId));
        customer.setCustAccBalanace(customer.getCustAccBalanace() + amount);
        return customerRepository.save(customer);
    }

    public Customer withdraw(int custId, double amount){
        Customer customer=customerRepository.findByCustId(custId).orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custId));
        if(customer.getCustAccBalanace()<amount){
            throw new RuntimeException("Insufficient Fund");
        }
        customer.setCustAccBalanace(customer.getCustAccBalanace() - amount);
        return customerRepository.save(customer);
    }

    public Customer updateContactNumber(int custId, double custContactNumber){
        Customer customer= customerRepository.findByCustId(custId).orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custId));
        customer.setCustContactNumber(custContactNumber);
        return customerRepository.save(customer);
    }

    public Customer updateAddress(int custId, String custAddress){
        Customer customer=customerRepository.findByCustId(custId).orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custId));
        customer.setCustAddress(custAddress);
        return customerRepository.save(customer);
    }

    public Customer updateEmail(int custId, String custEmail){
        Customer customer= customerRepository.findByCustId(custId).orElseThrow(() -> new RuntimeException("Customer Account does not exists: " + custId));
        customer.setCustEmail(custEmail);
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(int custId){
        return customerRepository.findById(custId);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void deleteById(int custId){
         customerRepository.deleteById(custId);
    }

    public void deleteAll(){
        customerRepository.deleteAll();
    }

    public void logout(int custId){
        Customer customer=customerRepository.findByCustId(custId).orElseThrow(() -> new IllegalArgumentException("Customer Account does not exists:" + custId));
    }

    private int generatedOtp;

    public int generatedOtpForTransfer(int custId){
        Customer customer=customerRepository.findByCustId(custId).orElseThrow(() -> new IllegalArgumentException("Customer Account does not exists:" + custId));
        generatedOtp= otpService.otp();
        System.out.println("Generated OTP For account :"+custId + ":" + generatedOtp);
        return generatedOtp;
    }

    public String transferWithOtp(int custId, double amount, int enteredOtp){

        if(enteredOtp != generatedOtp){
            throw new IllegalArgumentException("Invalid OTP");
        }
        Customer customer=customerRepository.findByCustId(custId).orElseThrow(() -> new IllegalArgumentException("Customer Account does not exists:" + custId));

        if(customer.getCustAccBalanace() < amount){
            throw new IllegalArgumentException("Insufficient Fund");
        }
        customer.setCustAccBalanace(customer.getCustAccBalanace()- amount);
        customerRepository.save(customer);
        return "Transfer successful!";
    }
}
