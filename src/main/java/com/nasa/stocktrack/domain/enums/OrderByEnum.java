package com.nasa.stocktrack.domain.enums;

public enum OrderByEnum {
    ASC,
    DESC;

    public static OrderByEnum fromString(String value) {
        if(value !=null) {
            for(OrderByEnum orderBy: OrderByEnum.values()) {
                if(value.equalsIgnoreCase(orderBy.name())) {
                    return orderBy;
                }
            }
        }

        throw new IllegalArgumentException("No constant with name " + value + " found");
    }
}
