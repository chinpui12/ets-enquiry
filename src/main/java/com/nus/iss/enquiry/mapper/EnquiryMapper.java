package com.nus.iss.enquiry.mapper;


import com.nus.iss.enquiry.domain.Enquiry;
import com.nus.iss.enquiry.dto.EnquiryDTO;
import org.mapstruct.Mapper;

/**
 * @author yanbintan
 */
@Mapper(componentModel = "spring")
public interface EnquiryMapper extends AbstractEntityMapper<EnquiryDTO, Enquiry> {
}
