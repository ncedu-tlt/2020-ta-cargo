package  com.netcracker.repository;

import com.netcracker.model.TypeCargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeCargo, Integer> {

}
