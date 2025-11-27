package fr.insa.ms.DemandManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import fr.insa.ms.DemandManager.DemandRepository;
import fr.insa.ms.DemandManager.model.Demand;

@RestController
public class DemandRessource {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.hibernate.naming.physical-strategy}")
	private String physicalStrategy;

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
	
	@GetMapping("/{id}")
	public Demand getDemand(@PathVariable int id) {
		return repo.findById(id).orElse(null);
	}
	
	@PutMapping("/accept/{id}")
	public Demand acceptDemand(@PathVariable int id) {
		Demand demand = this.getDemand(id);
		demand.setStatut("En cours");
		return repo.save(demand);
	}
	
	@PutMapping("/abandonner/{id}")
	public Demand abandonnerDemand(@PathVariable int id) {
		Demand demand = this.getDemand(id);
		demand.setStatut("Abandonnée");
		return repo.save(demand);
	}
	
	@PutMapping("/realiser/{id}")
	public Demand realiserDemand(@PathVariable int id) {
		Demand demand = this.getDemand(id);
		demand.setStatut("Réalisée");
		return repo.save(demand);
	}
	
	
}