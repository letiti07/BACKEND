package com.stage.projet.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="typePaiement")
public class TypePaiement extends AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "TypePaiement")
    private String TypePaiement;

    @OneToMany(mappedBy = "typePaiement")
    private List<FactureFON> facturefons;
}
