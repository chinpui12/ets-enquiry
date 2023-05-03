package com.nus.iss.enquiry.service;

import com.nus.iss.enquiry.domain.Enquiry;
import com.nus.iss.enquiry.dto.EnquiryCriteria;
import com.nus.iss.enquiry.dto.EnquiryDTO;
import com.nus.iss.enquiry.mapper.EnquiryMapper;
import com.nus.iss.enquiry.repository.EnquiryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnquiryServiceTest {
    EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
    EnquiryMapper enquiryMapper = mock(EnquiryMapper.class);

    EnquiryService service = new EnquiryService(enquiryRepository, enquiryMapper);

    @Before
    public void setup() {


    }

    private List<Enquiry> mockListData() {
        List<Enquiry> records = new ArrayList<>();
        Enquiry enquiry1 = new Enquiry();
        enquiry1.setTitle("Test1");
        enquiry1.setMessage("TestMsg1");
        enquiry1.setSenderEmail("TestEmail@g.com");
        enquiry1.setSenderName("TestName1");
        enquiry1.setSenderContact("123456");
        enquiry1.setReadEmail(false);
        records.add(enquiry1);
        return records;
    }

    private EnquiryDTO mockListData2() {
        EnquiryDTO enquiry1 = new EnquiryDTO();
        enquiry1.setTitle("Test2");
        enquiry1.setMessage("TestMsg2");
        enquiry1.setSenderEmail("TestEmail2@g.com");
        enquiry1.setSenderName("TestName2");
        enquiry1.setSenderContact("123456");
        enquiry1.setReadEmail(true);
        return enquiry1;
    }

    @Test
    public void find() {

        List<Enquiry> records = mockListData();

        EnquiryCriteria criteria = new EnquiryCriteria();
        StringFilter title = new StringFilter();
        title.setEquals("Test1");
        BooleanFilter isRead = new BooleanFilter();
        isRead.setEquals(false);
        criteria.setTitle(title);
        criteria.setReadEmail(isRead);
        when(enquiryRepository.findByTitleContainsAndReadEmailFalse(ArgumentMatchers.any())).thenReturn(records);

        List<EnquiryDTO> result = service.findByCriteria(criteria);
        assertTrue(result != null);
    }

    @Test
    public void find2() {

        List<Enquiry> records = mockListData();

        EnquiryCriteria criteria = new EnquiryCriteria();
        StringFilter title = new StringFilter();
        title.setEquals("Test1");
        BooleanFilter isRead = new BooleanFilter();
        isRead.setEquals(true);
        criteria.setTitle(title);
        criteria.setReadEmail(isRead);
        when(enquiryRepository.findByTitleContainsAndReadEmailTrue(ArgumentMatchers.any())).thenReturn(records);

        List<EnquiryDTO> result = service.findByCriteria(criteria);
        assertTrue(result != null);
    }

    @Test
    public void find3() {

        List<Enquiry> records = mockListData();

        EnquiryCriteria criteria = new EnquiryCriteria();
        BooleanFilter isRead = new BooleanFilter();
        isRead.setEquals(false);
        criteria.setReadEmail(isRead);
        when(enquiryRepository.findByReadEmailFalse()).thenReturn(records);

        List<EnquiryDTO> result = service.findByCriteria(criteria);
        assertTrue(result != null);
    }

    @Test
    public void find4() {

        List<Enquiry> records = mockListData();

        EnquiryCriteria criteria = new EnquiryCriteria();
        BooleanFilter isRead = new BooleanFilter();
        isRead.setEquals(true);
        criteria.setReadEmail(isRead);
        when(enquiryRepository.findByReadEmailTrue()).thenReturn(records);

        List<EnquiryDTO> result = service.findByCriteria(criteria);
        assertTrue(result != null);
    }

    @Test
    public void find5() {

        List<Enquiry> records = mockListData();

        EnquiryCriteria criteria = new EnquiryCriteria();
        StringFilter title = new StringFilter();
        title.setEquals("Test1");
        criteria.setTitle(title);
        when(enquiryRepository.findByTitleContains(ArgumentMatchers.any())).thenReturn(records);

        List<EnquiryDTO> result = service.findByCriteria(criteria);
        assertTrue(result != null);
    }

    @Test
    public void find6() {

        List<Enquiry> records = mockListData();

        EnquiryCriteria criteria = new EnquiryCriteria();
        when(enquiryRepository.findAll()).thenReturn(records);

        List<EnquiryDTO> result = service.findByCriteria(criteria);
        assertTrue(result != null);
    }

    @Test
    public void create() {

        EnquiryDTO data = mockListData2();
        when(enquiryRepository.saveAndFlush(data)).thenReturn(data);

        EnquiryDTO result = service.createEnquiry(data);
        assertNull(result);
    }

    @Test
    public void update() {

        EnquiryDTO data = mockListData2();
        when(enquiryRepository.saveAndFlush(data)).thenReturn(data);

        EnquiryDTO result = service.save(data);
        assertNull(result);
    }
}