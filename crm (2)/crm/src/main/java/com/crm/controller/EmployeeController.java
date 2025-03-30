package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController////we are using it through api so use rest not html or any other ui then use controller
////it acts mediator between front end and backend services data comes in form of json object
@RequestMapping("/api/v1/employee")
////when we send this url call this class
public class EmployeeController {
private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }




   ////obj selected in postman is post so postmapping http://localhost:8080/api/v1/employee/add half url emp and add
            ////json content via this url will  come to this method object employee
            //var names in json obj must match these entity class object
         //  @RequestBody Employee employee
         @PostMapping("/add")
         public ResponseEntity <?> addEmployee(//? return value multiple is taken
                 @Valid @RequestBody EmployeeDto dto, //data from postman to controler withouut json copying to dto
                BindingResult result //automatically captures the errors springboot takes care


                 // data in this dto is validated by valid annotation and the will goto object

         ){
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

            EmployeeDto employeeDto = employeeService.addEmployee(dto);
             return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
        /*Employee emp = employeeService.addEmployee(dto);/////201 405 error finding process
             return new ResponseEntity<>(emp, HttpStatus.CREATED);*/


       /* Employee emp = employeeService.addEmployee(employee);/////201 405 error finding process
    return new ResponseEntity<>(emp, HttpStatus.CREATED);*/

       /*System.out.println(employee.getName());
       System.out.println(employee.getEmailId());
       System.out.println(employee.getMobile());
       ////#run in crm application first then here after this send to postman*/


    }
    //http://localhost:8080/api/v1/employee?id=1 from url comes to emp class and then deletes by id
    //it is query parameter because ?  is there read query parameter by @req param
    @DeleteMapping
     public ResponseEntity <String> deleteEmployee        //public String deleteEmployee
    (
        @RequestParam Long id
        ////fetches id and puts into it id and url var must be same
    ){
        employeeService.deleteEmployee(id); ////creates new emp method in service layer
          return new ResponseEntity<>("deleted",HttpStatus.OK);   //ok means it will return status code 200
          // return "deleted";//method in service layer

}
    @PutMapping
    public ResponseEntity <EmployeeDto> updateEmployee(
    //public String UpdateEmployee(
            @RequestParam Long id,
            //// fetches id and puts into it id and url var must be same
            @RequestBody EmployeeDto dto
    ){
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
         return new ResponseEntity<>(employeeDto,HttpStatus.OK);
         //  return "updated";    //// method in service layer

    }

    //http://localhost:8080/api/v1/employee?pageSize=5&pageNo=1
    @GetMapping
     public ResponseEntity<List<EmployeeDto>>getEmployees(     ////public List<Employee> getEmployee
     @RequestParam(name="pageSize",required = false,defaultValue = "5") int pageSize,
     // per page maximum 5 records
     @RequestParam(name="pageNo",required = false,defaultValue = "0")  int pageNo,
     @RequestParam(name="sortBy",required = false,defaultValue="name") String sortBy,
     @RequestParam(name="sortDir",required = false,defaultValue="asc") String sortDir
            //// to fetch all data

    ){
       List<EmployeeDto> employeesDto= employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
       return new ResponseEntity<>(employeesDto,HttpStatus.OK);////displays all records
       //return employees;    //// method in service layer

    }
     @GetMapping("/employeeId/{empId}") //http://localhost:8080/api/v1/employee/employeeId/1
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable long empId //it will fetch id no. from url and saves in it
     ){
        //create method in service layer
        EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
        //whenever we are reading data from backend it must be 200
     }

    }


