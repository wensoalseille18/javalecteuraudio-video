/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjavafx;

/**
 *
 * @author Blemy
 */
public class ClassAtrib {

    public int id;
    public String nom, chemin;

    public ClassAtrib() {
    }

    public ClassAtrib(int id, String nom, String chemin) {
        this.id = id;
        this.nom = nom;
        this.chemin = chemin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

}
