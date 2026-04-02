package com.ingresos_soft.Universal.Models;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Usuarios")
public class UsuariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apPaterno;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    /*
     * @Column(length = 500)
     * private String avatar = "https://cdn.vuetifyjs.com/images/cards/road.jpg";
     */

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 30)
    private Long telefono;

    @Column(nullable = false)
    private Boolean status;

    public UsuariosModel(String nombre, String apPaterno, String email, String password,
                         LocalDate fechaNacimiento, Long telefono, Boolean status) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.status = status;
    }
}