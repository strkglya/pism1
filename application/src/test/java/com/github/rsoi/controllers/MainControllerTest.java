package com.github.rsoi.controllers;
import com.github.rsoi.domain.TelephoneInfo;
import com.github.rsoi.repository.RepositoryFunctional;
import com.github.rsoi.service.TelephoneComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MainControllerTest {

    @Mock
    private RepositoryFunctional repositoryFunctional;

    @Mock
    private TelephoneComparator telephoneComparator;

    @Mock
    private Model model;

    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mainController = new MainController(repositoryFunctional, telephoneComparator);
    }

    @Test
    void homePage() {
        String result = mainController.homePage(model);
        assertEquals("homePage", result);
        verify(model).addAttribute("title", "Home page");
    }

    @Test
    void phones() {
        when(repositoryFunctional.showTelephones()).thenReturn(new ArrayList<>());
        String result = mainController.phones(model, null, null);
        assertEquals("telephonesPage", result);
        verify(model).addAttribute("telephonesPage", new ArrayList<>());
    }

    @Test
    void phoneAdd() {
        String result = mainController.phoneAdd("test", 4, 5.5, "Yes", 1000, "image");
        assertEquals("redirect:/telephonesPage", result);
        verify(repositoryFunctional).savePhone(new TelephoneInfo("test", 1000, 5.5, 4, true, "image"));
    }

    @Test
    void phoneEdit() {
        TelephoneInfo telephoneInfo = new TelephoneInfo("test", 1000, 5.5, 4, true, "image");
        when(repositoryFunctional.existsById(1L)).thenReturn(true);
        when(repositoryFunctional.findById(1L)).thenReturn(telephoneInfo);
        String result = mainController.phoneEdit(model, 1L);
        assertEquals("telephoneEditor", result);
        verify(model).addAttribute("telephones", Collections.singletonList(telephoneInfo));
    }

    @Test
    void phoneEditNotFound() {
        when(repositoryFunctional.existsById(1L)).thenReturn(false);
        String result = mainController.phoneEdit(model, 1L);
        assertEquals("redirect:/telephonesPage", result);
    }

    @Test
    void phoneUpdate() {
        TelephoneInfo telephoneInfo = new TelephoneInfo("test", 1000, 5.5, 4, true, "image");
        when(repositoryFunctional.findById(1L)).thenReturn(telephoneInfo);
        String result = mainController.phoneUpdate(1L, "newName", 8, 6.0, "No", 2000, "newImage");
        assertEquals("redirect:/telephonesPage", result);
        verify(repositoryFunctional).savePhone(telephoneInfo);
        assertEquals("newName", telephoneInfo.getNameOfTheTelephone());
        assertEquals(8, telephoneInfo.getAmountOfRAM());
        assertEquals(6.0, telephoneInfo.getSizeOfTheScreen());
        assertEquals(false, telephoneInfo.getSdCardIsAvailable());
        assertEquals(2000, telephoneInfo.getPriceOfTheTelephone());
        assertEquals("newImage", telephoneInfo.getImageOfTheTelephone());
    }

    @Test
    void phoneSearch() {
        List<TelephoneInfo> expectedTelephones = new ArrayList<>();
        when(telephoneComparator.searchForPhone(4, 5.5, "Yes", 1000, 2000)).thenReturn(expectedTelephones);
        String result = mainController.phoneSearch(4, 5.5, "Yes", 1000, 2000, model);
        assertEquals("searchPage", result);
        verify(model).addAttribute("telephones", expectedTelephones);
    }
}