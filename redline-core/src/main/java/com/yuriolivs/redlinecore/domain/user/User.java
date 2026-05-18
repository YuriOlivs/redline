package com.yuriolivs.redlinecore.domain.user;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Getter
public class User {
    private String name;

    private String lastName;

    private String email;

    @Setter
    private String password;

    private LocalDate dateOfBirth;

    private List<SavedAdvertisement> savedAdvertisements;

    public static final String NAME_REGEX = "[\\p{L} ]{2,50}";
    public static final String EMAIL_REGEX = "((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])";

    public User(
            String name,
            String lastName,
            String email,
            String password,
            LocalDate dateOfBirth
    ) {
        validateName(name);
        validateLastName(lastName);
        validateEmail(email);
        validateAge(dateOfBirth);

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    private Integer calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private void validateName(String name) {
        if (!name.trim().matches(NAME_REGEX))
            throw new IllegalArgumentException("Name must contain between 2 and 50 characters and " +
                    "can't contain numbers or special characters");
    }

    private void validateLastName(String lastName) {
        if (!lastName.trim().matches(NAME_REGEX))
            throw new IllegalArgumentException("Last name must contain between 2 and 50 characters and " +
                    "can't contain numbers or special characters");
    }

    private void validateEmail(String email) {
        if (!email.trim().matches(EMAIL_REGEX))
            throw new IllegalArgumentException("Email must be valid");
    }

    private void validateAge(LocalDate dateOfBirth) {
        if (calculateAge(dateOfBirth) < 18)
            throw new IllegalArgumentException("User must be 18 or older");
    }

    private Integer getAge() {
        return calculateAge(this.dateOfBirth);
    }

    public List<SavedAdvertisement> getSavedAdvertisements() {
        return Collections.unmodifiableList(this.savedAdvertisements);
    }

    public void setEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        validateAge(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }
}
