package com.nyha.webfinal.entity;

import java.util.Collections;
import java.util.List;

/**
 * Describes the entity Train
 *
 * @author Dovlet Jumaev
 */
public class Train extends Entity{
    private int numberOfSeats;
    private List<Route> routes;

    public Train() {
    }

    public Train(int numberOfSeats, List<Route> routes) {
        this.numberOfSeats = numberOfSeats;
        this.routes = routes;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Route> getRoutes() {
        return Collections.unmodifiableList(routes);
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Train train = (Train) o;
        if (train.numberOfSeats != numberOfSeats) {
            return false;
        }
        if (routes == null) {
            if (train.routes != null) {
                return false;
            }
        } else if (!routes.equals(train.routes)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((routes == null) ? 0 : routes.hashCode());
        result = prime * result + numberOfSeats;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Train{");
        sb.append("numberOfSeats='").append(numberOfSeats).append('\'');
        sb.append(", routes=").append(routes);
        sb.append('}');
        return sb.toString();
    }
}
