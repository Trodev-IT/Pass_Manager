package com.trodev.mypasswordgenerator.password_generate_models.generators;

import com.trodev.mypasswordgenerator.password_generate_models.helpers.Helper;

public class NumericGenerator extends PasswordGenerator{
    private static final char CHAR_0 = '0';
    private static final char CHAR_9 = '9';


    @Override
    public String getChar() {
        return String.valueOf((char) (Helper.randomChar(CHAR_0, CHAR_9)));
    }
}
