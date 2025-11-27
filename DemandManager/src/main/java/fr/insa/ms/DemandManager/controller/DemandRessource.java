package fr.insa.ms.DemandManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import fr.insa.ms.DemandManager.DemandRepository;
import fr.insa.ms.DemandManager.model.Demand;

@RestController
@RequestMapping("/demand")
public class DemandRessource {
	
	private final DemandRepository repo;
	
	public DemandRessource(DemandRepository repo) {
        this.repo = repo;
    }
	
	
	@GetMapping("/")
	public int getTest() {
		return 42; 
	}
	
	
	@GetMapping("/all")
	public List<Demand> getDemands() {
		return repo.findAll();
	}
	
	
	@PostMapping("/add")
	public Demand addDemand(@RequestBody Demand demand) {
		return repo.save(demand);
	}
		
	
	@DeleteMapping("/{id}")
	public void deleteDemand(@PathVariable int id) {
		repo.deleteById(id);
	}
	
	
}