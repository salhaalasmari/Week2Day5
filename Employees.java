package com.example.week2day2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employees {
    //ID , name , age , onLeave, employmentYear ,annualLeave
    @NotEmpty(message = "ID can't be null")
    @Size(min = 2,message = "ID  must be  more than 2")
    private  String ID;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 4,message = "name must be  more than 4")
    private  String name;

    @NotNull(message = "age can't be null")
    @Min(value = 25,message = "age must be  more than 25")
    //@Pattern(regexp = "^[1-9]+[0-9]*$",message = "please enter age as digits only")
    private Integer age;

   // @Pattern(regexp = "(false)",message = "On leave must be false only")]
    @AssertFalse
    private  Boolean onLeave;
    @NotEmpty(message = "employment Year can't be empty")
    //@PastOrPresent(message = "must be a valid year")

    // @Pattern(regexp = "^[1-9]+[0-9]*$",message = "please enter employment Year as digits only")
    @Range(min=2000 , max= 2023 , message="please enter employment Year as digits only")
    private  String employmentYear;
    @NotNull(message = "annual Leave Year can't be empty")
   // @Pattern(regexp = "^[1-9]+[0-9]*$",message = "please enter annual Leave  as it is correct")
    private  Integer annualLeave;
}
