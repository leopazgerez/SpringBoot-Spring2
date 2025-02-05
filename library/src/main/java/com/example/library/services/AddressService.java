package com.example.library.services;

import com.example.library.models.Address;

public interface AddressService {

    void createAddress();

    Address getAddress();

    Address updateAddress();

    void deleteAddress();
}
