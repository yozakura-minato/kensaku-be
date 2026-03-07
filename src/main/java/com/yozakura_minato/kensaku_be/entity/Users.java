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
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code userId} - (int)</li>
 *     <br>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code displayName} - (String)</li>
 *     <li>{@code hashedPassword} - (String)</li>
 *     <br>
 *     <li>{@code createdDateTime} - (LocalDateTime)</li>
 *     <li>{@code lastModifiedDateTime} - (LocalDateTime)</li>
 *     <li>{@code isDeleted} - (boolean)</li>
 *     <li>{@code deletedDateTime} - (LocalDateTime)</li>
 *     <br>
 *     <li>{@code searchPages} - (List<SearchPages>)</li>
 *     <li>{@code searchData} - (List<SearchData>)</li>
 * </ul>
 */
@Entity
@Table(name = "users", indexes = @Index(columnList = "email"))
@Getter
@Setter
public class Users implements UserDetails {

    // ============ ATTRIBUTES ============ //
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
    private List<SearchPages> searchPages;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<SearchData> searchData;

    // ============ METHODS ============ //

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just a placeholder for method overriding.</p>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just only used for handling JWT tokens.</p>
     * @return <b><u>hashedPassword</u></b> - (String)
     */
    @Override
    public @Nullable String getPassword() {
        return hashedPassword;
    }

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just only used for handling JWT tokens.</p>
     * @return <b><u>email</u></b> - (String). <i>We use (<u>email</u>, hashedPassword) instead of default (username, hashedPassword).</i>
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just a placeholder for method overriding.</p>
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just a placeholder for method overriding.</p>
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just a placeholder for method overriding.</p>
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * @deprecated
     * <p><b><u>DO NOT</u></b> use this method. It's just a placeholder for method overriding.</p>
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
