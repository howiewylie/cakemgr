package com.waracle.cakemgr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "Cake", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "TITLE")})
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Getter
    private Integer cakeId;

    @Getter
    @Setter
    @Column(name = "TITLE", unique = true, nullable = false, length = 100)
    private String title;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
    private String description;

    @Getter
    @Setter
    @Column(name = "IMAGE", unique = false, nullable = false, length = 300)
    private String image;

}
