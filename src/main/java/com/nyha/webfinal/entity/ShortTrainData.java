package com.nyha.webfinal.entity;

/**
 * Describes the entity ShortTrainData
 *
 * @author Dovlet Jumaev
 */
public class ShortTrainData {
    private Long trainId;
    private String departureStation;
    private String arrivalStation;
    private String departureTime;
    private String arrivalTime;
    private double price;

    public ShortTrainData() {
    }

    public ShortTrainData(Long trainId, String departureStation, String arrivalStation, String departureTime, String arrivalTime, double price) {
        this.trainId = trainId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
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
        ShortTrainData shortTrainData = (ShortTrainData) o;
        if (trainId == null) {
            if (shortTrainData.trainId != null) {
                return false;
            }
        } else if (!trainId.equals(shortTrainData.trainId)) {
            return false;
        }
        if (departureStation == null) {
            if (shortTrainData.departureStation != null) {
                return false;
            }
        } else if (!departureStation.equals(shortTrainData.departureStation)) {
            return false;
        }
        if (arrivalStation == null) {
            if (shortTrainData.arrivalStation != null) {
                return false;
            }
        } else if (!arrivalStation.equals(shortTrainData.arrivalStation)) {
            return false;
        }
        if (departureTime == null) {
            if (shortTrainData.departureTime != null) {
                return false;
            }
        } else if (!departureTime.equals(shortTrainData.departureTime)) {
            return false;
        }
        if (arrivalTime == null) {
            if (shortTrainData.arrivalTime != null) {
                return false;
            }
        } else if (!arrivalTime.equals(shortTrainData.arrivalTime)) {
            return false;
        }
        if (price != shortTrainData.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((trainId == null) ? 0 : trainId.hashCode());
        result = prime * result + ((departureStation == null) ? 0 : departureStation.hashCode());
        result = prime * result + (int)price;
        result = prime * result + ((arrivalStation == null) ? 0 : arrivalStation.hashCode());
        result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
        result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShortTrainData{");
        sb.append("trainId='").append(trainId).append('\'');
        sb.append(", departureStation='").append(departureStation).append('\'');
        sb.append(", arrivalStation='").append(arrivalStation).append('\'');
        sb.append(", departureTime='").append(departureTime).append('\'');
        sb.append(", arrivalTime='").append(arrivalTime).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
