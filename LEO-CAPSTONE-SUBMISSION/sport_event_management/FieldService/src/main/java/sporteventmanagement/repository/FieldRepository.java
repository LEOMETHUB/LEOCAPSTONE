package sporteventmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sporteventmanagement.entity.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {
}
