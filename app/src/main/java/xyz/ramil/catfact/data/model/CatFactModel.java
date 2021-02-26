package xyz.ramil.catfact.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class CatFactModel {
    @PrimaryKey
    @NonNull
    public long id;
    public byte [] cat;
    public String fact;
    public String type;
}
