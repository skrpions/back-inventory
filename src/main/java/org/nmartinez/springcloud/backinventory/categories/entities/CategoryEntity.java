package org.nmartinez.springcloud.backinventory.categories.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data // Reduce el código repetitivo como la implementación de getters y setters
@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = -4310027227752446841L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
