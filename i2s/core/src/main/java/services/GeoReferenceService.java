package services;

import models.Envolvente;
import models.FatorRisco;
import models.Rating;

public class GeoReferenceService implements GeoReferenceServiceInterface {

    @Override
    public Rating getGeoRating(Envolvente envolvente, FatorRisco fatorRisco, Double latitude, Double longitude) {
        return Rating.MEDIUM;
    }

}
