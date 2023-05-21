package kz.bitlab.treello.services.impl;

import kz.bitlab.treello.entities.Folder;
import kz.bitlab.treello.entities.Task;
import kz.bitlab.treello.repositories.TaskRepository;
import kz.bitlab.treello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findAllById(id);
    }

    @Override
    public Task updateTask(Task task) {
        Task currentTask = getTask(task.getId());
        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setStatus(task.getStatus());
        return taskRepository.save(currentTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTaskByFolder(List<Task> tasks, Folder folder) {
        List<Task> newTasks=new ArrayList<>();
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getFolder().getId()==folder.getId()){
                newTasks.add(tasks.get(i));
            }
        }
        return newTasks;
    }
}
