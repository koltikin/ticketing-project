package com.cydeo.service.impl;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO task) {

//        if(task.getId()==null){
//            task.setId(myAtomicLong.incrementAndGet());
//        }

        if(task.getId()==null){
            task.setId(UUID.randomUUID().getMostSignificantBits());
        }

        if(task.getAssignedDate()==null){
            LocalDate assignDate = LocalDate.now();
            task.setAssignedDate(assignDate);
        }
        if(task.getTsakStatus()==null){
            task.setTsakStatus(Status.OPEN);
        }
        return super.save(task.getId(),task);
    }
    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }
    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    public void update(TaskDTO task) {

        if(task.getAssignedDate()==null){
            LocalDate assignDate = LocalDate.now();
            task.setAssignedDate(assignDate);
        }
        if(task.getTsakStatus()==null){
            task.setTsakStatus(Status.OPEN);
        }
        super.update(task,task.getId());

    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return findAll().stream()
                .filter(task->task.getProject().getManager().equals(manager))
                .collect(Collectors.toList());
    }
}
