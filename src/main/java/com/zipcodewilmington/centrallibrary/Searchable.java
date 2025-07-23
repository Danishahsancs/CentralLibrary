package com.zipcodewilmington.centrallibrary;

import java.util.List;

public interface Searchable {
    // boolean matchesKeyword(String keyWord);
    default boolean matchesKeyword(String keyWord) {
        for (String field : this.getSearchableFields()) {
            if (field != null && field.toLowerCase().contains(keyWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    List<String> getSearchableFields();
}
