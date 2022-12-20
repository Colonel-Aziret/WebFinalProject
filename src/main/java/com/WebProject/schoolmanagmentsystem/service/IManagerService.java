package com.WebProject.schoolmanagmentsystem.service;

import com.WebProject.schoolmanagmentsystem.model.ManagerModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IManagerService {
    List<ManagerModel> getAllManager();

    ManagerModel saveManager(ManagerModel managerModel);

    ManagerModel getManagerById(Long id);

    ManagerModel updateManager(ManagerModel managerModelanager);

    void deleteManagerById(Long id);
}
