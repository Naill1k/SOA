package fr.insa.ms.AvisManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import fr.insa.ms.AvisManager.AvisRepository;
import fr.insa.ms.AvisManager.model.Avis;

@RestController
@RequestMapping("/avis")
public class AvisRessource {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDdlAuto() {
		return ddlAuto;
	}


	public void setDdlAuto(String ddlAuto) {
		this.ddlAuto = ddlAuto;
	}


	public String getShowSql() {
		return showSql;
	}


	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}


	public String getDialect() {
		return dialect;
	}


	public void setDialect(String dialect) {
		this.dialect = dialect;
	}


	public String getPhysicalStrategy() {
		return physicalStrategy;
	}


	public void setPhysicalStrategy(String physicalStrategy) {
		this.physicalStrategy = physicalStrategy;
	}


	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.hibernate.naming.physical-strategy}")
	private String physicalStrategy;
	
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