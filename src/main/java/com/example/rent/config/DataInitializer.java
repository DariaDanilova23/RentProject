package com.example.rent.config;

import org.springframework.boot.ApplicationRunner;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Configuration;

import com.example.rent.models.Typing;

import com.example.rent.repository.TypingRepository;
import java.util.List;

@Configuration
public class DataInitializer implements ApplicationRunner{
    private final TypingRepository typingRepository;

    public DataInitializer(TypingRepository typeOfRoomRepository) {
        this.typingRepository = typeOfRoomRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (typingRepository.count() == 0) {
            // Инициализация данных для Typing
            List<Typing> initialRoomData = Arrays.asList(
                new Typing ("Подъезд"),
                new Typing ("Этаж"),
                new Typing ("Лестница"),
                new Typing ("Коридор"),
                new Typing ("Квартира"),
                new Typing ("Санузел"),
                new Typing ("Ванная"),
                new Typing ("Кухня"),
                new Typing ("Комната"),
                new Typing ("Балкон"),
                new Typing ("Лифт")
        );
        // Сохранение данных в репозиторий TypingRepository
        typingRepository.saveAll(initialRoomData);
        }
    }
    
}
