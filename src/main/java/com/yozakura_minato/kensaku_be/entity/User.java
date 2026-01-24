package com.yozakura_minato.kensaku_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", indexes = @Index(columnList = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    private String email;
    private String displayName;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private LocalDateTime deletedDateTime;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<SearchPage> searchPages;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<SearchData> searchData;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getDeletedDateTime() {
        return deletedDateTime;
    }

    public void setDeletedDateTime(LocalDateTime deletedDateTime) {
        this.deletedDateTime = deletedDateTime;
    }

    public List<SearchPage> getSearchPages() {
        return searchPages;
    }

    public void setSearchPages(List<SearchPage> searchPages) {
        this.searchPages = searchPages;
    }

    public List<SearchData> getSearchData() {
        return searchData;
    }

    public void setSearchData(List<SearchData> searchData) {
        this.searchData = searchData;
    }
}
