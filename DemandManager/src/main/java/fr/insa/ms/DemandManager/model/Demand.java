package fr.insa.ms.DemandManager.model;

import jakarta.persistence.*;

@Entity
@Table(name="Demande")
public class Demand {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDemande")
    private int id;

    @Column(name = "etudiantDemande")
    private int etudiant;

    @Column(name = "titreDemande")
    private String titre;

    @Column(name = "descriptionDemande")
    private String description;

    @Column(name = "dateDemande")
    private String date;

    @Column(name = "statutDemande")
    private String statut;

	
	public Demand(int id, int etudiant, String titre, String description, String date, String statut) {
		this.id = id;
		this.etudiant = etudiant;
		this.titre = titre;
		this.description = description;
		this.date = date;
		this.statut = statut;
	}

	public Demand() {

	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(int etudiant) {
		this.etudiant = etudiant;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
