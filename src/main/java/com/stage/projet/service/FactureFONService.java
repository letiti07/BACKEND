package com.stage.projet.service;

import com.stage.projet.dto.FactureFONDTO;
import com.stage.projet.model.FactureFON;

import java.util.List;

public interface FactureFONService {
    FactureFONDTO create(FactureFONDTO factureFONDTO);

    List<FactureFONDTO> findAll();

    FactureFONDTO findById(Integer id);

    void update(Integer identifiant, FactureFONDTO factureFONDTO);

    void deleteById(Integer id);

    List<FactureFONDTO> findFactureFonsOfLocation(Integer identifiant);

    public void validerEnInstance(FactureFONDTO factureFONDTO,Integer identifiant);

    public void validerLitigieux(FactureFONDTO factureFONDTO,Integer identifiant);

    public void validerAnnule(FactureFONDTO factureFONDTO,Integer identifiant);

    public void validerPaye(FactureFONDTO factureFONDTO,Integer identifiant);

    List<FactureFONDTO> findFacturesesOfLocation(Integer identifiant);
}
