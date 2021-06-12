package com.example.ec.explorecali.services;

import com.example.ec.explorecali.domain.Difficulty;
import com.example.ec.explorecali.domain.Region;
import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackagesRepository;
import com.example.ec.explorecali.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourServices {

    private TourRepository tourRepository;
    private TourPackagesRepository tourPackagesRepository;

    @Autowired
    public TourServices(TourRepository tourRepository, TourPackagesRepository tourPackagesRepository) {
        this.tourRepository = tourRepository;
        this.tourPackagesRepository = tourPackagesRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration,
                           String bullets, String keywords, String tourPackageName, Difficulty difficulty, Region region) {

        TourPackage tourPackage = tourPackagesRepository.findById(tourPackageName)
                .orElseThrow(() -> new RuntimeException ("Tour package does not exist " + tourPackageName));

        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficulty, region));
    }

    public long total() {
        return tourRepository.count();
    }
}
