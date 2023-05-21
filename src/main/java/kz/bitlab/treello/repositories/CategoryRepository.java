package kz.bitlab.treello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.treello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<TaskCategories, Long> {
    TaskCategories findAllById(Long id);
}
