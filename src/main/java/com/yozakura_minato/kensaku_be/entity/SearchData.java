package com.yozakura_minato.kensaku_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

/**
 * Entity for search data in search page
 */
@Entity
@Table(name = "search_data")
public class SearchData {

    // ============ ATTRIBUTES ============ //
    /**
     * ID of this data
     * (auto generated)
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int dataId;

    /**
     * User ID of the owner of this data
     * (required)
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    /**
     * Page ID of the search page of this data
     * (required)
     */
    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private SearchPage searchPage;

    /**
     * Value of the fist input field
     */
    @Column(name = "input_1")
    private String input1;

    /**
     * Value of the second input field
     */
    @Column(name = "input_2")
    private String input2;

    /**
     * Value of the fist output field
     */
    @Column(name = "output_1")
    private String output1;

    /**
     * Value of the second output field
     */
    @Column(name = "output_2")
    private String output2;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private LocalDateTime deletedDateTime;

    // ============ GETTERS & SETTERS ============ //
    public int getDataId() {
        return dataId;
    }
    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public SearchPage getSearchPage() {
        return searchPage;
    }
    public void setSearchPage(SearchPage searchPage) {
        this.searchPage = searchPage;
    }

    public String getInput1() {
        return input1;
    }
    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }
    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getOutput1() {
        return output1;
    }
    public void setOutput1(String output1) {
        this.output1 = output1;
    }

    public String getOutput2() {
        return output2;
    }
    public void setOutput2(String output2) {
        this.output2 = output2;
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

}
