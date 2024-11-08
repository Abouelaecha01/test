package com.example.cv.services;

import com.example.cv.config.ReportTypeEnum;
import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.exception.ApiException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Locale;
import java.util.Map;


public interface CvService {

    Cv createCv(CvPersonalInfoDTO cvPersonalInfo, byte[] imageBytes);

    CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO);

    CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO);



    CvDisplayLanguage addDisplayLanguageToCV(Long cvId, CvDisplayLanguageDTO cvDisplayLanguageDTO);

    CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO);

    CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO);

    CvCertificate addCertificateToCv(Long cvId, CvCertificateDTO cvCertificateDTO);

//    CvTemplate addTemplateToCv(Long cvId, CvTemplateDTO cvTemplateDTO);

    boolean isEmailAlreadyUsed(String email);

//    byte[] exportCvToPdf(Long cvId) throws Exception;

//    byte[] exportCvToDocx(Long cvId) throws Exception;


//byte[] employeeJasperReportInBytes(String fileType) throws Exception;
    byte[] exportCV(Long cvId , String fileType , Locale locale) throws Exception;
    List<Cv> getCvsBySkillId(Long skillId);

    CvHobby addHobbyToCV(Long cvId, CvHobbyDTO cvHobbyDTO);

    void saveCvImage(Long cvId, byte[] imageBytes) throws ApiException;
    List<Map<String, Object>> getAllTemplateImages();
    List<Skill> findAllSkills();
    List<LevelSkill> findAllLevelSkills();
    List<Country> findAllCountries();
    List<Company> findAllCompanies();
    List<City> findAllCities();
//    List<StatusOffer> findAllStatusOffers();
    List<ContractType> findAllContractTypes();
    List<School> findAllSchools();
    List<Mention> findAllMentions();
    List<LevelFormation> findAllLevelFormations();
    List<Certificate> findAllCertificates();

    List<Language> findAllLanguages();
    List<LevelLanguage> findAllLevelLanguages();
    List<Hobby> findAllHobbies();
    Template getTemplateById(Long id); // zed had method
    Resource generateQrCode(Long cvId) throws Exception;


//    void exportJasperReport(HttpServletResponse response) throws JRException, IOException;

//    Cv exportCv(Long cvId);
}
