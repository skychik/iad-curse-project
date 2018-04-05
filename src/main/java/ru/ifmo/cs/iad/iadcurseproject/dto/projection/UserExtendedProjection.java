package ru.ifmo.cs.iad.iadcurseproject.dto.projection;

import java.sql.Timestamp;

public interface UserExtendedProjection extends UserProjection {
	String getFirstName();
	String getSurname();
	String getPatronymic();
	Timestamp getBirthDate();
	boolean getSex();
}
