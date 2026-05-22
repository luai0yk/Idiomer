package com.esh.eih.Databases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esh.eih.IdiomsModel;
import com.esh.eih.LYK;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context, LYK.FAVORITES_TABLE_NAME, null, LYK.FAVORITE_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+LYK.FAVORITES_TABLE_NAME+" ("+LYK.ID+" INTEGER, "+LYK.IDIOM+" TEXT, "+LYK.AR_IDIOM+" TEXT, "+LYK.DEFINITION+" TEXT, "+LYK.AR_DEFINITION+" TEXT, "+LYK.EXAMPLE+" TEXT, "+LYK.AR_EXAMPLE+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addToFavorite(IdiomsModel idiomsModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LYK.ID,idiomsModel.getId());
        values.put(LYK.IDIOM,idiomsModel.getIdiom());
        values.put(LYK.AR_IDIOM,idiomsModel.getArIdiom());
        values.put(LYK.DEFINITION,idiomsModel.getDefinition());
        values.put(LYK.AR_DEFINITION,idiomsModel.getArDefinition());
        values.put(LYK.EXAMPLE,idiomsModel.getExample());
        values.put(LYK.AR_EXAMPLE,idiomsModel.getArExample());
        long result = db.insert(LYK.FAVORITES_TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean deleteFromFavorite(int ID) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {ID + ""};
        int result = db.delete(LYK.FAVORITES_TABLE_NAME, "" + LYK.ID + "=?", args);
        return result > 0;
    }

    public boolean getCheckFavorite(int Idiom_ID){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + LYK.FAVORITES_TABLE_NAME+ " WHERE " +LYK.ID+ " LIKE'" +Idiom_ID+"'", null);
        return cursor.getCount() > 0;
    }

    public ArrayList<IdiomsModel> getFavorites() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<IdiomsModel> idiomArray = new ArrayList<>();
        Cursor cursor = cursor = db.rawQuery("SELECT * FROM " + LYK.FAVORITES_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do{
                int id = cursor.getInt(0);
                String idiom = cursor.getString(1);
                String arIdiom = cursor.getString(2);
                String definition = cursor.getString(3);
                String arDefinition = cursor.getString(4);
                String example = cursor.getString(5);
                String arExample = cursor.getString(6);
                IdiomsModel idiomsModel = new IdiomsModel();
                idiomsModel.setId(id);
                idiomsModel.setIdiom(idiom);
                idiomsModel.setArIdiom(arIdiom);
                idiomsModel.setDefinition(definition);
                idiomsModel.setArDefinition(arDefinition);
                idiomsModel.setExample(example);
                idiomsModel.setArExample(arExample);
                idiomArray.add(idiomsModel);
            } while(cursor.moveToNext());
            cursor.close();
        }
        return idiomArray;
    }
}
