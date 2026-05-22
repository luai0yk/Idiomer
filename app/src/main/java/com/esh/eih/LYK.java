package com.esh.eih;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LYK {

    public final static List<String> lettersArray = Arrays.asList("♡","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
    public static final String DB_NAME = "EnglishIdiom.db";
    public static final int DB_VERSION = 2;
    public static final int FAVORITE_DB_VERSION = 1;
    public static final String IDIOMS_TABLE_NAME = "Idioms";
    public static final String FAVORITES_TABLE_NAME = "Favorite";
    public static final String ID = "Id";
    public static final String FAVORITE_ID = "Favorite_Id";
    public static final String IDIOM = "Idiom";
    public static final String AR_IDIOM = "Ar_Idiom";
    public static final String DEFINITION = "Definition";
    public static final String AR_DEFINITION = "Ar_Definition";
    public static final String EXAMPLE = "Example";
    public static final String AR_EXAMPLE = "Ar_Example";

    public static final int IDIOMS_FRAGMENT = 1;
    public static final int FAVORITE_FRAGMENT = 2;
    public static final int QUIZZES_FRAGMENT = 3;
    public static final int SETTINGS_FRAGMENT = 4;
    public static final int VIEW_IDIOM_FRAGMENT = 5;

    public static final int EMPTY_LIST = 6;
    public static final int QUIZZES_VIEW_FRAGMENT = 7;
    public static final int VIEW_VIEW_FRAGMENT = 8;

    public  static final int i = 0;
    public static final String FIRST_LETTER = "First_Letter";
}
