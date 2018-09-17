package net.contargo.types.contactinfo.validation;

import net.contargo.types.contactinfo.ContactInformation;

import java.util.List;


/**
 * Implementations of this interface can check whether certain properties in a {@link ContactInformation} object are
 * unique within the context of the validators implementation.
 */
public interface UniquenessValidator {

    List<ValidationResult> checkUniqueness(ContactInformation contactInformation);


    boolean isEmailUnique(String userUuid, String email);


    boolean isMobileUnique(String userUuid, String mobile);
}
