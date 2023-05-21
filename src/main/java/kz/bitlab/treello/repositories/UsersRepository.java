package kz.bitlab.treello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.treello.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
}
