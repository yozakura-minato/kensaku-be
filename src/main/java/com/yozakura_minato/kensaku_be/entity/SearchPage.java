package com.yozakura_minato.kensaku_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity for search pages
 */
@Entity
@Table(name = "search_pages")
public class SearchPage {

    // ============ ATTRIBUTES ============ //
    /**
     * ID of this page
     * (auto generated)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pageId;

    /**
     * User ID of the owner of this page
     * (required)
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    /**
     * Name of this page
     * (unique, required)
     */
    @Column(unique = true, nullable = false)
    private String pageName;

    /**
     * Description of this page
     */
    private String description;

    /**
     * Instruction for searching in this page
     */
    private String searchInstruction;

    /**
     * Label of the fist input field
     */
    @Column(name = "input_1_label")
    private String input1Label;

    /**
     * Label of the second input field
     */
    @Column(name = "input_2_label")
    private String input2Label;

    /**
     * Label of the fist output field
     */
    @Column(name = "output_1_label")
    private String output1Label;

    /**
     * Label of the second output field
     */
    @Column(name = "output_2_label")
    private String output2Label;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private LocalDateTime deletedDateTime;

    @OneToMany(mappedBy = "searchPage", fetch = FetchType.EAGER)
    private List<SearchData> searchData;

    // ============ GETTERS & SETTERS ============ //
    public int getPageId() {
        return pageId;
    }
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getPageName() {
        return pageName;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSearchInstruction() {
        return searchInstruction;
    }
    public void setSearchInstruction(String searchInstruction) {
        this.searchInstruction = searchInstruction;
    }

    public String getInput1Label() {
        return input1Label;
    }
    public void setInput1Label(String input1Label) {
        this.input1Label = input1Label;
    }

    public String getInput2Label() {
        return input2Label;
    }
    public void setInput2Label(String input2Label) {
        this.input2Label = input2Label;
    }

    public String getOutput1Label() {
        return output1Label;
    }
    public void setOutput1Label(String output1Label) {
        this.output1Label = output1Label;
    }

    public String getOutput2Label() {
        return output2Label;
    }
    public void setOutput2Label(String output2Label) {
        this.output2Label = output2Label;
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

    public List<SearchData> getSearchData() {
        return searchData;
    }
    public void setSearchData(List<SearchData> searchData) {
        this.searchData = searchData;
    }

}
