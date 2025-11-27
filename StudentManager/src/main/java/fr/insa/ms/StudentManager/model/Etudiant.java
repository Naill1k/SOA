package fr.insa.ms.StudentManager.model;

import jakarta.persistence.*;

@Entity
@Table(name="Etudiant")
public class Etudiant {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtudiant")
    private int id;

    @Column(name = "firstNameEtudiant")
    private String firstName;

    @Column(name = "lastNameEtudiant")
    private String lastName;

    @Column(name = "emailEtudiant", unique = true)
    private String email;

    @Column(name = "filiereEtudiant")
    private String filiere;

    @Column(name = "etablissementEtudiant")
    private String etablissement;

    @Column(name = "dispoEtudiant", columnDefinition = "varchar(200)")
    private String dispo;

    @Column(name = "competencesEtudiant", columnDefinition = "varchar(200)")
    private String competences;

	
	public Etudiant(int id, String lastName, String firstName, String email, String etablissement, String filiere,
			String competences, String dispo) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.etablissement = etablissement;
		this.filiere = filiere;
		this.competences = competences;
		this.dispo = dispo;
	}
	
	public Etudiant() {

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getCompetences() {
		return competences;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	public String getDispo() {
		return dispo;
	}

	public void setDispo(String dispo) {
		this.dispo = dispo;
	}

	
}
