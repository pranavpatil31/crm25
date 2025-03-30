package com.crm.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//import java.util.Date;

@Getter
@Setter ////or else data is also fine
public class EmployeeDto {
    private Long id;
    @NotBlank
    @Size(min = 3,message="Atleast 3 chars required")//name must be more than 2 characters
    private String name;

    @Email
    private String emailId;

    @Size(min=10,max=10,message = "should be 10 digit")//min 10 and max 10 so less or more error
    private String mobile;
    ////private Date date;

}
