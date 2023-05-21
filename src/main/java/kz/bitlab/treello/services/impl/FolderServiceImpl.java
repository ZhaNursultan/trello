package kz.bitlab.treello.services.impl;

import kz.bitlab.treello.entities.Folder;
import kz.bitlab.treello.entities.TaskCategories;
import kz.bitlab.treello.repositories.FolderRepository;
import kz.bitlab.treello.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepository folderRepository;
    @Override
    public Folder addFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public Folder getFolder(Long id) {
        return folderRepository.findAllById(id);
    }

    @Override
    public List<TaskCategories> nonUsedCategories(List<TaskCategories> taskCategoriesList, Folder folder) {
       List<TaskCategories>newCategories=new ArrayList<>();
        for (int i = 0; i < taskCategoriesList.size(); i++) {
            boolean similar = true;
            for (int j = 0; j < folder.getCategories().size(); j++) {
                if (taskCategoriesList.get(i).getId() == folder.getCategories().get(j).getId()) {
                    similar = false;
                    break;
                }
            }
            if (similar == true) {
                newCategories.add(taskCategoriesList.get(i));
            }
        }
        return newCategories;
    }
}
