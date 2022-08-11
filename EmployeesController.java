package com.example.week2day2.controller;
import com.example.week2day2.ApiResponse;
import com.example.week2day2.Model.Employees;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/auth")
public class EmployeesController {

    private ArrayList<Employees> employeeList=new ArrayList<>();

    @GetMapping("/employee")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(employeeList);
    }
    @PostMapping("/register")
    public ResponseEntity addEmployee(@RequestBody @Valid Employees myemplye, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        employeeList.add(myemplye);
        return ResponseEntity.status(201).body( new ApiResponse("New user added !",201));
    }

    @PutMapping("/employee/{index}")
    public ResponseEntity updateUser(@RequestBody @Valid Employees myemplye
            ,@PathVariable Integer index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(index>=employeeList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        employeeList.set(index,myemplye);
        return ResponseEntity.status(201).body( new ApiResponse("User updated !",201));
    }
    @DeleteMapping("/users/{index}")
    public ResponseEntity deleteUser(@PathVariable int index){
        if(index>=employeeList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        employeeList.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted !",200));
    }
    // Employees apply for an annual leave and and turn
    // their onLeave status to true and reduce annualLeave
    // by 1(Check if employee is on leave return bad request,
    // if employee applies for leave and has 0 days return bad request)

 @PostMapping("/annual/{index}")
 public ResponseEntity AnnualLaeve(@PathVariable Integer index){
        Employees e = employeeList.get(index);
   if(e.getOnLeave().equals(false) && e.getAnnualLeave()>0){
    // employeeList.get(index).setAnnualLeave(employeeList.get(index).getAnnualLeave()-1);
     e.setAnnualLeave(e.getAnnualLeave()-1);
     e.setOnLeave(true);
       return ResponseEntity.status(200).body(new ApiResponse("done  !",200));
   }

     return ResponseEntity.status(400).body(new ApiResponse("bad request !",400));
 }



}
