package org.authentication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "token", schema = "user_schema")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenID;
    @Column(name = "token_value")
    private String tokenValue;
    @Column(name = "role")
    private String role;
    @Column(name = "expired")
    private boolean expired;
    @Column(name = "revoked")
    private boolean revoked;
    @Column(name = "username")
    private String username;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Token() {
    }

    public Token(int tokenID, String tokenValue, String role, boolean expired, boolean revoked, String username, Date lastUpdate) {
        this.tokenID = tokenID;
        this.tokenValue = tokenValue;
        this.role = role;
        this.expired = expired;
        this.revoked = revoked;
        this.username = username;
        this.lastUpdate = lastUpdate;
    }

    public Token(String tokenValue, String role, boolean expired, boolean revoked, String username, Date lastUpdate) {
        this.tokenValue = tokenValue;
        this.role = role;
        this.expired = expired;
        this.revoked = revoked;
        this.username = username;
        this.lastUpdate = lastUpdate;
    }

    public int getTokenID() {
        return tokenID;
    }

    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String token) {
        this.tokenValue = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
