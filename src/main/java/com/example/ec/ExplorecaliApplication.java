package com.example.ec;

import com.example.ec.domain.Region;
import com.example.ec.domain.Difficulty;
import com.example.ec.services.TourPackageService;
import com.example.ec.services.TourServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner {

    @Value("${ec.importfile}")
    private String importFile;

    @Autowired
    private TourPackageService tourPackageService;

    @Autowired
    private TourServices tourServices;

    public static void main(String[] args) {
        SpringApplication.run(ExplorecaliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createTourPackages();
        long numOfPackages = tourPackageService.total();

        createTours("ExploreCalifornia.json");
        long numOfTours = tourServices.total();
    }

    private void createTourPackages() {
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KS", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }

    private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(importedTour ->
                tourServices.createTour(importedTour.getTitle(),
                        importedTour.getDescription(),
                        importedTour.getBlurb(),
                        importedTour.getPrice(),
                        importedTour.getLength(),
                        importedTour.getBullets(),
                        importedTour.getKeywords(),
                        importedTour.getPackageType(),
                        importedTour.getDifficulty(),
                        importedTour.getRegion()));
    }

    private static class TourFromFile {

        private String packageType, title, description, blurb, price, length,
                bullets, keywords, difficulty, region;

        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY)
                    .readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
        }

        protected TourFromFile() {}

        public String getPackageType() {
            return packageType;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getBlurb() {
            return blurb;
        }

        public Integer getPrice() {
            return Integer.parseInt(price);
        }

        public String getLength() {
            return length;
        }

        public String getBullets() {
            return bullets;
        }

        public String getKeywords() {
            return keywords;
        }

        public Difficulty getDifficulty() {
            return Difficulty.valueOf(difficulty);
        }

        public Region getRegion() {
            return Region.findByLabel(region);
        }
    }
}
