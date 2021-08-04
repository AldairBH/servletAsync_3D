package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;

import java.io.File;

public class BeanGame {
    private int idGame;
    private String nameGame;
    private String img_game;
    private BeanCategory Category_idCategory;
    private String datePremiere;
    private String status;

    public BeanGame() {
    }

    public BeanGame(int idGame, String nameGame, String img_game, BeanCategory category_idCategory, String datePremiere, String status) {
        this.idGame = idGame;
        this.nameGame = nameGame;
        this.img_game = img_game;
        Category_idCategory = category_idCategory;
        this.datePremiere = datePremiere;
        this.status = status;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public String getImg_game() {
        return img_game;
    }

    public void setImg_game(String img_game) {
        this.img_game = img_game;
    }

    public BeanCategory getCategory_idCategory() {
        return Category_idCategory;
    }

    public void setCategory_idCategory(BeanCategory category_idCategory) {
        Category_idCategory = category_idCategory;
    }

    public String getDatePremiere() {
        return datePremiere;
    }

    public void setDatePremiere(String datePremiere) {
        this.datePremiere = datePremiere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
