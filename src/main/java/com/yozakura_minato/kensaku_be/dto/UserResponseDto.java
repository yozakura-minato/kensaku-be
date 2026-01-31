package com.yozakura_minato.kensaku_be.dto;

/**
 * Response DTO for users
 */
public class UserResponseDto {

    // ============ ATTRIBUTES ============ //
    /**
     * ID of this user
     */
    private int userId;

    /**
     * Email of this user
     */
    private String email;

    /**
     * Display name of this user
     * (not unique)
     */
    private String displayName;

    // ============ GETTERS & SETTERS ============ //
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

}
