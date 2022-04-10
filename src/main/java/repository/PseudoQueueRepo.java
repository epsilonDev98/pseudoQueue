package repository;

import model.PseudoQueueModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PseudoQueueRepo extends JpaRepository<PseudoQueueModel, Long>{
    List<PseudoQueueRepo> findByAccountNumber(String accountNumber);
//    List<PseduoQueueRepo> findByType(String type);


}
