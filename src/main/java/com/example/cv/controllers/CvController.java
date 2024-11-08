    package com.example.cv.controllers;

    import com.example.cv.exception.BusinessException;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;



/****/
    import com.example.cv.config.ReportTypeEnum;
    import com.example.cv.dto.*;
    import com.example.cv.entities.*;
    import com.example.cv.services.CvService;

    import java.time.LocalDateTime;
    import java.util.*;


    import com.example.cv.exception.ApiException;

    import org.springframework.core.io.ByteArrayResource;
    import org.springframework.core.io.Resource;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.util.StringUtils;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    // import org.springframework.web.server.ResponseStatusException;

    @RestController
    @RequestMapping("/cv")

    public class CvController {

        @Autowired
        private CvService cvService;

        @GetMapping("/healthcheck")
        public ResponseEntity<String> isAlive() {
            return new ResponseEntity<>("Service is up and running!", HttpStatus.OK);
        }

        @GetMapping("/skills/{skillId}/cvs")
        public ResponseEntity<List<Cv>> getCvsBySkillId(@PathVariable Long skillId) {
            List<Cv> cvs = cvService.getCvsBySkillId(skillId);
            return ResponseEntity.ok(cvs);
        }
        @PostMapping(consumes = "multipart/form-data")
        public ResponseEntity<?> createCv(@RequestPart("cvInfo") CvPersonalInfoDTO cvPersonalInfo,
                                                    @RequestPart("image") MultipartFile image) {
            // Check if the email is already used
            if (cvService.isEmailAlreadyUsed(cvPersonalInfo.getEmail())) {
                return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
            }

            try {
                byte[] imageBytes = image.getBytes();
                Cv savedCv = cvService.createCv(cvPersonalInfo, imageBytes);
                return new ResponseEntity<>(savedCv, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error processing request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



        @PostMapping("/{cvId}/skills")
        public ResponseEntity<Object> addSkillsToCv(@PathVariable Long cvId, @RequestBody List<CvSkillDTO> cvSkillDTOList) {
            try {
                List<CvSkill> cvSkills = new ArrayList<>();
                for (CvSkillDTO cvSkillDTO : cvSkillDTOList) {
                    CvSkill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);
                    cvSkills.add(cvSkill);
                }
                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus())
                        .body(createErrorResponse(ex));
            }
        }

        private Map<String, Object> createErrorResponse(ApiException ex) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", ex.getStatus());
            errorResponse.put("errorMessages", ex.getErrorMessages());
            return errorResponse;
        }

        @PostMapping("/{cvId}/languages")
        public ResponseEntity<Object> addLanguagesToCV(@PathVariable Long cvId,
                @RequestBody List<CvLanguageDTO> cvLanguageDTOList) {
            try {
                List<CvLanguage> cvLanguages = new ArrayList<>();

                for (CvLanguageDTO cvLanguageDTO : cvLanguageDTOList) {

                    CvLanguage cvLanguage = cvService.addLanguageToCV(cvId, cvLanguageDTO);

                    cvLanguages.add(cvLanguage);
                }

                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
            }
        }

        @PostMapping("/{cvId}/hobbies")
        public ResponseEntity<Object> addHobbyToCV(@PathVariable Long cvId, @RequestBody List<CvHobbyDTO> cvHobbyDTOList) {
            try {
                List<CvHobby> cvHobbies = new ArrayList<>();

                for (CvHobbyDTO cvHobbyDTO : cvHobbyDTOList) {

                    CvHobby cvHobby = cvService.addHobbyToCV(cvId, cvHobbyDTO);

                    cvHobbies.add(cvHobby);
                }

                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
            }
        }
        @PostMapping("/{cvId}/titles")
        public ResponseEntity<Object> addDisplayLanguageToCV(@PathVariable Long cvId, @RequestBody CvDisplayLanguageDTO cvDisplayLanguageDTO) {
            try {
                    CvDisplayLanguage cvDisplayLanguage = cvService.addDisplayLanguageToCV(cvId, cvDisplayLanguageDTO);
                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
            }
        }


        @PostMapping("/{cvId}/experiences")
        public ResponseEntity<Object> addExperiencesToCv(@PathVariable Long cvId,
                @RequestBody List<CvExperienceDTO> cvExperienceDTOList) {
            try {

                List<CvExperience> cvExperiences = new ArrayList<>();

                for (CvExperienceDTO cvExperienceDTO : cvExperienceDTOList) {

                    CvExperience cvExperience = cvService.addExperienceToCv(cvId, cvExperienceDTO);

                    cvExperiences.add(cvExperience);
                }
                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
            }
        }

        @PostMapping("/{cvId}/formations")
        public ResponseEntity<Object> addFormationsToCv(@PathVariable Long cvId,
                @RequestBody List<CvFormationDTO> cvFormationDTOList) {
            try {
                List<CvFormation> cvFormations = new ArrayList<>();

                for (CvFormationDTO cvFormationDTO : cvFormationDTOList) {

                    CvFormation cvFormation = cvService.addFormationToCv(cvId, cvFormationDTO);

                    cvFormations.add(cvFormation);
                }
                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
            }
        }

        @PostMapping("/{cvId}/certificates")
        public ResponseEntity<Object> addCertificatesToCv(@PathVariable Long cvId,
                @RequestBody List<CvCertificateDTO> cvCertificateDTOList) {
            try {
                List<CvCertificate> cvCertificates = new ArrayList<>();
                for (CvCertificateDTO cvCertificateDTO : cvCertificateDTOList) {
                    CvCertificate cvCertificate = cvService.addCertificateToCv(cvId, cvCertificateDTO);
                    cvCertificates.add(cvCertificate);
                }
                return new ResponseEntity<>("saved successfully", HttpStatus.OK);
            } catch (ApiException ex) {
                return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
            }
        }




        private static final Logger log = LoggerFactory.getLogger(CvController.class);

        @GetMapping("/export/{cvId}")
        public ResponseEntity<Resource> exportCV(@PathVariable Long cvId, @RequestParam("fileType") String fileType , @RequestParam("lang") String language) throws Exception {
            Locale locale= StringUtils.parseLocaleString(language);
            ReportTypeEnum report = ReportTypeEnum.getReportTypeByCode(fileType);
            log.info("Eum :"+report);
            // byte[] bytes = reportsService.employeeJasperReport24(fileType);
            byte[] bytes = cvService.exportCV(cvId,fileType , locale);
            if (null != bytes) {
                ByteArrayResource resource = new ByteArrayResource(bytes);
               String fileName = "cv" + "_" + cvId + "_" + LocalDateTime.now() + report.getExtension();
                return ResponseEntity.ok()
                        .header(com.google.common.net.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .contentLength(resource.contentLength())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                throw new BusinessException("File Download Failed");
            }
        }



        @GetMapping("/generateQrCode/{cvId}")
        public ResponseEntity<Map<String, String>> generateQrCode(@PathVariable Long cvId) {
            try {
                Resource qrCode = cvService.generateQrCode(cvId);
                String encodedQrCode = Base64.getEncoder().encodeToString(qrCode.getInputStream().readAllBytes());
                Map<String, String> response = new HashMap<>();
                response.put("qrCode", encodedQrCode);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PostMapping("/{cvId}/uploadImage")
        public ResponseEntity<String> uploadCvImage(@PathVariable Long cvId, @RequestParam("image") MultipartFile image) {
            try {
                // Convert MultipartFile to byte[]
                byte[] imageBytes = image.getBytes();

                // Save the image to the database using your service
                cvService.saveCvImage(cvId, imageBytes);

                return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error uploading image", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping("/imagesTemplates")
        public ResponseEntity<List<Map<String, Object>>> getAllTemplateImages() {
            List<Map<String, Object>> images = cvService.getAllTemplateImages();
            return ResponseEntity.ok().body(images);
        }




//        @GetMapping("/images")
//        public ResponseEntity<List<byte[]>> getAllTemplateImages() {
//            List<byte[]> images = cvService.getAllTemplateImages();
//            return ResponseEntity.ok().body(images);
//        }

        @GetMapping("/skills")
        public ResponseEntity<List<Skill>> getAllSkills() {
            List<Skill> skills = cvService.findAllSkills();
            return ResponseEntity.ok(skills);
        }

        @GetMapping("/levels_skills")
        public ResponseEntity<List<LevelSkill>> getAllLevelSkills() {
            List<LevelSkill> levelSkills = cvService.findAllLevelSkills();
            return ResponseEntity.ok(levelSkills);
        }
        @GetMapping("/countries")
        public ResponseEntity<List<Country>> getAllCountries() {
            List<Country> countries = cvService.findAllCountries();
            return ResponseEntity.ok(countries);
        }
        @GetMapping("/companies")
        public ResponseEntity<List<Company>> getAllCompanies() {
            List<Company> companies = cvService.findAllCompanies();
            return ResponseEntity.ok(companies);
        }

        @GetMapping("/cities")
        public ResponseEntity<List<City>> getAllCities() {
            List<City> cities = cvService.findAllCities();
            return ResponseEntity.ok(cities);
        }
//        @GetMapping("/statusOffers")
//        public ResponseEntity<List<StatusOffer>> getAllStatusOffers() {
//            List<StatusOffer> statusOffers = cvService.findAllStatusOffers();
//            return ResponseEntity.ok(statusOffers);
//        }
        @GetMapping("/contractTypes")
        public ResponseEntity<List<ContractType>> getAllContractTypes() {
            List<ContractType> contractTypes = cvService.findAllContractTypes();
            return ResponseEntity.ok(contractTypes);
        }
        @GetMapping("/schools")
        public ResponseEntity<List<School>> getAllSchools() {
            List<School> schools = cvService.findAllSchools();
            return ResponseEntity.ok(schools);
        }
        @GetMapping("/mentions")
        public ResponseEntity<List<Mention>> getAllMentions() {
            List<Mention> mentions = cvService.findAllMentions();
            return ResponseEntity.ok(mentions);
        }
        @GetMapping("/levelsFormations")
        public ResponseEntity<List<LevelFormation>> getAllLevelFormations() {
            List<LevelFormation> levelFormations = cvService.findAllLevelFormations();
            return ResponseEntity.ok(levelFormations);
        }
        @GetMapping("/certificates")
        public ResponseEntity<List<Certificate>> getAllCertificates() {
            List<Certificate> certificates = cvService.findAllCertificates();
            return ResponseEntity.ok(certificates);
        }

        @GetMapping("/languages")
        public ResponseEntity<List<Language>> getAllLanguages() {
            List<Language> languages = cvService.findAllLanguages();
            return ResponseEntity.ok(languages);
        }

        @GetMapping("/levels_languages")
        public ResponseEntity<List<LevelLanguage>> getAllLevelLanguages() {
            List<LevelLanguage> levelLanguages = cvService.findAllLevelLanguages();
            return ResponseEntity.ok(levelLanguages);
        }

        @GetMapping("hobbies")
        public ResponseEntity<List<Hobby>>getALLHobbies(){
            List<Hobby> hobbies = cvService.findAllHobbies();
            return ResponseEntity.ok(hobbies);
        }
        @GetMapping("/templates/{id}")
        public ResponseEntity<Template> getTemplateById(@PathVariable Long id) {
            Template template = cvService.getTemplateById(id);
            return new ResponseEntity<>(template, HttpStatus.OK);
        }
    }

