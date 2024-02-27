package com.example.rent.services;

import com.example.rent.repository.ArendatorRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.rent.models.Arendator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor
public class ArendatorServices {
    private final ArendatorRepository arendatorRepository;

    public Arendator saveArendator(Arendator arendator) {
        return arendatorRepository.save(arendator);
    }

    public List<Arendator> getAllArendator() {
        return arendatorRepository.findAll();
    }

    public Arendator getArendatorById(long arendatorId) {
        return arendatorRepository.findById(arendatorId).orElse(null);
    }

    public void deleteArendator(long arendatorId) {
        arendatorRepository.deleteById(arendatorId);
    }

    public void updateArendator(Arendator updatedArendator) {
        // Проверка, существует ли арендатор в базе данных
        Arendator existingArendator = getArendatorById(updatedArendator.getId());
        
        if (existingArendator == null) {
            // Обработка ситуации, когда арендатор не найден
            throw new EntityNotFoundException("Arendator with id " + updatedArendator.getId() + " not found");
        }

        // Обновление данных арендатора
        existingArendator.setArendator_name(updatedArendator.getArendator_name());
        existingArendator.setArendator_adr(updatedArendator.getArendator_adr());
        existingArendator.setArendator_bank(updatedArendator.getArendator_bank());
        existingArendator.setArendator_telefon(updatedArendator.getArendator_telefon());

        // Сохранение обновленных данных в базе данных
        saveArendator(existingArendator);
    }
}
