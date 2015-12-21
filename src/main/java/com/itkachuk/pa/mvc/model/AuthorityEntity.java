package com.itkachuk.pa.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 12/17/2015 4:03 PM
 */
@Entity
@Table(name = "authorities", schema = "", catalog = "")
public class AuthorityEntity implements Serializable {
    private UserEntity username;
    private String authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    @Id
    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorityEntity that = (AuthorityEntity) o;

        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}
