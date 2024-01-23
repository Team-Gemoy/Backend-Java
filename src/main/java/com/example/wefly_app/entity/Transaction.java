package com.example.wefly_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Table(name = "transaction")
@Entity
@Where(clause = "deleted_date is null")
public class Transaction extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    @JsonManagedReference
    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionDetail> transactionDetails;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderer_id")
    private Orderer orderer;

    private int adultPassenger;
    private int childPassenger;
    private int infantPassenger;
    @Column(name = "total_price", precision = 15, scale = 2)
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    private String paymentProof;
}
