package com.example.ec.explorecali.services;

import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackagesService {

    private TourPackagesRepository tourPackagesRepository;

    @Autowired
    public TourPackagesService(TourPackagesRepository tourPackagesRepository) {
        this.tourPackagesRepository = tourPackagesRepository;
    }

    public TourPackage createTourPackage(String code, String name) {

        return tourPackagesRepository.findById(code)
                .orElse(tourPackagesRepository.save(new TourPackage(code, name)));
    }

    public Iterable<TourPackage> lookup() {
        return tourPackagesRepository.findAll();
    }

    public long count() {
        return tourPackagesRepository.count();
    }
}
