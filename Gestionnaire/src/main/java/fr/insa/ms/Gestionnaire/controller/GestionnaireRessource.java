package fr.insa.ms.Gestionnaire.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.Gestionnaire.model.*;


@RestController
public class GestionnaireRessource {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public int getTest() {
		return 42; 
	}
	
	@PostMapping("/addAvis")
	public Avis addAvis(@RequestBody Avis avis) {
		restTemplate.put("http://DemandManager/realiser/"+avis.getIdDemande(), null);
		return restTemplate.postForObject("http://AvisManager/add", avis, Avis.class);
	}
	
	@PostMapping("/addStudent/{password}")
	public Etudiant addStudent(@RequestBody Etudiant student, @PathVariable String password) {
		return restTemplate.postForObject("http://StudentManager/add/"+password, student, Etudiant.class);
	}
	
	@GetMapping("/getStudents")
	public List<Etudiant> getStudents() {
		List<Etudiant> students = restTemplate.exchange("http://StudentManager/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Etudiant>>() {}).getBody();
		return students;
	}
	
	@PutMapping("/acceptDemand/{id}")
	public void acceptDemand(@PathVariable int id) {
		restTemplate.put("http://DemandManager/accept/"+id, null);
	}
	
	@PutMapping("/abandonnerDemand/{id}")
	public void abandonnerDemand(@PathVariable int id) {
		restTemplate.put("http://DemandManager/abandonner/"+id, null);
	}
	
	@PostMapping("/addDemand")
	public List<Etudiant> addDemand(@RequestBody Demand newDemand){
		newDemand.setStatut("En attente");
		Demand demand = restTemplate.postForObject("http://DemandManager/add", newDemand, Demand.class);
		Etudiant demandeur = restTemplate.getForObject("http://StudentManager/"+demand.getEtudiant(), Etudiant.class);
		String contenu = demand.getTitre().toLowerCase() + ". " + demand.getDescription().toLowerCase();
		
		List<Etudiant> students = this.getStudents();
		HashMap<Integer, List<Etudiant>> map = new HashMap<>();
		
		for (Etudiant student : students) {
			// Filtre année (on suppose que filière est au format INSA : 5IR, 3MIC, ...)	
			if (demandeur.getFiliere().charAt(0) >= student.getFiliere().charAt(0)) {
				continue;
			}
			
			// Filtre dispos
			if (! student.getDispo().contains(demand.getDate())) {
				continue;
			}
			
			// Filtre compétences
			List<String> competencesList = Arrays.asList(student.getCompetences().split(";"));
			int scoreStudent = 0;
			
			for (String competence : competencesList) {
				if (contenu.contains(competence.toLowerCase())) {
					scoreStudent++;
				}
			}
			
			if (! map.containsKey(scoreStudent)) {
				map.put(scoreStudent, new ArrayList<Etudiant>());
			} 
			map.get(scoreStudent).add(student);
			
		}
		
		List<Etudiant> potentialTutors = new ArrayList<Etudiant>();
		
		// Ajoute au minimum 5 étudiants par ordre de compétence
		while((potentialTutors.size() < 5) && (! map.isEmpty())) {
			int maxScore = Collections.max(map.keySet());
			potentialTutors.addAll(map.get(maxScore));
			map.remove(maxScore);
		}
		
		return potentialTutors;
	}
	
}