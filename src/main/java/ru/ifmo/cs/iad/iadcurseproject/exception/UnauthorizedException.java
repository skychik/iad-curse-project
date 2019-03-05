package ru.ifmo.cs.iad.iadcurseproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "UnauthorizedException")
public class UnauthorizedException extends RuntimeException {
}
