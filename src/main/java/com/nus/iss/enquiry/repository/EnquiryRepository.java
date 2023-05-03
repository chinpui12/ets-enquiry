package com.nus.iss.enquiry.repository;

import com.nus.iss.enquiry.domain.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanbintan
 */
@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    public List<Enquiry> findByTitleContainsAndReadEmailTrue(String title);
    public List<Enquiry> findByTitleContainsAndReadEmailFalse(String title);
    public List<Enquiry> findByTitleContains(String title);
    public List<Enquiry> findByReadEmailTrue();
    public List<Enquiry> findByReadEmailFalse();
}
