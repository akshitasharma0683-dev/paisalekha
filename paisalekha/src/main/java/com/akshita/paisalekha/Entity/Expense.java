package com.akshita.paisalekha.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentMethod;

    private String description;

    @Column(nullable = false)
    private LocalDate expenseDate;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

	public Long getExpenseId() {
		return expenseId;
	}

	@PrePersist
	public void prePersist() {

	    if (this.createdAt == null) {
	        this.createdAt = LocalDateTime.now();
	    }

	    if (this.expenseDate == null) {
	        this.expenseDate = LocalDate.now();
	    }
	    
	}
	
	@PreUpdate
	public void preUpdate() {
	    this.updatedAt = LocalDateTime.now();
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense(Long expenseId, User user, Category category, Double amount, String paymentMethod,
			String description, LocalDate expenseDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.expenseId = expenseId;
		this.user = user;
		this.category = category;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.description = description;
		this.expenseDate = expenseDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", user=" + user + ", category=" + category + ", amount=" + amount
				+ ", paymentMethod=" + paymentMethod + ", description=" + description + ", expenseDate=" + expenseDate
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
    
    
}