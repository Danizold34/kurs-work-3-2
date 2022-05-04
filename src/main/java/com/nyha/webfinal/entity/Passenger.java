package com.nyha.webfinal.entity;

/**
 * Describes the entity Passenger
 *
 * @author Dovlet Jumaev
 */
public class Passenger extends Entity{
    private String name;
    private String lastName;
    private String passportNumber;
    private String phoneNumber;
    private Long userId;

    public Passenger() {
    }

    public Passenger(String name, String lastName, String passportNumber, String phoneNumber, Long userId) {
        this.name = name;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passenger passenger = (Passenger) o;
        if (name == null) {
            if (passenger.name != null) {
                return false;
            }
        } else if (!name.equals(passenger.name)) {
            return false;
        }
        if (lastName == null) {
            if (passenger.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(passenger.lastName)) {
            return false;
        }
        if (passportNumber == null) {
            if (passenger.passportNumber != null) {
                return false;
            }
        } else if (!passportNumber.equals(passenger.passportNumber)) {
            return false;
        }
        if (phoneNumber == null) {
            if (passenger.phoneNumber != null) {
                return false;
            }
        } else if (!phoneNumber.equals(passenger.phoneNumber)) {
            return false;
        }
        if (userId == null) {
            if (passenger.userId != null) {
                return false;
            }
        } else if (!userId.equals(passenger.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Passenger{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
