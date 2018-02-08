package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "achievement_receiving")
public class AchievementReceiving implements Serializable {
	@ManyToOne
	@JoinColumn(name = "id_achievement", nullable = false)
	private long achievementId;
	public long getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(long achievementId) {
		this.achievementId = achievementId;
	}

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "date", nullable = false)
	private Timestamp date;
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
