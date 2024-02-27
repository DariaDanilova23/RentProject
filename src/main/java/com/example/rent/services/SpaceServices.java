package com.example.rent.services;

import com.example.rent.models.Arendator;
import com.example.rent.models.Space;
import com.example.rent.models.Typing;
import com.example.rent.repository.SpaceRepository;

import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class SpaceServices {
    private final SpaceRepository spaceRepository;

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    public Optional<Space> getSpaceById(long spaceId) {
        return spaceRepository.findById(spaceId);
    }

    public void saveSpace(Space space) {
        spaceRepository.save(space);
    }

    public void deleteSpace(long spaceId) {
        spaceRepository.deleteById(spaceId);
    }

    public Arendator getArendatorForSpace(long spaceId) {
        Optional<Space> space = spaceRepository.findById(spaceId);
        return space.map(Space::getArendator).orElse(null);
    }

    public Typing getTypingForSpace(long spaceId) {
        Optional<Space> space = spaceRepository.findById(spaceId);
        return space.map(Space::getTyping).orElse(null);
    }

    public void updateSpace(long spaceId, Space updatedSpace) {
        Optional<Space> existingSpace = spaceRepository.findById(spaceId);
        existingSpace.ifPresent(space -> {
            space.setTyping(updatedSpace.getTyping());
            space.setInsert_id(updatedSpace.getInsert_id());
            space.setBase_X(updatedSpace.getBase_X());
            space.setBase_Y(updatedSpace.getBase_Y());
            space.setBase_Z(updatedSpace.getBase_Z());
            space.setSize_X(updatedSpace.getSize_X());
            space.setSize_Y(updatedSpace.getSize_Y());
            space.setSize_Z(updatedSpace.getSize_Z());
            space.setArendator(updatedSpace.getArendator());
            space.setPrice(updatedSpace.getPrice());
            space.setComment(updatedSpace.getComment());
            if (updatedSpace.getInsert_id() != null) {
                Optional<Space> insertSpaceOptional = spaceRepository.findById(updatedSpace.getInsert_id().getId());
                Space insertSpace = insertSpaceOptional.orElse(null);
                space.setInsert_id(insertSpace);
            }
            saveSpace(space);
        });
    }
}
