package xyz.ramil.catfact.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class CatFactModel {
    @PrimaryKey
    byte [] cat;
    String fact;
}
