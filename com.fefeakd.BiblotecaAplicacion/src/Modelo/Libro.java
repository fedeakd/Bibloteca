/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Libro {

    private int idLibro;
    private String nombre;
    private int idAutor;
    private int cantidad;
    private float precio;
    private String fechaPublicacion;
    private int idGenero;
    private int valoracion;
    private int idEditorial;
    private int idUsuario;

    private boolean estado;
    private String autor;
    private String genero;
    private String editorial;

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Libro() {

    }

    public Libro(int idLibro, String nombre, int cantidad, float precio, String fechaPublicacion, int valoracion, int idGenero, int idAutor, int idEditorial, int idUsuario) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.idAutor = idAutor;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
        this.idGenero = idGenero;
        this.valoracion = valoracion;
        this.idEditorial = idEditorial;
        this.idUsuario = idUsuario;
    }

    public static ArrayList<Libro> TraerTodosLosLibros(Conexion con) {
        ArrayList<Libro> libros = new ArrayList();
        try {
            ResultSet r = con.getCmd().executeQuery(String.format("SELECT l.*,g.nombre AS 'genero', e.nombre AS 'editorial', a.nombre AS 'autor' FROM libros AS l\n"
                    + "INNER JOIN generos AS g ON g.idGenero=l.idGenero\n"
                    + "INNER JOIN autores  AS a  ON a.idAutor= l.idAutor\n"
                    + "INNER JOIN editoriales AS e ON e.idEditorial= l.idEditorial"
            ));
            while (r.next()) {
                System.out.println("alho");
                Libro l = new Libro();

                l.setIdLibro(r.getInt("idLibro"));
                l.setNombre(r.getString("nombre"));
                l.setAutor(r.getString("autor"));
                l.setCantidad(r.getInt("cantidad"));
                l.setPrecio(r.getFloat("precio"));
                l.setFechaPublicacion(r.getString("fechaDEPublicacion"));
                l.setGenero(r.getString("genero"));
                l.setValoracion(r.getInt("valoracion"));
                l.setEditorial(r.getString("editorial"));
                libros.add(l);

            }
            return libros;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return libros;
    }

}
