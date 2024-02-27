package com.example.rent.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "space")
@AllArgsConstructor
@NoArgsConstructor
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Typing typing;//Тип помещения	     Внешний ключ в таблице типов	
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insert_id", nullable = true)
    private Space insert_id;  // Ссылка на родительское пространство
    
    private float base_X;	//Базовая координата  X		
    private float base_Y;	// Базовая координата   Y		
    private float base_Z;	// Базовая координата    Z		
    private float size_X;	//Размер по  X		
    private float size_Y;	//Размер по  Y		
    private float size_Z;	//Размер по  Z	
    @ManyToOne
    @JoinColumn(name = "arendator_id")
    private Arendator arendator; //                 Внешний ключ арендатора области	
    private float price;	//Цена аренды		
    private String comment;	//Комментарий	
}
