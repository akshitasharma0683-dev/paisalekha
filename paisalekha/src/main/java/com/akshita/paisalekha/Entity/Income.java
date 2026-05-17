package com.akshita.paisalekha.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "incomes")

public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double amount;

    private String source;

    @Column(nullable = false)
    private LocalDate incomeDate;

    private LocalDateTime createdAt = LocalDateTime.now();
    
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {

        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }

        if (this.incomeDate == null) {
            this.incomeDate = LocalDate.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
    
	public Long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDate getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Income() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Income(Long incomeId, User user, Double amount, String source, LocalDate incomeDate,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.incomeId = incomeId;
		this.user = user;
		this.amount = amount;
		this.source = source;
		this.incomeDate = incomeDate;
		this.createdAt = createdAt;
		this.updatedAt=updatedAt;
	}

	@Override
	public String toString() {
		return "Income [incomeId=" + incomeId + ", user=" + user + ", amount=" + amount + ", source=" + source
				+ ", incomeDate=" + incomeDate + ", createdAt=" + createdAt + "]";
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	
    
}
