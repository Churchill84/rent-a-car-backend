package com.rentacar.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CarStatus {
    AVAILABLE,
    RENTED,
    RESERVED,
    OUT_OF_SERVICE
}
