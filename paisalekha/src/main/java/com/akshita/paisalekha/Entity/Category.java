package com.akshita.paisalekha.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "categories",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "name"})
    }
)

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    private String description;
    private Boolean isDefault = false; 
    private LocalDateTime createdAt = LocalDateTime.now();

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Long categoryId, User user, String name, String description, LocalDateTime createdAt, Boolean isDefault) {
		super();
		this.categoryId = categoryId;
		this.user = user;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.isDefault= isDefault;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", user=" + user + ", name=" + name + ", description="
				+ description + ", isDefault=" + isDefault + ", createdAt=" + createdAt + "]";
	}
	
	@PrePersist
	public void prePersist() {

	    if (this.createdAt == null) {
	        this.createdAt = LocalDateTime.now();
	    }

	    if (this.isDefault == null) {
	        this.isDefault = false;
	    }
	}

	

	
    
    
}
