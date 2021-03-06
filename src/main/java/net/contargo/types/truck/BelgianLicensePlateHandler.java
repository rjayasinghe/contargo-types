package net.contargo.types.truck;

/**
 * Can handle Belgian {@link LicensePlate}s.
 *
 * <p>Examples of Belgian license plates:</p>
 *
 * <ul>
 * <li>1-ABC-147</li>
 * <li>Q-AAA-001</li>
 * </ul>
 *
 * <p>Further information: <a
 * href="https://de.wikipedia.org/wiki/Systematik_der_Kfz-Kennzeichen_(Belgien)#Kombinationen">License plates of
 * Belgium</a></p>
 *
 * @author  Aljona Murygina - murygina@synyx.de
 * @since  0.2.0
 */
class BelgianLicensePlateHandler implements LicensePlateHandler {

    /**
     * Normalizes the given {@link LicensePlate} value by upper casing it and replacing all whitespaces by hyphens.
     *
     * @param  value  to get the normalized value for, never {@code null}
     *
     * @return  the normalized value, never {@code null}
     */
    @Override
    public String normalize(String value) {

        return LicensePlateHandler.trim(value).replaceAll("\\s", "-");
    }


    /**
     * Validates the given {@link LicensePlate} value.
     *
     * <p>A Belgian license plate consists of a combination of seven digits and letters. It has three groups that are
     * separated by a hyphen:</p>
     *
     * <ul>
     * <li>one leading digit or letter</li>
     * <li>three letters</li>
     * <li>three digits</li>
     * </ul>
     *
     * <p>or</p>
     *
     * <ul>
     * <li>two leading letters</li>
     * <li>two digits</li>
     * <li>three letters</li>
     * </ul>
     *
     * <p>Structure: 1-XXX-999, 9-999-XXX, X-XXX-999, WX-16-XXX</p>
     *
     * <p>There are certain license plates that may deviate from this rule, for example personalized or royal license
     * plates.</p>
     *
     * <p>Note that these special cases are not covered by this validator! Also this validator considers only the
     * license plate format in general, but not letter combinations in detail.</p>
     *
     * @param  value  to be validated, never {@code null}
     *
     * @return  {@code true} if the given {@link LicensePlate} is valid, else {@code false}
     */
    @Override
    public boolean validate(String value) {

        String normalizedValue = normalize(value);

        // 1-XXX-999
        boolean leadingDigit = normalizedValue.matches("[1-9]\\-[A-Z]{3}\\-[0-9]{3}");

        // 9-999-XXX
        boolean leadingDigitAlternative = normalizedValue.matches("[1-9]\\-[0-9]{3}\\-[A-Z]{3}");

        // X-XXX-999
        boolean leadingLetter = normalizedValue.matches("[A-Z]\\-[A-Z]{3}\\-[0-9]{3}");

        // WX-99-XXX
        boolean leadingLetters = normalizedValue.matches("[A-Z]{2}\\-[0-9]{2}\\-[A-Z]{3}");

        return leadingDigit || leadingDigitAlternative || leadingLetter || leadingLetters;
    }
}
