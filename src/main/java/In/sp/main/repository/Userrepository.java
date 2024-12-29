package In.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import In.sp.main.entity.User;

@Repository
public interface Userrepository extends JpaRepository<User, Long>{

}
