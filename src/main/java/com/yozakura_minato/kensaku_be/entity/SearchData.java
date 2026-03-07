package com.yozakura_minato.kensaku_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>Attributes</p>
 * <ul>
 *     <li>{@code dataId} - (int)</li>
 *     <li>{@code owner} - (Users)</li>
 *     <li>{@code searchPages} - (SearchPages)</li>
 *     <br>
 *     <li>{@code input1} - (String)</li>
 *     <li>{@code input2} - (String)</li>
 *     <li>{@code output1} - (String)</li>
 *     <li>{@code output2} - (String)</li>
 *     <br>
 *     <li>{@code createdDateTime} - (LocalDateTime)</li>
 *     <li>{@code lastModifiedDateTime} - (LocalDateTime)</li>
 *     <li>{@code isDeleted} - (boolean)</li>
 *     <li>{@code deletedDateTime} - (LocalDateTime)</li>
 * </ul>
 */
@Entity
@Table(name = "search_data")
@Getter
@Setter
public class SearchData {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int dataId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users owner;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private SearchPages searchPages;

    @Column(name = "input_1")
    private String input1;

    @Column(name = "input_2")
    private String input2;

    @Column(name = "output_1")
    private String output1;

    @Column(name = "output_2")
    private String output2;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;

    private LocalDateTime lastModifiedDateTime;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    private LocalDateTime deletedDateTime;

}
