package kz.bitlab.treello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.treello.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FolderRepository extends JpaRepository<Folder, Long> {

    Folder findAllById(Long id);
}
