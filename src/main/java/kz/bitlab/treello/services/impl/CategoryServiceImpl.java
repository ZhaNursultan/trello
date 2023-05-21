package kz.bitlab.treello.services.impl;

import kz.bitlab.treello.entities.TaskCategories;
import kz.bitlab.treello.repositories.CategoryRepository;
import kz.bitlab.treello.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public TaskCategories addTaskCategory(TaskCategories taskCategory) {
        return categoryRepository.save(taskCategory);
    }

    @Override
    public List<TaskCategories> getAllTaskCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteTaskCategory(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public TaskCategories getTaskCategory(Long id) {
        return categoryRepository.findAllById(id);
    }
}
