package fr.insa.ms.AvisManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import fr.insa.ms.AvisManager.AvisRepository;
import fr.insa.ms.AvisManager.model.Avis;

@RestController
@RequestMapping("/avis")
public class AvisRessource {
	
	private final AvisRepository repo;
	
	public AvisRessource(AvisRepository repo) {
        this.repo = repo;
    }
	
	
	@GetMapping("/")
	public int getTest() {
		return 42; 
	}
	
	
	@GetMapping("/all")
	public List<Avis> getAvis() {
		return repo.findAll();
	}
	
	
	@PostMapping("/add")
	public Avis addAvis(@RequestBody Avis avis) {
		return repo.save(avis);
	}
		
	
	@DeleteMapping("/{id}")
	public void deleteAvis(@PathVariable int id) {
		repo.deleteById(id);
	}
	
	
}