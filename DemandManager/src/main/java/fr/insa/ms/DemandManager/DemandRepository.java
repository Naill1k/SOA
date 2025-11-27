package fr.insa.ms.DemandManager;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.DemandManager.model.Demand;

public interface DemandRepository extends JpaRepository<Demand, Integer> {
	
	

}
