package fr.insa.ms.AvisManager.model;

import jakarta.persistence.*;

@Entity
@Table(name="Avis")
public class Avis {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAvis")
    private int id;

    @Column(name = "idDemande")
    private int idDemande;

    @Column(name = "idTuteur")
    private int idTuteur;

    @Column(name = "commentaires")
    private String commentaires;


	public Avis(int id, int idDemande, int idTuteur, String commentaires) {
		this.id = id;
		this.idDemande = idDemande;
		this.idTuteur = idTuteur;
		this.commentaires = commentaires;
	}

	public Avis() {

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdDemande() {
		return idDemande;
	}


	public void setIdDemande(int idDemande) {
		this.idDemande = idDemande;
	}


	public int getIdTuteur() {
		return idTuteur;
	}


	public void setIdTuteur(int idTuteur) {
		this.idTuteur = idTuteur;
	}


	public String getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	
}
