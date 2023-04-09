package com.github.rsoi.repository;

import com.github.rsoi.domain.TelephoneInfo;
import com.github.rsoi.repository.RepositoryFunctional;
import com.github.rsoi.repository.TelephoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RepositoryFunctionalTest {

    @Mock
    private TelephoneRepository telephoneRepository;

    @InjectMocks
    private RepositoryFunctional repositoryFunctional;

    @Test
    public void showTelephonesTest(){
        List<TelephoneInfo> telephoneInfos = new ArrayList<>();
        when(telephoneRepository.findAll()).thenReturn(telephoneInfos);

        List<TelephoneInfo> telephoneInfosActual = repositoryFunctional.showTelephones();

        assertThat(telephoneInfosActual).isEqualTo(telephoneInfos);
        verify(telephoneRepository,times(1)).findAll();
    }

    @Test
    public void deleteTelephoneTest(){
        long id = 1L;

        doNothing().when(telephoneRepository).deleteById(id);

        repositoryFunctional.deleteTelephone(id);

        verify(telephoneRepository,times(1)).deleteById(id);
    }

    @Test
    public void existsByIdTrueTest(){
        long id = 1L;

        when(telephoneRepository.existsById(id)).thenReturn(true);

        boolean isExists = repositoryFunctional.existsById(id);

        assertThat(isExists).isTrue();
        verify(telephoneRepository,times(1)).existsById(id);
    }

    @Test
    public void existsByIdFalseTest(){
        long id = 1L;

        when(telephoneRepository.existsById(id)).thenReturn(false);

        boolean isExists = repositoryFunctional.existsById(id);

        assertThat(isExists).isFalse();
        verify(telephoneRepository,times(1)).existsById(id);
    }

    @Test
    public void findByIdTest(){
        long id = 1L;
        TelephoneInfo telephoneInfo = new TelephoneInfo();
        when(telephoneRepository.findById(id)).thenReturn(java.util.Optional.of(telephoneInfo));

        TelephoneInfo telephoneInfoActual = repositoryFunctional.findById(id);

        assertThat(telephoneInfoActual).isEqualTo(telephoneInfo);
        verify(telephoneRepository,times(1)).findById(id);
    }
}