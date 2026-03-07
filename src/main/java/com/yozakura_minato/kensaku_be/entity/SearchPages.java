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
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code pageId} - (int)</li>
 *     <li>{@code owner} - (Users)</li>
 *     <br>
 *     <li>{@code pageName} - (String)</li>
 *     <li>{@code description} - (String)</li>
 *     <li>{@code searchInstruction} - (String)</li>
 *     <li>{@code input1Label} - (String)</li>
 *     <li>{@code input2Label} - (String)</li>
 *     <li>{@code output1Label} - (String)</li>
 *     <li>{@code output1Label} - (String)</li>
 *     <br>
 *     <li>{@code createdDateTime} - (LocalDateTime)</li>
 *     <li>{@code lastModifiedDateTime} - (LocalDateTime)</li>
 *     <li>{@code isDeleted} - (boolean)</li>
 *     <li>{@code deletedDateTime} - (LocalDateTime)</li>
 *     <br>
 *     <li>{@code searchData} - (List<SearchData>)</li>
 * </ul>
 */
@Entity
@Table(name = "search_pages")
@Getter
@Setter
public class SearchPages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users owner;

    @Column(unique = true, nullable = false)
    private String pageName;

    private String description;

    private String searchInstruction;

    @Column(name = "input_1_label")
    private String input1Label;

    @Column(name = "input_2_label")
    private String input2Label;

    @Column(name = "output_1_label")
    private String output1Label;

    @Column(name = "output_2_label")
    private String output2Label;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;

    private LocalDateTime lastModifiedDateTime;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    private LocalDateTime deletedDateTime;

    @OneToMany(mappedBy = "searchPages", fetch = FetchType.EAGER)
    private List<SearchData> searchData;

}
