package com.zipcodewilmington.centrallibrary;

public interface Reservable {
    void reserve(LibraryMember member);

    void cancelReservation(LibraryMember member);

    boolean isReserved();
}
