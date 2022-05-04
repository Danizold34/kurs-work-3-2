package com.nyha.webfinal.entity;


import java.sql.Timestamp;

/**
 * Describes the entity Ticket
 *
 * @author Dovlet Jumaev
 */
public class Ticket extends Entity {
    private Passenger passenger;
    private Long trainId;
    private String departureStation;
    private String arrivalStation;
    private int seat;
    private Timestamp departureDate;
    private Timestamp arrivalDate;
    private double price;

    public Ticket() {
    }

    public Ticket(Passenger passenger, Long trainId, String departureStation, String arrivalStation, int seat, Timestamp departureDate, Timestamp arrivalDate, double price) {
        this.passenger = passenger;
        this.trainId = trainId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.seat = seat;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getDepartureDate() {
        return departureDate.toString().substring(0,16);
    }

    public Timestamp getDepartureDateDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate.toString().substring(0,16);
    }

    public Timestamp getArrivalDateDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
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
        Ticket ticket = (Ticket) o;
        if (passenger == null) {
            if (ticket.passenger != null) {
                return false;
            }
        } else if (!passenger.equals(ticket.passenger)) {
            return false;
        }
        if (trainId == null) {
            if (ticket.trainId != null) {
                return false;
            }
        } else if (!trainId.equals(ticket.trainId)) {
            return false;
        }
        if (departureStation == null) {
            if (ticket.departureStation != null) {
                return false;
            }
        } else if (!departureStation.equals(ticket.departureStation)) {
            return false;
        }
        if (arrivalStation == null) {
            if (ticket.arrivalStation != null) {
                return false;
            }
        } else if (!arrivalStation.equals(ticket.arrivalStation)) {
            return false;
        }
        if (seat != ticket.seat) {
            return false;
        }
        if (departureDate == null) {
            if (ticket.departureDate != null) {
                return false;
            }
        } else if (!departureDate.equals(ticket.departureDate)) {
            return false;
        }
        if (arrivalDate == null) {
            if (ticket.arrivalDate != null) {
                return false;
            }
        } else if (!arrivalDate.equals(ticket.arrivalDate)) {
            return false;
        }
        if (price != ticket.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((departureStation == null) ? 0 : departureStation.hashCode());
        result = prime * result + ((arrivalStation == null) ? 0 : arrivalStation.hashCode());
        result = prime * result + ((passenger == null) ? 0 : passenger.hashCode());
        result = prime * result + ((trainId == null) ? 0 : trainId.hashCode());
        result = prime * result + seat;
        result = prime * result + (int)price;
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ticket{");
        sb.append(passenger);
        sb.append(", trainId='").append(trainId).append('\'');
        sb.append(", departureStation='").append(departureStation).append('\'');
        sb.append(", arrivalStation='").append(arrivalStation).append('\'');
        sb.append(", seat='").append(seat).append('\'');
        sb.append(", departureDate='").append(departureDate).append('\'');
        sb.append(", arrivalDate='").append(arrivalDate).append('\'');
        sb.append(", price='").append(price);
        sb.append('}');
        return sb.toString();
    }
}
