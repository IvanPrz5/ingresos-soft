package com.ingresos_soft.Universal.Models;

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
@Table(name = "DataSource")
public class DataSourceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String razonSocial;

    @Column(length = 25, nullable = false)
    private String rfc;

    @Column(length = 250, nullable = false)
    private String nameDatabase;

    @Column(nullable = false)
    private Boolean status;

    public DataSourceModel(String razonSocial, String rfc, String nameDatabase, Boolean status) {
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.nameDatabase = nameDatabase;
        this.status = status;
    }
}