package com.codepath.shellhacks;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Information {

    Information(String name, String bloodType, String allergies, int number1, int number2){
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.number1 = number1;
        this.number2 = number2;
    }

    @PrimaryKey
    @ColumnInfo(name = "uuid")
    private String uuid;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "blood_type")
    public String bloodType;

    @ColumnInfo(name = "allergies")
    public String allergies;

    @ColumnInfo(name = "number1")
    public int number1;

    @ColumnInfo(name = "number2")
    public int number2;
}
