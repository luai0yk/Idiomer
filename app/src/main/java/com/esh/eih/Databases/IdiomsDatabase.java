package com.esh.eih.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.esh.eih.IdiomsModel;
import com.esh.eih.LYK;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
public class IdiomsDatabase extends SQLiteAssetHelper {

    public IdiomsDatabase(Context context){
        super(context, LYK.DB_NAME, null, LYK.DB_VERSION);
        setForcedUpgrade(LYK.DB_VERSION);
    }

    //edit
   /* public boolean edit(IdiomsModel im) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LYK.IDIOM, im.getFavorite());
        String[] args = {im.getId() + ""};
        int result = db.update(LYK.IDIOMS_TABLE_NAME, values, "Id=?", args);
        return result > 0;
    }*/

    //get data
    public ArrayList<IdiomsModel> getData(String... type) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<IdiomsModel> idiomArray = new ArrayList<>();
        Cursor cursor = null;
        switch (type[0]) {
            case "idiom":
                cursor = db.rawQuery("SELECT * FROM " + LYK.IDIOMS_TABLE_NAME + " WHERE "+LYK.ID+" LIKE " + Integer.valueOf(type[1]), null);
                break;
            case "search":
                cursor = db.rawQuery("SELECT * FROM " + LYK.IDIOMS_TABLE_NAME + " WHERE " + LYK.IDIOM + " LIKE ? ORDER BY " + LYK.IDIOM + " ASC", new String[] { "%" + type[1] + "%" });
                break;
            case "main":
                cursor = db.rawQuery("SELECT * FROM " + LYK.IDIOMS_TABLE_NAME + " ORDER BY " + LYK.IDIOM + " ASC", null);
                break;
            case LYK.FIRST_LETTER:
                cursor = db.rawQuery("SELECT * FROM " + LYK.IDIOMS_TABLE_NAME + " WHERE " + LYK.IDIOM + " LIKE '"+type[1]+"%'", null);
                break;
        }

        assert cursor != null;
        if (cursor.moveToFirst()) {
            do{
                int id = cursor.getInt(0);
                String idiom = cursor.getString(1);
                String arIdiom = cursor.getString(2);
                String definition = cursor.getString(3);
                String arDefinition = cursor.getString(4);
                String example = cursor.getString(5);
                String arExample = cursor.getString(6);
                IdiomsModel idm = new IdiomsModel();
                idm.setId(id);
                idm.setIdiom(idiom);
                idm.setArIdiom(arIdiom);
                idm.setDefinition(definition);
                idm.setArDefinition(arDefinition);
                idm.setExample(example);
                idm.setArExample(arExample);
                idiomArray.add(idm);
            } while(cursor.moveToNext());
            cursor.close();
        }
        return idiomArray;
    }

    //get idioms number
    public long getCount() {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, LYK.IDIOMS_TABLE_NAME);
    }

}