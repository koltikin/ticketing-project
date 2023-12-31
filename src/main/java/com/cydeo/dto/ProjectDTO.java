package com.cydeo.dto;
import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String projectName;
    private String projectCode;
    private UserDTO manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate,endDate;
    private String projectDetails;
    private Status projectStatus;

    private int completedTaskCount;
    private int unfinishedTaskCount;

    public ProjectDTO(String projectName, String projectCode, UserDTO manager, LocalDate startDate, LocalDate endDate, String projectDetails, Status projectStatus) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.manager = manager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetails = projectDetails;
        this.projectStatus = projectStatus;
    }
}
