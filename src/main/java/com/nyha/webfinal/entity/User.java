package com.nyha.webfinal.entity;

/**
 * Describes the entity User
 *
 * @author Dovlet Jumaev
 */
public class User extends Entity{
    public enum Role {
        ADMIN, USER, GUEST
    }

    private String username;
    private String email;
    private Role role;

    public User() {
    }

    public User(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (email == null) {
            if (user.email != null) {
                return false;
            }
        } else if (!email.equals(user.email)) {
            return false;
        }
        if (username == null) {
            if (user.username != null) {
                return false;
            }
        } else if (!username.equals(user.username)) {
            return false;
        }
        if (role == null) {
            if (user.role != null) {
                return false;
            }
        } else if (role != user.role) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
