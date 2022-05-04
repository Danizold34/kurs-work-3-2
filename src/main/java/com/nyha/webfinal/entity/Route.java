package com.nyha.webfinal.entity;

import java.sql.Time;

/**
 * Describes the entity Route
 *
 * @author Dovlet Jumaev
 */
public class Route extends Entity{
    private String station;
    private Time time;
    private double price;
    private Long trainNumber;

    public Route() {
    }

    public Route(String station, Time time, double price, Long trainNumber) {
        this.station = station;
        this.time = time;
        this.price = price;
        this.trainNumber = trainNumber;
    }

    public Long getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Long trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTime() {
        return time.toString().substring(0,5);
    }

    public Time getTimeTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        if (station == null) {
            if (route.station != null) {
                return false;
            }
        } else if (!station.equals(route.station)) {
            return false;
        }
        if (time == null) {
            if (route.time != null) {
                return false;
            }
        } else if (!time.equals(route.time)) {
            return false;
        }
        if (price != route.price) {
            return false;
        }
        if (trainNumber != route.trainNumber) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((station == null) ? 0 : station.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + (int)price;
        result = prime * result + ((trainNumber == null) ? 0 : trainNumber.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rout{");
        sb.append("station='").append(station).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", trainNumber=").append(trainNumber);
        sb.append('}');
        return sb.toString();
    }
}
