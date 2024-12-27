package com.fullstack.controller;


import com.fullstack.model.Customer;
import com.fullstack.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;


    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer){
        return ResponseEntity.ok( customerService.signUp(customer));
    }

    @PostMapping("/signin")
    public ResponseEntity<Boolean> signIn(@RequestParam String custEmail, @RequestParam String custPass){
        return ResponseEntity.ok(customerService.signIn(custEmail, custPass));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Customer> deposit(@RequestParam int custId, @RequestParam double amount){
        return ResponseEntity.ok(customerService.deposit(custId, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Customer> withdraw(@RequestParam int custId, @RequestParam double amount){
        return ResponseEntity.ok(customerService.withdraw(custId, amount));
    }

    @PutMapping("/updatecontact/{custId}/{custContactNumber}")
    public ResponseEntity<Customer> updateContactNumber(@PathVariable int custId, @RequestBody long custContactNumber){
        return ResponseEntity.ok(customerService.updateContactNumber(custId, custContactNumber));
    }

    @PutMapping("/updateaddress/{custId}/{custAddress}")
    public ResponseEntity<Customer> updateAddress(@PathVariable int custId, @RequestBody String  custAddress){
        return ResponseEntity.ok(customerService.updateAddress(custId, custAddress));
    }

    @PutMapping("/updateEmail/{custId}/{custEmail}")
    public ResponseEntity<Customer> updateEmail(@PathVariable int custId, @RequestBody String custEmail){
        return ResponseEntity.ok(customerService.updateEmail(custId, custEmail));
    }
    
    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable int custId){
        return ResponseEntity.ok(customerService.findById(custId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @DeleteMapping("/deletebyid/{custId}")
    public void deleteById(@PathVariable int custId){
        customerService.deleteById(custId);
         ResponseEntity.ok("Accounts deleted successfully");
    }

    @DeleteMapping("/deleteall")
    public void deleteAll(){
        customerService.deleteAll();
        ResponseEntity.ok("All accounts deleted successfully");
    }

    @PostMapping("/logout/{custId}")
    public ResponseEntity<String> logout(@PathVariable int custId){
        customerService.logout(custId);
        return ResponseEntity.ok("Logout Successfully");
    }

    @GetMapping("/generate-otp/{custId}")
    public ResponseEntity<String> generatOtp(@PathVariable int custId ){
        int otp = customerService.generatedOtpForTransfer(custId);
        return ResponseEntity.ok("Generated OTP: " + otp);
    }
    @PostMapping("/transfer/{custId}")
    public ResponseEntity<String> transfer(@PathVariable int custId, @RequestParam double amount , @RequestParam int otp){
        String customer= customerService.transferWithOtp(custId, amount, otp);
        return ResponseEntity.ok(customer);
    }
}
