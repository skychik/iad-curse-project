package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "achievement", schema = "public")
@ToString
@Getter
@Setter
public class Achievement implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@SequenceGenerator(name = "achievement_id_seq", sequenceName = "achievement_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private String type;

//	@OneToMany(mappedBy="achievement", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<AchievementReceiving> achievementReceivings;
}
