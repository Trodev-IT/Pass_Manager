package com.trodev.mypasswordgenerator.password_generate_models.generators;

import com.trodev.mypasswordgenerator.password_generate_models.helpers.Helper;

public class SpecialCharGenerator extends PasswordGenerator {
    private static final char[] SPECIAL_CHAR_ARRAY = "?./!%*$^+-)]@(['{}#<>".toCharArray();

    @Override
    public String getChar() {
        return String.valueOf(SPECIAL_CHAR_ARRAY[Helper.randomChar(0, SPECIAL_CHAR_ARRAY.length - 1)]);
    }
}
