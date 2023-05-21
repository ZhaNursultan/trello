package kz.bitlab.treello.services;

import kz.bitlab.treello.entities.Folder;
import kz.bitlab.treello.entities.Task;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);

    List<Task> getAllTasks();

    Task getTask(Long id);

    Task updateTask(Task task);

    void deleteTask(Long id);

    List<Task> getTaskByFolder(List<Task> tasks, Folder folder);
}
