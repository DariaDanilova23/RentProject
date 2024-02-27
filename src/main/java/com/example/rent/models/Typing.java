package com.example.rent.models;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "typing")
@AllArgsConstructor
@NoArgsConstructor
public class Typing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Первичный ключ

    private String name;

    
    //Первичный ключ для Помещения 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typing")
    private List<Space> spaces;

    public Typing(String name) {
        this.name = name;
    }
}
