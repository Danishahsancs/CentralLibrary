package com.zipcodewilmington.centrallibrary;

import java.util.List;

public interface Searchable {
    boolean matchesKeyword(String keyWord);
    List<String> getSearchableFields();
}
