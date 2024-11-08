package com.example.cv.services.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
//import com.aspose.words.*;
import com.example.cv.config.ImageProcessor;
import com.example.cv.config.JasperReportsUtil;
import com.example.cv.config.ReportTypeEnum;
import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.exception.*;
import com.example.cv.repositories.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.example.cv.services.CvService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CvServiceImpl implements CvService {

    private final MessageSource messageSource;

    @Autowired
    CvRepository cvRepository;

//    @Autowired
//    private MessageSource messageSource;
    @Autowired
    private SkillRepository skillRepository;
//    @Autowired
//    private StatusOfferRepository statusOfferRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private CvHobbyRepository cvHobbyRepository;
    @Autowired
    private DisplayLanguageRepository displayLanguageRepository;
    @Autowired
    private CvDisplayLanguageRepository cvDisplayLanguageRepository;

    @Autowired
    private LevelSkillRepository levelSkillRepository;

    @Autowired
    private CvSkillRepository cvSkillRepository;

    @Autowired
    private CvEperienceRepository cvEperienceRepository;

    @Autowired
    private CvFormationRepository cvFormationRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private LevelFormationRepository levelFormationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private CvCertificateRepository cvCertificateRepository;

    @Autowired
    private CvLanguageRepository cvLanguageRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LevelLanguageRepository levelLanguageRepository;

    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private JasperReportsUtil jasperReportsUtil;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<Map<String, Object>> getAllTemplateImages() {
        List<Template> templates = templateRepository.findAll();
        return templates.stream()
                .map(template -> {
                    Map<String, Object> templateData = new HashMap<>();
                    templateData.put("id", template.getTemplateID());
                    templateData.put("imageSrc", Base64.getEncoder().encodeToString(template.getTemplateImage()));
                    return templateData;
                })
                .collect(Collectors.toList());
    }


//    @Override
//    public List<byte[]> getAllTemplateImages() {
//        List<Template> templates = templateRepository.findAll();
//        return templates.stream()
//                .map(Template::getTemplateImage)
//                .collect(Collectors.toList());
//    }

    public boolean isEmailAlreadyUsed(String email) {
        Optional<Cv> existingUser = cvRepository.findByEmail(email);
        return existingUser.isPresent();
    }

    @Override
    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo, byte[] imageBytes) {
        Cv newCv = new Cv();

        newCv.setNom(cvPersonalInfo.getNom());
        newCv.setPrenom(cvPersonalInfo.getPrenom());
        newCv.setDateDeNaissance(cvPersonalInfo.getDateDeNaissance());
        newCv.setEmail(cvPersonalInfo.getEmail());
        newCv.setTel1(cvPersonalInfo.getTel1());
        newCv.setFixmobile(cvPersonalInfo.getFixmobile());
        newCv.setAddress(cvPersonalInfo.getAddress());
        newCv.setLinkedin(cvPersonalInfo.getLinkedin());
        newCv.setProfile(cvPersonalInfo.getProfile());

        if (cvPersonalInfo.getTemplateId() != null) {
            Template template = templateRepository.findById(cvPersonalInfo.getTemplateId()).orElse(null);
            newCv.setTemplate(template);
        }

        newCv.setCreatedAt(new Date());
        newCv.setUpdatedAt(new Date());
        newCv.setImage(imageBytes);

        return cvRepository.save(newCv);
    }

    @Override
    public List<Cv> getCvsBySkillId(Long skillId) {
        return cvRepository.findCvsBySkillId(skillId);
    }


    @Override
    public CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        List<String> errorMessages = new ArrayList<>();
        try {
            Cv cv = cvRepository.findById(cvId)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "CV not found with ID: " + cvId));

            Skill skill = skillRepository.findById(cvSkillDTO.getSkillID()).orElseThrow(
                    () -> new EntityNotFoundException(
                            "Skill not found with ID: " + cvSkillDTO.getSkillID()));

            LevelSkill levelSkill = levelSkillRepository.findById(cvSkillDTO.getLevelSkillID())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "LevelSkill not found with ID: "
                                    + cvSkillDTO.getLevelSkillID()));

            CvSkill cvSkill = new CvSkill();

            cvSkill.setCv(cv);
            cvSkill.setSkill(skill);
            cvSkill.setLevelSkill(levelSkill);
            cvSkill.setCreatedAt(new Date());
            cvSkill.setUpdatedAt(new Date());

            return cvSkillRepository.save(cvSkill);
        } catch (EntityNotFoundException ex) {
            errorMessages.add(ex.getMessage());
            throw new ApiException(HttpStatus.NOT_FOUND, errorMessages);
        } catch (Exception ex) {
            errorMessages.add("An error occurred while adding skill to CV");
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessages);
        }
    }

    @Override
    public CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        Language language = languageRepository.findById(cvLanguageDTO.getLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Language not found with ID: "
                                + cvLanguageDTO.getLanguageID())));
        LevelLanguage levelLanguage = levelLanguageRepository.findById(cvLanguageDTO.getLevelLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("LevelLanguage not found with ID: "
                                + cvLanguageDTO.getLevelLanguageID())));

        CvLanguage cvLanguage = new CvLanguage();

        cvLanguage.setCv(cv);
        cvLanguage.setLanguage(language);
        cvLanguage.setLevelLanguage(levelLanguage);
        cvLanguage.setCreatedAt(new Date());
        cvLanguage.setUpdatedAt(new Date());

        return cvLanguageRepository.save(cvLanguage);
    }



    @Override
    public CvHobby addHobbyToCV(Long cvId, CvHobbyDTO cvHobbyDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        Hobby hobby = hobbyRepository.findById(cvHobbyDTO.getHobbyID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Hobby not found with ID:" + cvHobbyDTO.getHobbyID())));
        CvHobby cvHobby = new CvHobby();

        cvHobby.setCv(cv);
        cvHobby.setHobby(hobby);
        cvHobby.setCreatedAt(new Date());
        cvHobby.setUpdatedAt(new Date());
        return cvHobbyRepository.save(cvHobby);
    }
    @Override
    public CvDisplayLanguage addDisplayLanguageToCV(Long cvId, CvDisplayLanguageDTO cvDisplayLanguageDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        DisplayLanguage displayLanguage = displayLanguageRepository.findById(cvDisplayLanguageDTO.getDisplayLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("LanguageTitle not found with ID:" + cvDisplayLanguageDTO.getDisplayLanguageID())));
        CvDisplayLanguage cvDisplayLanguage = new CvDisplayLanguage();

        cvDisplayLanguage.setCv(cv);
        cvDisplayLanguage.setDisplayLanguage(displayLanguage);
        cvDisplayLanguage.setCreatedAt(new Date());
        cvDisplayLanguage.setUpdatedAt(new Date());
        return cvDisplayLanguageRepository.save(cvDisplayLanguage);
    }

    @Override
    public CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID: " + cvId)));
        Company company = companyRepository.findById(cvExperienceDTO.getCompanyID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Company not found with ID:"
                                + cvExperienceDTO.getCompanyID())));
        Country country = countryRepository.findById(cvExperienceDTO.getCountryID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Country not found with ID:"
                                + cvExperienceDTO.getCountryID())));

        CvExperience cvExperience = new CvExperience();

        cvExperience.setCv(cv);
        cvExperience.setCompany(company);
        cvExperience.setDateDebut(cvExperienceDTO.getDateDebut());
        cvExperience.setDateFin(cvExperienceDTO.getDateFin());
        cvExperience.setDescription(cvExperienceDTO.getDescription());
        cvExperience.setFonction(cvExperienceDTO.getFonction());
        cvExperience.setCountry(country);
        cvExperience.setFonction(cvExperience.getFonction());
        cvExperience.setCreatedAt(new Date());
        cvExperience.setUpdatedAt(new Date());

        return cvEperienceRepository.save(cvExperience);
    }

    @Override
    public CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
//        School school = schoolRepository.findById(cvFormationDTO.getSchoolID())
//                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
//                        Arrays.asList("School not found with ID:"
//                                + cvFormationDTO.getSchoolID())));
        Mention mention = mentionRepository.findById(cvFormationDTO.getMentionID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Mention not found with ID:"
                                + cvFormationDTO.getMentionID())));
        LevelFormation levelFormation = levelFormationRepository.findById(cvFormationDTO.getLevelFormationID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("LevelFormation not found with ID:"
                                + cvFormationDTO.getLevelFormationID())));
        Country country = countryRepository.findById(cvFormationDTO.getCountryID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Country not found with ID:"
                                + cvFormationDTO.getCountryID())));
        School school = schoolRepository.findById(cvFormationDTO.getSchoolID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("School not found with ID:"
                                + cvFormationDTO.getSchoolID())));
        City city = cityRepository.findById(cvFormationDTO.getCityID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("City not found with ID:"
                                + cvFormationDTO.getCityID())));



        CvFormation cvFormation = new CvFormation();

        cvFormation.setCv(cv);
        cvFormation.setSchool(school);
        cvFormation.setCity(city);
        cvFormation.setFormationTitle(cvFormationDTO.getFormationTitle());
        cvFormation.setMention(mention);
        cvFormation.setLevelFormation(levelFormation);
        cvFormation.setCountry(country);
        cvFormation.setDateDebut(cvFormationDTO.getDateDebut());
        cvFormation.setDateFin(cvFormationDTO.getDateFin());
        cvFormation.setDescription(cvFormationDTO.getDescription());
        cvFormation.setCreatedAt(new Date());
        cvFormation.setUpdatedAt(new Date());

        return cvFormationRepository.save(cvFormation);
    }

    @Override
    public CvCertificate addCertificateToCv(Long cvId, CvCertificateDTO cvCertificateDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID: " + cvId)));
        // Arrays.asList("CV not found") hadi katraja3 String liste dyal String fiha
        // element wahed li howa "CV not found"
        // kandiro hoka 7it ApiException katakhod liste dyal String ok
        Certificate certificate = certificateRepository.findById(cvCertificateDTO.getCertificateID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Certificate not found with ID: "
                                + cvCertificateDTO.getCertificateID())));

        CvCertificate cvCertificate = new CvCertificate();

        cvCertificate.setCv(cv);
        cvCertificate.setCertificate(certificate);
        cvCertificate.setDateAcquisition(cvCertificateDTO.getDateAcquisition());
        cvCertificate.setCreatedAt(new Date());
        cvCertificate.setUpdatedAt(new Date());

        return cvCertificateRepository.save(cvCertificate);
    }

    @Override
    public Resource generateQrCode(Long cvId) throws Exception {
        try {
            String downloadUrl = "http://localhost:8087/cv/download/" + cvId;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(downloadUrl, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            return new ByteArrayResource(pngData);
        } catch (Exception e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("Error generating QR code: " + e.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessages);
        }
    }




//jasperreport version final :

    public byte[] exportCV(Long cvId, String fileType , Locale locale) throws Exception {
        Cv cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));
        String cvTemplateData = cv.getTemplate().getTemplateData();
//        String experiencesFormatted = String.join("", experiencesList);

        // Prepare parameters
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("experiences", experiencesFormatted);

        // Add extra parameters
        parameters.put("prenom", cv.getPrenom());
        parameters.put("nom", cv.getNom());
        parameters.put("address", cv.getAddress());
        parameters.put("tel1", cv.getTel1());
        parameters.put("email", cv.getEmail());
        parameters.put("profile", cv.getProfile());
        setLocalizedParameters(parameters,locale);

        parameters.put("IMAGE_PATH_1", getClass().getClassLoader().getResource("reports/template1/Rectangle.png").toString());
        parameters.put("IMAGE_PATH_2", getClass().getClassLoader().getResource("reports/template1/email.svg").toString());
        parameters.put("IMAGE_PATH_3", getClass().getClassLoader().getResource("reports/template1/location.svg").toString());
        parameters.put("IMAGE_PATH_4", getClass().getClassLoader().getResource("reports/template1/phone.svg").toString());
        parameters.put("IMAGE_PATH_5", getClass().getClassLoader().getResource("reports/template1/line2.png").toString());


        parameters.put("IMAGE_PATH_13", getClass().getClassLoader().getResource("reports/template1/Group.png").toString());
        parameters.put("IMAGE_PATH_6", getClass().getClassLoader().getResource("reports/template1/Group1.png").toString());
        parameters.put("IMAGE_PATH_7", getClass().getClassLoader().getResource("reports/template1/Group2.png").toString());
        parameters.put("IMAGE_PATH_8", getClass().getClassLoader().getResource("reports/template1/Group3.png").toString());
        parameters.put("IMAGE_PATH_9", getClass().getClassLoader().getResource("reports/template1/Group4.png").toString());
        parameters.put("IMAGE_PATH_10", getClass().getClassLoader().getResource("reports/template1/Group5.png").toString());
        parameters.put("IMAGE_PATH_11", getClass().getClassLoader().getResource("reports/template1/Group6.png").toString());
        parameters.put("IMAGE_PATH_12", getClass().getClassLoader().getResource("reports/template1/Group7.png").toString());
//        parameters.put("IMAGE_PATH_2", getClass().getClassLoader().getResource("reports/template1/Rectangle2.png").toString());



        if (cvTemplateData.equals("cv1.jrxml") || cvTemplateData.equals("cv2.jrxml")) {
            // Process image from byte[] to BufferedImage for these templates
            ByteArrayInputStream bais = new ByteArrayInputStream(cv.getImage());
            BufferedImage originalImage = ImageIO.read(bais);
            BufferedImage circularImage = ImageProcessor.makeCircularImage(originalImage);
            parameters.put("image", circularImage);
        } else if (cvTemplateData.equals("cv3.jrxml") || cvTemplateData.equals("cv4.jrxml")) {

            // Convert byte[] to BufferedImage even if you are using raw image
            ByteArrayInputStream bais = new ByteArrayInputStream(cv.getImage());
            BufferedImage originalImage = ImageIO.read(bais);

            parameters.put("image", originalImage );
        }

        List<String> experiencesList = new ArrayList<>();
        for (CvExperience experience : cv.getCvExperiences()) {
            StringBuilder experienceDetails = new StringBuilder();

            if (cvTemplateData.equals("report.jrxml")) {
            experienceDetails.append("<b>").append(experience.getFonction()).append("</b> - ")
                    .append(new SimpleDateFormat("dd/MM/yyyy").format(experience.getDateDebut())).append(" to ")
                    .append(new SimpleDateFormat("dd/MM/yyyy").format(experience.getDateFin())).append("<br>")
                    .append("<b>").append(experience.getCompany().getCompanyName()).append("</b>, ")
                    .append(experience.getCountry().getCountryName()).append("<br>")
                    .append(experience.getDescription()).append("<br><br>");

        }else if (cvTemplateData.equals("cv1.jrxml")) {
                // Template 2 style (Styled to match the provided image)
                experienceDetails.append("<strong><span style='font-size: 10px; '>").append(experience.getFonction()).append("</span></strong><br>") // Bold job title
                        .append("<strong><i>").append(experience.getCompany().getCompanyName()).append("</i></strong>, ") // Italic and bold for company name
                        .append("<strong>").append(experience.getCountry().getCountryName()).append("</strong> | ") // Bold for country name
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateDebut())).append(" - ")
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateFin())).append("<br>") // Year period
                        .append("<span style='font-size: 10px;'>&#8226;   </span>").append(experience.getDescription()).append("<br><br>"); // Responsibility description as list items
            }else if (cvTemplateData.equals("cv2.jrxml")) {
                // Template 2 style (Styled to match the provided image)
                experienceDetails.append("<strong><span style='font-size: 10px; '>").append(experience.getFonction()).append("</span></strong><br>") // Bold job title
                        .append("<strong><i>").append(experience.getCompany().getCompanyName()).append("</i></strong>, ") // Italic and bold for company name
                        .append("<strong>").append(experience.getCountry().getCountryName()).append("</strong> | ") // Bold for country name
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateDebut())).append(" - ")
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateFin())).append("<br>") // Year period
                        .append("<span style='font-size: 10px;'>&#8226;   </span>").append(experience.getDescription()).append("<br><br>"); // Responsibility description as list items
            }else if (cvTemplateData.equals("cv3.jrxml")) {
                // Template 2 style (Styled to match the provided image)
                experienceDetails.append("<strong><span style='font-size: 10px; '>").append(experience.getFonction()).append("</span></strong><br>") // Bold job title
                        .append("<strong><i>").append(experience.getCompany().getCompanyName()).append("</i></strong>, ") // Italic and bold for company name
                        .append("<strong>").append(experience.getCountry().getCountryName()).append("</strong> | ") // Bold for country name
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateDebut())).append(" - ")
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateFin())).append("<br>") // Year period
                        .append("<span style='font-size: 10px;'>&#8226;   </span>").append(experience.getDescription()).append("<br><br>"); // Responsibility description as list items
            }else if (cvTemplateData.equals("cv4.jrxml")) {
                // Template 2 style (Styled to match the provided image)
                experienceDetails.append("<strong><span style='font-size: 10px; '>").append(experience.getFonction()).append("</span></strong><br>") // Bold job title
                        .append("<strong><i>").append(experience.getCompany().getCompanyName()).append("</i></strong>, ") // Italic and bold for company name
                        .append("<strong>").append(experience.getCountry().getCountryName()).append("</strong> | ") // Bold for country name
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateDebut())).append(" - ")
                        .append(new SimpleDateFormat("yyyy").format(experience.getDateFin())).append("<br>") // Year period
                        .append("<span style='font-size: 10px;'>&#8226;   </span>").append(experience.getDescription()).append("<br><br>"); // Responsibility description as list items
            }
            experiencesList.add(experienceDetails.toString());

            }

        parameters.put("experiences", String.join("", experiencesList));


        // Populate skills directly
        List<String> skillsList = new ArrayList<>();
        for (CvSkill skill : cv.getCvSkills()) {
            String skillDetails = "<b>" + skill.getSkill().getSkillName() + "</b> : " + skill.getLevelSkill().getLevelSkillName() + "<br>";
            skillsList.add(skillDetails);
        }
        parameters.put("skills", String.join("", skillsList));

        // Populate certificates directly
        List<String> certificatesList = new ArrayList<>();
        for (CvCertificate certificate : cv.getCvCertificates()) {
            String certificateDetails = "<b>" + certificate.getCertificate().getCertificateName() + "</b> : " + certificate.getCertificate().getDescription() + "<br>";
            certificatesList.add(certificateDetails);
        }
        parameters.put("certificates", String.join("", certificatesList));

        // Populate languages directly
        List<String> languagesList = new ArrayList<>();
        for (CvLanguage language : cv.getCvLanguages()) {
            String languageDetails = "<b>" + language.getLanguage().getLanguageName() + "</b> : " + language.getLevelLanguage().getLevelLanguageName() + "<br>";
            languagesList.add(languageDetails);
        }
        parameters.put("languages", String.join("", languagesList));

        // Populate formations directly
        List<String> formationsList = new ArrayList<>();
        for (CvFormation formation : cv.getCvFormations()) {
            String formationDetails = "<b>" + formation.getLevelFormation().getLevelFormationName() + "</b> - "
                    + new SimpleDateFormat("dd/MM/yyyy").format(formation.getDateDebut()) + " to "
                    + new SimpleDateFormat("dd/MM/yyyy").format(formation.getDateFin()) + "<br>"
                    + "<b>" + formation.getSchool().getSchoolName() + "</b>, " + formation.getCountry().getCountryName() + "<br><br>";
            formationsList.add(formationDetails);
        }
        parameters.put("formations", String.join("", formationsList));

        // Populate hobbies directly
        List<String> hobbiesList = new ArrayList<>();
        for (CvHobby hobby : cv.getCvHobbies()) {
            String hobbyDetails = "<span style='font-size: 10px;'>&#8226;</span>"+ "  " + hobby.getHobby().getHobbyName() + "<br>";
            hobbiesList.add(hobbyDetails);
        }
        parameters.put("hobbies", String.join("", hobbiesList));



        InputStream reportStream = resourceLoader.getResource("classpath:reports/templates/" + cvTemplateData).getInputStream();
//        InputStream reportStream = resourceLoader.getResource("classpath:reports/template1/" + cvTemplateData).getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(cv));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Convert fileType from String to ReportTypeEnum
        ReportTypeEnum reportTypeEnum = ReportTypeEnum.getReportTypeByCode(fileType);

        // Export PDF or other formats (PDF, DOCX, XLSX)
        return jasperReportsUtil.exportJasperReportBytes(jasperPrint, reportTypeEnum);
    }

    private void setLocalizedParameters(Map<String,Object> parameters, Locale locale){
        MessageSourceResourceBundle resourceBundle=new MessageSourceResourceBundle(messageSource,locale);
        parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE,resourceBundle);
        parameters.put(JRParameter.REPORT_LOCALE,locale);
    }



    public void saveCvImage(Long cvId, byte[] imageBytes) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        cv.setImage(imageBytes);
        cvRepository.save(cv);
    }

    @Override
    public List<Skill> findAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        if (skills.isEmpty()) {
            throw new SkillNotFoundException("No skills found");
        }
        return skills;
    }

    @Override
    public List<LevelSkill> findAllLevelSkills() {
        List<LevelSkill> levelSkills = levelSkillRepository.findAll();
        if (levelSkills.isEmpty()) {
            throw new LevelSkillNotFoundException("No level skills found");
        }
        return levelSkills;
    }

    public List<Country> findAllCountries() {
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new CountryNotFoundException("No countries found");
        }
        return countries;
    }

    public List<Company> findAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty()) {
            throw new CompanyNotFoundException("No companies found");
        }
        return companies;
    }
    public List<City> findAllCities() {
        List<City> cities = cityRepository.findAll();
        if (cities.isEmpty()) {
            throw new CityNotFoundException("No cities found");
        }
        return cities;
    }
//    public List<StatusOffer> findAllStatusOffers() {
//        List<StatusOffer> statusOffers = statusOfferRepository.findAll();
//        if (statusOffers.isEmpty()) {
//            throw new CityNotFoundException("No statusOffers found");
//        }
//        return statusOffers;
//    }
    public List<ContractType> findAllContractTypes() {
        List<ContractType> contractTypes = contractTypeRepository.findAll();
        if (contractTypes.isEmpty()) {
            throw new CityNotFoundException("No contractTypes found");
        }
        return contractTypes;
    }
    public List<School> findAllSchools() {
        List<School> schools = schoolRepository.findAll();
        if (schools.isEmpty()) {
            throw new SchoolNotFoundException("No schools found");
        }
        return schools;
    }
    public List<Mention> findAllMentions() {
        List<Mention> mentions = mentionRepository.findAll();
        if (mentions.isEmpty()) {
            throw new MentionNotFoundException("No mentions found");
        }
        return mentions;
    }
    public List<LevelFormation> findAllLevelFormations() {
        List<LevelFormation> levelFormations = levelFormationRepository.findAll();
        if (levelFormations.isEmpty()) {
            throw new LevelFormationNotFoundException("No levelFormations found");
        }
        return levelFormations;
    }

    @Override
    public List<Certificate> findAllCertificates() {
        return certificateRepository.findAll();
    }

    @Override
    public List<Language> findAllLanguages() {
        List<Language> languages = languageRepository.findAll();
        if (languages.isEmpty()) {
            throw new LanguageNotFoundException("No languages found");
        }
        return languages;
    }

    @Override
    public List<LevelLanguage> findAllLevelLanguages() {
        List<LevelLanguage> levelLanguages = levelLanguageRepository.findAll();
        if (levelLanguages.isEmpty()) {
            throw new LevelLanguageNotFoundException("No level Languages found");
        }
        return levelLanguages;
    }

    @Override
    public List<Hobby> findAllHobbies() {
        List<Hobby> hobbies = hobbyRepository.findAll();
        if (hobbies.isEmpty()) {
            throw new HobbyNotFoundException("No hobbies found");
        }
        return hobbies;
    }
    @Override
    public Template getTemplateById(Long id) {
        return templateRepository.findById(id).orElseThrow(() ->
                new TemplateNotFoundException("Template not found with ID: " + id));
    }


}