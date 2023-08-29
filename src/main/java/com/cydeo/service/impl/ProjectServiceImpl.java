package com.cydeo.service.impl;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if (project.getProjectStatus()==null) {
            project.setProjectStatus(Status.OPEN);
        }
        return super.save(project.getProjectCode(),project);
    }
    @Override
    public ProjectDTO findById(String code) {
        return super.findById(code);
    }
    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }
    @Override
    public void deleteById(String code) {
        super.deleteById(code);
    }
    @Override
    public void update(ProjectDTO project) {
        Status status = findById(project.getProjectCode()).getProjectStatus();
        project.setProjectStatus(status);
        super.update(project,project.getProjectCode());

    }

    @Override
    public void completeStatus(String prjCode) {
        findById(prjCode).setProjectStatus(Status.COMPLETE);

    }

    @Override
    public List<ProjectDTO> getCountedProjectsList(UserDTO manager) {

        List<ProjectDTO> projectList = findAll().stream()
                .filter(prj->prj.getManager().equals(manager))
                .map(prj->{

                    List<TaskDTO> tasksList = taskService.findTasksByManager(manager);
                    int completedTaskCount = (int) tasksList.stream()
                            .filter(tk->tk.getProject().equals(prj) && tk.getTsakStatus() == Status.COMPLETE)
                            .count();

                    int unCompletedTaskCount = (int) tasksList.stream()
                            .filter(tk->tk.getProject().equals(prj))
                            .count() - completedTaskCount;

                    prj.setCompletedTaskCount(completedTaskCount);
                    prj.setUnfinishedTaskCount(unCompletedTaskCount);

                    return prj;
        })
                .collect(Collectors.toList());

        return projectList;
    }

}
