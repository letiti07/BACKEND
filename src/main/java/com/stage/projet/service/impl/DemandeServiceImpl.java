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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
                LocationFONDTO byId = this.locationFONService.findById(element.getLocationFONDTO().getId());
                element.setLocationFONDTO(byId);
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
        //    log.info(String.valueOf(element));

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



    public ResponseEntity<byte[]> exportReportFacturefon(Integer idLocation, Integer idFacture) throws FileNotFoundException, JRException {
       // log.info(String.valueOf(idLocation),idFacture);
        try {
            String path = "C:\\Users\\Fiacre\\Downloads\\uploads";

            LocationFONDTO locationFONDTO = this.locationFONService.findById(idLocation);
            FactureFONDTO factureFONDTO = this.factureFONService.findById(idFacture);
            String numeroFacture= "N°"+factureFONDTO.getId() + "/2023/DSI/DIFO";
            DemandeDTO demandeDTO = this.locationFONService.DemandeByIdLocationfon(idLocation);
            DemandeurDTO demandeurDTO = demandeDTO.getDemandeurDTO();
            List<LiaisonFONDTO> liaisonFONList= locationFONDTO.getLiaisonfons();
            List<LiaisonFactureDTO> liaisonFactureDTOList = new ArrayList<LiaisonFactureDTO>();
            List<SiteDTO> siteDTOList = new ArrayList<>();


            liaisonFONList.forEach(
                    element ->{
                        List<PointConnexionDTO> pointconnexions = element.getPointconnexions();

                        pointconnexions.forEach(
                                pointConnexion -> {
                                    SiteDTO siteDTO = new SiteDTO();
                                    siteDTO.setDesignation(element.getDebut() + " - " + element.getFin());
                                    siteDTO.setPeriode(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(factureFONDTO.getDebutPeriode())) + " à " + String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(factureFONDTO.getFinPeriode())));
                                    siteDTO.setDuree(String.valueOf(factureFONDTO.getDuree()) + " mois");
                                    siteDTO.setSite(pointConnexion.getNom());
                                    siteDTO.setType(pointConnexion.getTypeHebergement());
                                    siteDTO.setPrix_total((int) pointConnexion.getFraisHebergement());
                                    siteDTOList.add(siteDTO);
                                }

                        );

                    }
            );


            liaisonFONList.forEach(
                    element ->{
                        double prixTotalMetreLineaireLiaison = locationFONService.getPrixTotalMetreLineaireLiaison(idLocation, element.getId());
                        log.info(String.valueOf(prixTotalMetreLineaireLiaison));
                        element.setCoutMetreLineaireLiaison(prixTotalMetreLineaireLiaison);
                        LiaisonFactureDTO liaisonFactureDTO = new LiaisonFactureDTO();
                        liaisonFactureDTO.setDesignation( element.getDebut() + " - " + element.getFin());
                        liaisonFactureDTO.setPeriode(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(factureFONDTO.getDebutPeriode())) + " à " + String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(factureFONDTO.getFinPeriode())));
                        liaisonFactureDTO.setDuree(String.valueOf(factureFONDTO.getDuree()) + " mois");
                        liaisonFactureDTO.setLongueur(String.valueOf(((int) element.getDistance())) + " mètres");
                        liaisonFactureDTO.setPrix_unitaire(BigDecimal.valueOf(75));
                        liaisonFactureDTO.setPrix_total(BigDecimal.valueOf(element.getCoutMetreLineaireLiaison()));
                        liaisonFactureDTOList.add(liaisonFactureDTO);

                    }
            );



            Double CoutMetreLineaireTotalttc = this.locationFONService.getPrixTotalMetreLineaire(idLocation,idFacture);
            Double CoutMetreLineaireTotalHTVA = this.locationFONService.getPrixTotalMetreLineaireHTVA(idLocation);
            Double CoutMetreLineaireTotalWithTVA= this.locationFONService.getPrixTotalMetreLineaireWithTVA(idLocation,idFacture);

            Double FraisHebergementTotalttc = this.locationFONService.getFraisHebergementTotal(idLocation,idFacture);
            Double FraisHebergementTotalHTVA = this.locationFONService.getFraisHebergementTotalHTVA(idLocation);
            Double FraisHebergementTotalWithTVA= this.locationFONService.getFraisHebergementTotalWithTva(idLocation,idFacture);

            String FraisHebergementTotalLetters= (int)Math.round(FraisHebergementTotalttc) + " franc CFA";
            String coutTotalMetreLineaireLetters = (Long)Math.round(CoutMetreLineaireTotalttc) + " franc CFA";

            String montantTotalFacture= String.valueOf( Math.round(CoutMetreLineaireTotalttc + FraisHebergementTotalttc));


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


            //elements à gauche dans l'entete
            parameters.put("rccmDemandeur",demandeurDTO.getRccm());
            parameters.put("ifuDemandeur",demandeurDTO.getIfu());

            parameters.put("idFacture", numeroFacture);

            log.info(String.valueOf(CoutMetreLineaireTotalHTVA));
            //cout total htva
            parameters.put("coutTotalMetreLineaireHTVA",(Long) Math.round(CoutMetreLineaireTotalHTVA));

            //cout total ttc
            parameters.put("coutTotalMetreLineaire",(Long)Math.round (CoutMetreLineaireTotalttc));

            //cout total with tva
            parameters.put("coutTotalMetreLineaireWithTva", (int)Math.round(CoutMetreLineaireTotalWithTVA));

            //cout total htva
            parameters.put("FraisHebergementHTVA",(int) Math.round(FraisHebergementTotalHTVA));

            //cout total ttc
            parameters.put("FraisHebergementTotal",(int)Math.round (FraisHebergementTotalttc));

            //cout total with tva
            parameters.put("FraisHebergementWithTVA", (int)Math.round(FraisHebergementTotalWithTVA));
            log.info(String.valueOf(FraisHebergementTotalWithTVA));

            //periode de facture
            parameters.put("periode",String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(factureFONDTO.getDebutPeriode())) + " à " + String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(factureFONDTO.getFinPeriode())));


            parameters.put("FraisHebergementTotalLetters", FraisHebergementTotalLetters);

            parameters.put("coutTotalMetreLineaireLetters",coutTotalMetreLineaireLetters);

            parameters.put("montantTotalFacture",montantTotalFacture + " franc CFA");


            parameters.put("data_liaison",new JRBeanCollectionDataSource(liaisonFactureDTOList));

            parameters.put("data_liaison1",new JRBeanCollectionDataSource(siteDTOList));


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "facture de fibre optique noire.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<byte[]> exportReportRecuPaiementfon(Integer idLocation, Integer idFacture) throws FileNotFoundException, JRException {
        try {
            String path = "C:\\Users\\Fiacre\\Downloads\\uploads";

            LocationFONDTO locationFONDTO = this.locationFONService.findById(idLocation);
            FactureFONDTO factureFONDTO = this.factureFONService.findById(idFacture);
            String numeroFacture= "2023-00"+factureFONDTO.getId() ;
            VirementDTO virementDTO = factureFONDTO.getVirementDTO();
            ChecqueDTO checqueDTO = factureFONDTO.getChecqueDTO();
            Date dateEtablissement= new Date();
            String numeroRecu="";
            String modeReglement= "";
            String statusFacture= "";
            if(factureFONDTO.getEtat().equals("annulee")){
                statusFacture= "ANNULE" ;
            }
            if(factureFONDTO.getEtat().equals("payee")){
                statusFacture= "PAYEE";
            }

                if(virementDTO != null){

                    numeroRecu = factureFONDTO.getVirementDTO().getId() + "/0001";
                    modeReglement= "VIREMENT" ;
                    log.info(modeReglement);

                }
                if(checqueDTO != null){
                    numeroRecu = factureFONDTO.getChecqueDTO().getId() + "/001";
                    modeReglement= "CHECQUE" ;
                    log.info(modeReglement);

                }


            log.info(String.valueOf(factureFONDTO));
            log.info(modeReglement);
            log.info(String.valueOf(virementDTO));
            DemandeDTO demandeDTO = this.locationFONService.DemandeByIdLocationfon(idLocation);
            DemandeurDTO demandeurDTO = demandeDTO.getDemandeurDTO();



            Double FraisHebergementTotalttc = this.locationFONService.getFraisHebergementTotal(idLocation,idFacture);
            Double CoutMetreLineaireTotalttc = this.locationFONService.getPrixTotalMetreLineaire(idLocation,idFacture);
            Long montantTotalFacture= Math.round(CoutMetreLineaireTotalttc + FraisHebergementTotalttc);


            //load file and compile it
            File file = ResourceUtils.getFile("classpath:recuPaiementfon.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> parameters = new HashMap<>();

            //elements à droite dans l'entete
            parameters.put("nomDemandeur", demandeurDTO.getNom());


            parameters.put("numeroFacture", numeroFacture);

            //cout montant total Facture
            parameters.put("montantTotalFacture",montantTotalFacture);

            parameters.put("montantEspece",montantTotalFacture);

            parameters.put("statusFacture",statusFacture);

            parameters.put("modeReglement",modeReglement);

            parameters.put("dateEtablissement", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateEtablissement));

            parameters.put("numeroRecu", numeroRecu);



            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "reçu de paiement de fibre optique noire.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
