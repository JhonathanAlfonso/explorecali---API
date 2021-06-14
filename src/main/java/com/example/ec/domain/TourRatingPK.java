package com.example.ec.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TourRatingPK implements Serializable {

    @ManyToOne
    private Tour tour;

    @Column(insertable = false, updatable = false, nullable = false)
    private Integer customerId;

    public TourRatingPK(Tour tour, Integer customerId) {
        this.tour = tour;
        this.customerId = customerId;
    }

    protected TourRatingPK() {}

    public Tour getTour() {
        return tour;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourRatingPK that = (TourRatingPK) o;
        return Objects.equals(tour, that.tour) && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        int result = tour.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TourRatingPK{" +
                "tour=" + tour +
                ", customerId=" + customerId +
                '}';
    }
}
