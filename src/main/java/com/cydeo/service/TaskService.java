package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService extends CrudService<TaskDTO,Long>{
    List<TaskDTO> findTasksByManager(UserDTO manager);

    void updateProjectInTask(ProjectDTO project);

    List<TaskDTO> findAllTasksNotCompleted();

    void updateStatus(TaskDTO task);

    List<TaskDTO> findAllTasksByStatus(Status status);

}
