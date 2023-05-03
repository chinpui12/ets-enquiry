package com.nus.iss.enquiry.resource;


import com.nus.iss.enquiry.dto.EnquiryCriteria;
import com.nus.iss.enquiry.dto.EnquiryDTO;
import com.nus.iss.enquiry.service.EnquiryService;
import com.nus.iss.etscommon.dto.InternetTransferDTO;
import com.nus.iss.etscommon.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryResource {

    private final Logger log = LoggerFactory.getLogger(EnquiryResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnquiryService enquiryService;

    public EnquiryResource(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @PostMapping("/create-enquiry")
    public ResponseEntity<EnquiryDTO> createBooking(@RequestBody InternetTransferDTO internetTransferDTO) {
        EnquiryDTO enquiryDTO = decodeRequestParams(internetTransferDTO, EnquiryDTO.class);
        log.debug("REST request to create enquiryDTO : {}", enquiryDTO);
        EnquiryDTO result = enquiryService.createEnquiry(enquiryDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get-enquiries")
    public ResponseEntity<List<?>> getListEnquiry(@RequestBody InternetTransferDTO internetTransferDTO) {
        EnquiryCriteria criteria = decodeRequestParams(internetTransferDTO, EnquiryCriteria.class);
        log.debug("REST request to retrieve all enquiries by criteria: {}", criteria);
        List<EnquiryDTO> result = enquiryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update-enquiry")
    public ResponseEntity<EnquiryDTO> updateEnquiry(@RequestBody InternetTransferDTO internetTransferDTO) {
        EnquiryDTO enquiryDTO = decodeRequestParams(internetTransferDTO, EnquiryDTO.class);
        log.debug("REST request to update enquiry by id: {}", enquiryDTO.getId());
        EnquiryDTO result = enquiryService.save(enquiryDTO);
        return ResponseEntity.ok().body(result);
    }

    public <T> T decodeRequestParams(InternetTransferDTO internetTransferDTO, Class<T> clazz) {
        String params = internetTransferDTO.getBase64String();
        String jsonStr = new String(Base64.getDecoder().decode(params));
        return JsonUtils.stringToObject(jsonStr, clazz);
    }
}
