package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "achievement")
public class Achievement implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false)
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy="id_achievement", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<AchievementReceiving> achievementReceivings;
	public Set<AchievementReceiving> getAchievementReceivings() {
		return achievementReceivings;
	}
	public void setAchievementReceivings(Set<AchievementReceiving> achievementReceivings) {
		this.achievementReceivings = achievementReceivings;
	}
}
