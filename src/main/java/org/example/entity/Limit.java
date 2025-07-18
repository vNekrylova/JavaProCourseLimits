package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "limits")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "daily_limit")
    private BigDecimal dailyLimit;

    @Override
    public String toString() {
       return "Limit [id=" + id + ", clientId=" + clientId + ", limit=" + dailyLimit + "]";
    }
}
