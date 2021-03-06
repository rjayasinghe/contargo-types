package net.contargo.types.contactinfo.validation;

import net.contargo.types.contactinfo.ContactInformation;

import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ExternalUserContactInfoCompletenessValidatorTest {

    @Test
    public void ensureThatContactInfoWithAllDataIsValid() {

        ExternalUserCompletenessValidator externalUserCompletenessValidator = new ExternalUserCompletenessValidator();

        ContactInformation contactInfoWithAllData = new ContactInformation(UUID.randomUUID().toString(),
                "0156272364623", "0049574753847", "email");
        List<ValidationResult> validationResults = externalUserCompletenessValidator.checkCompleteness(
                contactInfoWithAllData);
        assertTrue(validationResults.isEmpty());
    }


    @Test
    public void ensureThatContactInfoWithoutMailAndMobileIsInvalid() {

        ExternalUserCompletenessValidator externalUserCompletenessValidator = new ExternalUserCompletenessValidator();

        ContactInformation contactInfoWithoutEmailAndMobile = new ContactInformation(UUID.randomUUID().toString(), "",
                "00234234234", "");
        List<ValidationResult> validationResults = externalUserCompletenessValidator.checkCompleteness(
                contactInfoWithoutEmailAndMobile);

        assertEquals(2, validationResults.size());
        assertTrue(validationResults.contains(ValidationResult.MISSING_MOBILE));
        assertTrue(validationResults.contains(ValidationResult.MISSING_EMAIL));
    }


    @Test
    public void ensureThatContactInfoWithoutPhoneNumberIsInvalid() {

        ExternalUserCompletenessValidator externalUserCompletenessValidator = new ExternalUserCompletenessValidator();

        ContactInformation contactInformationWithoutPhoneNumbers = new ContactInformation(UUID.randomUUID()
                .toString(), "", "", "email");
        List<ValidationResult> validationResults = externalUserCompletenessValidator.checkCompleteness(
                contactInformationWithoutPhoneNumbers);

        assertEquals(2, validationResults.size());
        assertTrue(validationResults.contains(ValidationResult.MISSING_MOBILE));
        assertTrue(validationResults.contains(ValidationResult.MISSING_PHONE));
    }
}
