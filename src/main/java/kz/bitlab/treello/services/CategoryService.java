package kz.bitlab.treello.services;

import kz.bitlab.treello.entities.TaskCategories;

import java.util.List;

public interface CategoryService {
    TaskCategories addTaskCategory(TaskCategories taskCategory);

    List<TaskCategories> getAllTaskCategories();

    void deleteTaskCategory(Long id);

    TaskCategories getTaskCategory(Long id);
}
