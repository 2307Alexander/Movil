package com.example.appalquilerdesalas.Modelo;

public class Sala {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private byte[] imagen;
    private String tiempo;
    private String estado;



    public Sala(Integer id, String nombre, String descripcion,Integer precio, byte[] imagen, String tiempo, String estado) {
        this.id = id;
        this.nombre =  nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.tiempo = tiempo;
        this.estado = estado;
    }

    public Sala() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre;}
    public String getDescripcion() { return descripcion;}
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}
    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public byte[] getImagen() {
        return imagen;
    }
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public String getTiempo() { return tiempo;}
    public void setTiempo(String tiempo) { this.tiempo = tiempo;}
    public String getEstado() { return estado;}

    public void setEstado(String estado) { this.estado = estado;}


}
