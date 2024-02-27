package com.example.rent.services;

import com.example.rent.models.Space;
import com.example.rent.models.Typing;
import com.example.rent.repository.TypingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

@RequiredArgsConstructor
public class TypingServices {
    private final TypingRepository typingRepository;
    
    public List<Typing> getAllTypesOfSpaces() {
        return typingRepository.findAll();
    }

    public Optional<Typing> getTypeOfSpaceById(long typeId) {
        return typingRepository.findById(typeId);
    }

    public void saveTypeOfSpace(Typing typeOfRoom) {
        typingRepository.save(typeOfRoom);
    }

    public void deleteTypeOfSpace(long typeId) {
        typingRepository.deleteById(typeId);
    }

    public List<Space> getSpacesForTyping(long typeId) {
        Optional<Typing> typeOfRoom = typingRepository.findById(typeId);
        return typeOfRoom.map(Typing::getSpaces).orElse(null);
    }
}
