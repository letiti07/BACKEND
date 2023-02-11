package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission extends AbstractEntity{

   @Column(name = "nom")
  private String nom;


    @ManyToMany(mappedBy="permissions")
    @JsonIgnore
    private Collection<Role> roles;

    public Collection<Role> getRoles() {
        return roles;
    }


}
