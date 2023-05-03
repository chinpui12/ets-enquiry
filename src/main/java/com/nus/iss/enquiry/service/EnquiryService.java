package com.nus.iss.enquiry.service;


import com.nus.iss.enquiry.domain.Enquiry;
import com.nus.iss.enquiry.dto.EnquiryCriteria;
import com.nus.iss.enquiry.dto.EnquiryDTO;
import com.nus.iss.enquiry.mapper.EnquiryMapper;
import com.nus.iss.enquiry.repository.EnquiryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yanbintan
 */
@Service
@Transactional
public class EnquiryService {

    private final Logger log = LoggerFactory.getLogger(EnquiryService.class);

    private final EnquiryRepository enquiryRepository;

    private final EnquiryMapper enquiryMapper;

    public EnquiryService(EnquiryRepository enquiryRepository, EnquiryMapper enquiryMapper) {
        this.enquiryRepository = enquiryRepository;
        this.enquiryMapper = enquiryMapper;
    }

    public EnquiryDTO createEnquiry(EnquiryDTO enquiryDTO) {
        enquiryDTO.setReadEmail(false);
        Enquiry enquiry = enquiryMapper.toEntity(enquiryDTO);
        return enquiryMapper.toDto(enquiryRepository.saveAndFlush(enquiry));
    }

    public EnquiryDTO save(EnquiryDTO enquiryDTO) {
        enquiryDTO.setReadEmail(true);
        Enquiry enquiry = enquiryMapper.toEntity(enquiryDTO);
        return enquiryMapper.toDto(enquiryRepository.saveAndFlush(enquiry));
    }

    public List<EnquiryDTO> findByCriteria(EnquiryCriteria criteria) {
        if (criteria.getReadEmail() != null && criteria.getReadEmail().getEquals().equals(true) && criteria.getTitle() != null) {
            return enquiryMapper.toDto(enquiryRepository.findByTitleContainsAndReadEmailTrue(criteria.getTitle().getContains()));
        } else if (criteria.getReadEmail() != null && criteria.getReadEmail().getEquals().equals(false) && criteria.getTitle() != null) {
            return enquiryMapper.toDto(enquiryRepository.findByTitleContainsAndReadEmailFalse(criteria.getTitle().getContains()));
        } else if (criteria.getTitle() != null) {
            return enquiryMapper.toDto(enquiryRepository.findByTitleContains(criteria.getTitle().getContains()));
        } else if (criteria.getReadEmail() != null && criteria.getReadEmail().getEquals().equals(true)) {
            return enquiryMapper.toDto(enquiryRepository.findByReadEmailTrue());
        } else if (criteria.getReadEmail() != null && criteria.getReadEmail().getEquals().equals(false)) {
            return enquiryMapper.toDto(enquiryRepository.findByReadEmailFalse());
        }
        return enquiryMapper.toDto(enquiryRepository.findAll());
    }
}
