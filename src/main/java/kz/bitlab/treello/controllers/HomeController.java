package kz.bitlab.treello.controllers;

import kz.bitlab.treello.entities.Folder;
import kz.bitlab.treello.entities.Task;
import kz.bitlab.treello.entities.TaskCategories;
import kz.bitlab.treello.services.CategoryService;
import kz.bitlab.treello.services.FolderService;
import kz.bitlab.treello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/trello")
public class HomeController {

    @Autowired
    private FolderService folderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TaskService taskService;


    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<Folder> folders = folderService.getAllFolders();
        model.addAttribute("folders", folders);
        return "home";
    }

    @PostMapping(value = "/add-folder")
    public String addFolderPost(@RequestParam(name = "folder-name") String folderName) {
        String redirect = "/trello/add-folder?error";
        Folder folder = Folder.builder()
                .name(folderName)
                .build();
        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/home";
        }
        return "redirect:" + redirect;
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable("id") Long id, Model model) {
        Folder folder = folderService.getFolder(id);
        model.addAttribute("folder", folder);
        List<TaskCategories> taskCategoriesList = categoryService.getAllTaskCategories();
        List<TaskCategories> newCategories= folderService.nonUsedCategories(taskCategoriesList,folder);
        model.addAttribute("taskCategoriesList", newCategories);
        List<Task> tasks=taskService.getAllTasks();
        List<Task> newTasks=taskService.getTaskByFolder(tasks,folder);
        model.addAttribute("tasks",newTasks);
        return "details";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping(value = "/add-categories")
    public String openAddCategories(Model model) {
        List<TaskCategories> taskCategoriesList = categoryService.getAllTaskCategories();
        model.addAttribute("taskCategoriesList", taskCategoriesList);
        return "add-categories";
    }

    @PostMapping(value = "/add-categories")
    public String addCategoriesPost(@RequestParam(name = "category-name") String name) {
        String redirect = "/trello/add-categories?error";
        TaskCategories taskCategory = TaskCategories.builder()
                .name(name)
                .build();
        if (categoryService.addTaskCategory(taskCategory) != null) {
            redirect = "/trello/add-categories";
        }
        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete-category")
    public String deleteCategoryPost(@RequestParam(name = "category-id") Long id) {
        categoryService.deleteTaskCategory(id);
        return "redirect:add-categories";
    }

    @PostMapping(value = "/delete-category-from-folder-details")
    public String deleteTaskCategoryFolderPost(@RequestParam(name = "category-id") Long categoryId,
                                               @RequestParam(name = "folder-id") Long folderId) {

        String redirect = "/trello/delete-category-from-folder-details?error";
        Folder folder = folderService.getFolder(folderId);
        int index = -1;
        for (int i = 0; i < folder.getCategories().size(); i++) {
            if (folder.getCategories().get(i).getId() == categoryId) {
                index = i;
                break;
            }
        }
        folder.getCategories().remove(index);
        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/details/" + folderId;
        }
        return "redirect:" + redirect;
    }


    @PostMapping(value = "/assign-category")
    public String assignCategoryPost(@RequestParam(name = "category-id") Long categoryId,
                                     @RequestParam(name = "folder-id") Long folderId) {

        String redirect = "/trello/assign-category?error";
        Folder folder = folderService.getFolder(folderId);
        TaskCategories taskCategory = categoryService.getTaskCategory(categoryId);
        folder.getCategories().add(taskCategory);
        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/details/" + folder.getId();
        }
        return "redirect:" + redirect;
    }
    @PostMapping(value = "/add-task")
    public String addTaskPost(@RequestParam (name = "task-title")String title,
                              @RequestParam (name = "task-description")String description,
                              @RequestParam (name = "folder-id")Long folderId){

        Folder folder=folderService.getFolder(folderId);
        Task task= Task.builder()
                .title(title)
                .description(description)
                .status(0)
                .folder(folder)
                .build();
        String redirect="/trello/add-task?error";
        if(taskService.addTask(task)!=null){
            redirect="/trello/details/"+folderId;
        }
        return "redirect:"+redirect;
    }
    @PostMapping(value = "/update")
    public String updateTaskPost(@RequestParam (name = "task-title")String title,
                                 @RequestParam (name = "task-description")String description,
                                 @RequestParam (name = "task-status")int status,
                                 @RequestParam (name = "task-id") long id){
        String redirect="/trello/update?error";

        Task task= Task.builder()
                .title(title)
                .description(description)
                .status(status)
                .id(id)
                .build();
        if(taskService.updateTask(task)!=null){
            redirect="/trello/home";
        }
        return "redirect:"+redirect;

    }
    @PostMapping (value = "/delete")
    public String deleteTaskPost(@RequestParam(name = "task-id")Long id){
        taskService.deleteTask(id);
    return "redirect:/trello/home";
    }

    @GetMapping(value = "/login")
    public String openLogin(){
        return "login";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping (value = "/profile")
    public String openProfile(){
        return "profile";
    }


}
