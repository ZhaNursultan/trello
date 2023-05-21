package kz.bitlab.treello.services;

import kz.bitlab.treello.entities.Folder;
import kz.bitlab.treello.entities.TaskCategories;

import java.util.List;


public interface FolderService {
    Folder addFolder(Folder folder);

    List<Folder> getAllFolders();

    Folder getFolder(Long id);

    List<TaskCategories> nonUsedCategories(List<TaskCategories> taskCategoriesList,
                                           Folder folder);
}
