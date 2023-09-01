package com.waracle.cakemgr.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Getter
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
    private Long cakeId;

    @Setter
    @Column(name = "TITLE", unique = true, nullable = false, length = 100)
    private String title;

    @Setter
    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Setter
    @Column(name = "IMAGE", nullable = false, length = 300)
    private String image;

}
