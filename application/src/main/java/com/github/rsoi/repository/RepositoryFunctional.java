package com.github.rsoi.repository;

import com.github.rsoi.domain.TelephoneInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepositoryFunctional {
    private final TelephoneRepository telephoneRepository;

    @Transactional
    public List<TelephoneInfo> showTelephones() {
        return telephoneRepository.findAll();
    }

    @Transactional
    public void deleteTelephone(long id) {
        telephoneRepository.deleteById(id);
    }

    @Transactional
    public boolean existsById(long id) {
        return telephoneRepository.existsById(id);
    }

    @Transactional
    public TelephoneInfo findById(long id) {return telephoneRepository.findById(id).orElseThrow();
    }
    @Transactional
    public void savePhone(TelephoneInfo telephoneInfo){
        log.info("Saving new {}", telephoneInfo);
        telephoneRepository.save(telephoneInfo);
    }
}
