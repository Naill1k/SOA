package fr.insa.ms.StudentManager;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.StudentManager.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
	
	

}
