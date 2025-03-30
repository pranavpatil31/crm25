package com.crm.service;


import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    ////this is constructor based dependency injection instead of autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = new   ModelMapper();

    }

   /* public Employee addEmployee(Employee employee) {
        Employee emp = employeeRepository.save(employee);
        return emp;*///// to find 202 405 201 etc
   public EmployeeDto addEmployee(EmployeeDto dto) {
       Employee employee = mapToEntity(dto);
       Employee emp = employeeRepository.save(employee);
       EmployeeDto employeeDto = mapToDto(emp);
       //employeeDto.setDate(new Date());
       return employeeDto;//entity is converted to dto
        /*public void addEmployee(Employee employee) {
        employeeRepository.save(employee);////this method is annotated with service annotaion*/
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);//created in control layer
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
       Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);//convert employee tp dto
          return employeeDto;

       /*Optional<Employee> opEmp = employeeRepository.findById(id);
        ////from control layer
        Employee employee = opEmp.get();
        employee.setName(dto.getName());
        employee.setEmailId(dto.getEmailId());
        employee.setMobile(dto.getMobile());*/

    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
       Sort sort = sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       Pageable page =  PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all=  employeeRepository.findAll(page);
       List<Employee> employees = all.getContent();
       List<EmployeeDto> dto = employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
       return dto;//to convert all entity to dto we have used stream api
        ////to find or get all data method from control layer
        //http://localhost:8080/api/v1/employee?pageNo=0&pageSize=5&sortBy=emailId&sortDir=asc
    }

    //duty of this is to convert map to a dto
    EmployeeDto mapToDto(Employee employee) {//job of this method is to copy entire emp object content to dto
      EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;
      //reduces below lne of codes the above code
       // EmployeeDto dto = new EmployeeDto();
        /*dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmailId(employee.getEmailId());
        dto.setMobile(employee.getMobile());*/

        //whenever we call this method and supply entity object toit that will be converted to dto and will returm dto

    }
    Employee mapToEntity(EmployeeDto dto) {//copying dto content to entity
      Employee emp = modelMapper.map(dto, Employee.class);
        return emp;

       /*  Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setName(dto.getName());
        emp.setEmailId(dto.getEmailId());
        emp.setMobile(dto.getMobile());*/
       //supplying dto and converts to entity
    }

    public EmployeeDto getEmployeeById(long empId) {
      Employee employee= employeeRepository.findById(empId).orElseThrow(
              ()->new ResourceNotFound("Record not found with id"+empId)
      );
      EmployeeDto dto = mapToDto(employee);
       return dto;
   }
}
