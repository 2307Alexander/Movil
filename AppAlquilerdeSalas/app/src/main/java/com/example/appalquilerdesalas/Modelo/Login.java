package com.example.appalquilerdesalas.Modelo;

public class Login {
    private Integer id;
    private String cedula;
    private String nombres;
    private String username;
    private String password;
    private String tipoUser;

    public Login(String cedula, String nombres, String username, String password, String tipoUser) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.username = username;
        this.password = password;
        this.tipoUser = tipoUser;
    }

    public Login() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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
    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

}