package fr.insa.ms.StudentManager.controller;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import fr.insa.ms.StudentManager.EtudiantRepository;
import fr.insa.ms.StudentManager.model.Etudiant;

@RestController
@RequestMapping("/students")
public class StudentRessource {
	
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
	

	private static final String PASSWORD_FILE = "passwords.txt";
    private static final HashMap<Integer, String> passwordDatabase = new HashMap<>();

    private final EtudiantRepository repo;

    public StudentRessource(EtudiantRepository repo) {
        this.repo = repo;
        loadPasswords();
    }


    private void loadPasswords() {
        try {
            File file = new File(PASSWORD_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            List<String> lines = Files.readAllLines(file.toPath());
            for (String l : lines) {
                if (l.contains(":")) {
                    String[] parts = l.split(":");
                    int id = Integer.parseInt(parts[0]);
                    String hash = parts[1];
                    passwordDatabase.put(id, hash);
                    System.out.println(id + ": " + hash);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void rewritePasswordFile() {
        try (FileWriter writer = new FileWriter(PASSWORD_FILE, false)) {
            for (Map.Entry<Integer, String> entry : passwordDatabase.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void savePassword(int id, String hashed) {
        passwordDatabase.put(id, hashed);
        rewritePasswordFile();
    }
    
    public boolean checkPasssword(int id, String password ) {
    	return ((passwordDatabase.containsKey(id)) && (BCrypt.checkpw(password, passwordDatabase.get(id))));
    }


    @PostMapping("/add/{password}")
    public Etudiant addStudent(@RequestBody Etudiant student, @PathVariable String password) {
        Etudiant newStudent = repo.save(student);

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        savePassword(newStudent.getId(), hashed);

        return newStudent;
    }


    @PutMapping("/{id}/{password}")
    public Etudiant updateStudent(@PathVariable int id, @RequestBody Etudiant newData, @PathVariable String password) {

        if (!checkPasssword(id, password))
            return null;

        Etudiant existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        if (newData.getFirstName() != null) existing.setFirstName(newData.getFirstName());
        if (newData.getLastName() != null) existing.setLastName(newData.getLastName());
        if (newData.getEmail() != null) existing.setEmail(newData.getEmail());
        if (newData.getFiliere() != null) existing.setFiliere(newData.getFiliere());
        if (newData.getEtablissement() != null) existing.setEtablissement(newData.getEtablissement());
        if (newData.getDispo() != null) existing.setDispo(newData.getDispo());
        if (newData.getCompetences() != null) existing.setCompetences(newData.getCompetences());

        return repo.save(existing);
    }


    @DeleteMapping("/{id}/{password}")
    public void deleteStudent(@PathVariable int id, @PathVariable String password) {
    	if (checkPasssword(id, password)) {
	        repo.deleteById(id);
	        
	        passwordDatabase.remove(id);
	        rewritePasswordFile();
    	}
    }


    @GetMapping("/all")
    public List<Etudiant> getStudents() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Etudiant getStudent(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }
    
    
    //Config Serveur
    @GetMapping("/url")
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
	
}
