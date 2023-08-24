package com.cydeo.dto;
import com.cydeo.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private String projectName;
    private String projectCode;
    private UserDTO manager;
    private LocalDate startDAte;
    private LocalDate endDate;
    private String projectDetails;
    private Status projectStatus;

}
