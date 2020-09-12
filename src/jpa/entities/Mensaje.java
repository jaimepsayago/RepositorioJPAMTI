package jpa.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Mensaje {

    @Id
    @GeneratedValue
    @Column(name = "mensaje_id")
    private Long id;
    @Column(nullable = false)
    private String texto;
    private Date fecha;
    @ManyToOne
    private Autor autor;

    public Long getId() { return id; }
    private void setId(Long id) {this.id = id;}

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public Mensaje() {}

    public Mensaje(String texto, Autor autor) {
        this.texto = texto;
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mensaje mensaje = (Mensaje) o;

        if (!autor.equals(mensaje.autor)) return false;
        if (!texto.equals(mensaje.texto)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = texto.hashCode();
        result = 31 * result + autor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fecha=" + fecha +
                ", autor=" + autor +
                '}';
    }
}
