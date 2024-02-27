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
@Table(name = "arendator")
@AllArgsConstructor
@NoArgsConstructor
public class Arendator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Первичный ключ

    private String arendator_name;//Имя арендатора помещения
    private String arendator_adr;//Адрес арендатора помещения
    private String arendator_bank;//Банк арендатора
    private String arendator_telefon;//Телефон арендатора
    
    //Первичный ключ для Помещения 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arendator")
    private List<Space> spaces;
}
