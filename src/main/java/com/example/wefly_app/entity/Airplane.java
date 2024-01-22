package com.example.wefly_app.entity;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "airplane")
@Where(clause = "deleted_date is null")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

}