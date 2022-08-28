package com.portfolio.bav.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPersona {

    @NotBlank
    private String nombreP;
    @NotBlank
    private String apellidoP;
    @NotBlank
    private String imgP;
    @NotBlank
    private String imgPortada;
    @NotBlank
    private String tituloP;
    @NotBlank
    private String descP;
    
    
    //Constructores

    public dtoPersona(String nombreP, String apellidoP, String imgP, String imgPortada, String tituloP, String descP) {
        this.nombreP = nombreP;
        this.apellidoP = apellidoP;
        this.imgP = imgP;
        this.imgPortada = imgPortada;
        this.tituloP = tituloP;
        this.descP = descP;
    }

    public dtoPersona() {
    }
    
    // GETTERS & SETTERS
    
    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    public String getImgPortada() {
        return imgPortada;
    }

    public void setImgPortada(String imgPortada) {
        this.imgPortada = imgPortada;
    }

    public String getTituloP() {
        return tituloP;
    }

    public void setTituloP(String tituloP) {
        this.tituloP = tituloP;
    }

    public String getDescP() {
        return descP;
    }

    public void setDescP(String descP) {
        this.descP = descP;
    }
    

}