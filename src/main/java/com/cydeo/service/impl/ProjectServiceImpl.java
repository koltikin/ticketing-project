package com.cydeo.service.impl;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
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
        super.update(project,project.getProjectCode());

    }
    public void updateStatus(ProjectDTO project, String prjCode){
        Status status = findById(prjCode).getProjectStatus();
        project.setProjectStatus(status);

    }
}
