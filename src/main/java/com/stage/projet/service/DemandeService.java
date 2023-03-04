package com.stage.projet.service;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.model.Demande;
import com.stage.projet.model.LocationFON;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface DemandeService {
    Integer create(DemandeDTO demandeDTO);

    List<DemandeDTO> findAll();

    DemandeDTO findById(Integer id);

    void update(Integer identifiant, DemandeDTO demandeDTO);

    void deleteById(Integer id);

    List<DemandeDTO> findAllByObjetAndEtat(String objet,String etat);

    ResponseEntity<byte[]> exportReport() throws FileNotFoundException, JRException;

     ResponseEntity<byte[]> exportReportFacturefon(Integer idLocation,Integer idFacture) throws FileNotFoundException, JRException;


}
