package com.stage.projet.service.impl;

import com.stage.projet.dto.*;
import com.stage.projet.model.Demande;
import com.stage.projet.model.Demandeur;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.LocationFON;
import com.stage.projet.repository.DemandeRepository;
import com.stage.projet.repository.DemandeurRepository;
import com.stage.projet.repository.LocationFONRepository;
import com.stage.projet.service.DemandeService;
import com.stage.projet.service.FactureFONService;
import com.stage.projet.service.LocationFONService;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
@Transactional
@Slf4j
public class DemandeServiceImpl implements DemandeService {

    private DemandeRepository demandeRepository;
    private LocationFONRepository locationFONRepository;

    private DemandeService demandeService;

    private FactureFONService factureFONService;

    private LocationFONService locationFONService;

    public DemandeServiceImpl(DemandeRepository demandeRepository, LocationFONRepository locationFONRepository,
                              LocationFONService locationFONService,FactureFONService factureFONService) {
        this.demandeRepository = demandeRepository;
        this.locationFONRepository = locationFONRepository;
        this.locationFONService=locationFONService;
        this.factureFONService=factureFONService;
    }

    @Override
    public Integer create(DemandeDTO demandeDTO) {
        return demandeRepository.save(DemandeDTO.toEntity(demandeDTO)).getId();
    }

    @Override
    public List<DemandeDTO> findAll() {
        List<DemandeDTO> demandeDTOS = this.demandeRepository.findAll().stream().map(DemandeDTO::toDTO).toList();
        //pour injecter la location avec ses liaisons et points de connection à l'interieur de chaque demande
        demandeDTOS.forEach(element->{
            if(element.getLocationFONDTO()!=null){
                element.setLocationFONDTO(this.locationFONService.findById(element.getLocationFONDTO().getId()));
            }

        });
        return demandeDTOS;
    }

    @Override
    public DemandeDTO findById(Integer id) {
        if (demandeRepository.findById(id).isPresent()) {
            log.info(String.valueOf(1));
            DemandeDTO demandeDTO = DemandeDTO.toDTO(demandeRepository.findById(id).get());
            //si la demande a une location
            if (demandeDTO.getLocationFONDTO() != null) {
                log.info(String.valueOf(2));
                LocationFONDTO locationFONDTO = demandeDTO.getLocationFONDTO();
                //si cette location existe bel et bien dans la BDD
                LocationFONDTO byId = this.locationFONService.findById(locationFONDTO.getId());
                if (byId != null) {
                    log.info(String.valueOf(3));
                    demandeDTO.setLocationFONDTO(byId);
                }
            }
            return demandeDTO;
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, DemandeDTO demandeDTO) {
        demandeDTO.setId(identifiant);
        demandeRepository.save(DemandeDTO.toEntity(demandeDTO));
    }

    @Override
    public void deleteById(Integer identifiant) {
        this.demandeRepository.deleteById(identifiant);
    }


    @Override
    //demande accepté
    public List<DemandeDTO> findAllByObjetAndEtat(String objet, String etat) {
        List<DemandeDTO> liste = new ArrayList<>();
        List<DemandeDTO> demandeDTOS = demandeRepository.findAllByObjetAndEtat(objet, etat).stream().map(DemandeDTO::toDTO).toList();
        List<DemandeDTO> listefinal=new ArrayList<>();
        demandeDTOS.forEach(element->{
            log.info(String.valueOf(element));

            if( element.getLocationFONDTO()!=null &&
                    (element.getLocationFONDTO().getEtat()==null || element.getLocationFONDTO().getEtat().equals("non-validé")) ||
            element.getLocationFONDTO()==null){

                listefinal.add(element);
            }
        });

        return listefinal;

    }



    @Override
    public ResponseEntity<byte[]> exportReport() throws FileNotFoundException,JRException {
        try {
            String path = "C:\\Users\\Fiacre\\Downloads\\uploads";
            List<Demande> demandes = demandeRepository.findAll();
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:demandes.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(demandes);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("cree par", "Fiacre");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "demandes.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    public ResponseEntity<byte[]> exportReportFacturefon() throws FileNotFoundException, JRException {
        try {
            String path = "C:\\Users\\Fiacre\\Downloads\\uploads";

            LocationFONDTO locationFONDTO = this.locationFONService.findById(457);
            FactureFONDTO factureFONDTO = this.factureFONService.findById(460);
            DemandeDTO demandeDTO = this.locationFONService.DemandeByIdLocationfon(457);
            DemandeurDTO demandeurDTO = demandeDTO.getDemandeurDTO();
            List<LiaisonFONDTO> liaisonFONList= locationFONDTO.getLiaisonfons();
            liaisonFONList.forEach(
                    element ->{
                        double prixTotalMetreLineaireLiaison = locationFONService.getPrixTotalMetreLineaireLiaison(457, element.getId());
                        log.info(String.valueOf(prixTotalMetreLineaireLiaison));
                        element.setCoutMetreLineaireLiaison(prixTotalMetreLineaireLiaison);
                    }
            );
            Double CoutMetreLineaireTotal = this.locationFONService.getPrixTotalMetreLineaire(457,460);
            Double CoutMetreLineaireTotalHTVA = this.locationFONService.getPrixTotalMetreLineaireHTVA(457);

            log.info(String.valueOf(demandeurDTO));
            log.info("******************************************************************");
            log.info(String.valueOf(demandeDTO));
            log.info("******************************************************************");
            log.info(String.valueOf(locationFONDTO));
            log.info("******************************************************************");
            log.info(liaisonFONList.toString());
            log.info("******************************************************************");
            log.info(factureFONDTO.toString());
            log.info("******************************************************************");
            log.info(CoutMetreLineaireTotalHTVA.toString());
            log.info("******************************************************************");
            log.info(CoutMetreLineaireTotal.toString());


            //load file and compile it
            File file = ResourceUtils.getFile("classpath:facturefon.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(liaisonFONList);
            Map<String, Object> parameters = new HashMap<>();

            //elements à droite dans l'entete
            parameters.put("nomDemandeur", demandeurDTO.getNom());
            parameters.put("bpDemandeur",demandeurDTO.getBoitePostale());
            parameters.put("emailDemandeur",demandeurDTO.getEmail());
            parameters.put("telDemandeur",demandeurDTO.getTel());
            parameters.put("tvaFacture",factureFONDTO.getTvaDTO().getTva());
          //  parameters.put("periodeDebutLocationfon",locationFONDTO.getPeriodeDebut());
           // parameters.put("periodeFinLocationfon",locationFONDTO.getPeriodeFin());

            //elements à gauche dans l'entete
            parameters.put("rccmDemandeur",demandeurDTO.getRccm());
            parameters.put("ifuDemandeur",demandeurDTO.getIfu());

            //total du cout par metre lineaire
            parameters.put("coutTotalMetreLineaireHTVA",CoutMetreLineaireTotalHTVA);

            //total du cout par metre lineaire
            parameters.put("coutTotalMetreLineaire",CoutMetreLineaireTotal);



            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "facture de fibre optique noire.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
