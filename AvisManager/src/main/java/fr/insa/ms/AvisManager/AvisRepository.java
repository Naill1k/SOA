package fr.insa.ms.AvisManager;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.AvisManager.model.Avis;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
	
	

}
