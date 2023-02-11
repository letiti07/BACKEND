package com.stage.projet.service;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.FactureFONDTO;
import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.LocationFONDTO;
import com.stage.projet.model.LocationFON;

import java.util.List;

public interface LocationFONService {
    LocationFONDTO create(LocationFONDTO locationFONDTO);

    List<LocationFONDTO> findAll();

    LocationFONDTO findById(Integer identifiant);

    void update(Integer identifiant, LocationFONDTO locationFONDTO);

    void deleteById(Integer id);


    List<LiaisonFONDTO> getLiaisons(Integer idLocation);

    double getFraisHebergementTotalHTVA(Integer idLocation);

    public double getFraisHebergementTotalWithTva(Integer IdLocation,Integer idFacture);

    public double getFraisHebergementTotal(Integer idLocation,Integer idFacture);

    void ValidateEnInstanceLocationFon(Integer id);

    void ValidateValidéLocationFon(Integer id);

    void ValidateNonValidéLocationFon(Integer id);

    double getPrixTotalMetreLineaireHTVA(Integer idLocation);

    public double getPrixTotalMetreLineaireWithTVA(Integer idLocation,Integer idFacture);

    public double getPrixTotalMetreLineaire(Integer idLocation,Integer idFacture);

    public List<FactureFONDTO> getFactureFons(Integer idLocation);

    public DemandeDTO DemandeByIdLocationfon(Integer idLocation);

    double getPrixTotalMetreLineaireLiaison(Integer idLocation, Integer idLiaison);
}
