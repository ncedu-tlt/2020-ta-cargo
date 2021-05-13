package  com.netcracker.repository;

import com.netcracker.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select distinct NEW com.netcracker.model.Address(a.city)  from Address a" )
    List<Address> fainAllCityDistinct();

}
