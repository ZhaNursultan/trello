package kz.bitlab.treello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.treello.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
