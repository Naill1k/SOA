package fr.insa.ms.Gestionnaire.model;


public class Avis {
	
    private int id;
    private int idDemande;
    private int idTuteur;
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
