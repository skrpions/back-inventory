package org.nmartinez.springcloud.backinventory.products.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;

import java.io.Serializable;

@Data // Reduce el código repetitivo como la implementación de getters y setters
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = -4310027227752446841L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CategoryEntity category;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", columnDefinition = "longblob")
    private byte[] picture;
}
