package com.github.rsoi.repository;

import com.github.rsoi.domain.TelephoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneInfo, Long> {

}
