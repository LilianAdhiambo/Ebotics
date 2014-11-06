package com.example.Ebotics.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.Ebotics.Models.user;
import com.example.Ebotics.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 10/30/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //Get the name of the class.
    private static final String TAG = DatabaseHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDataBase";
    private static final String TABLE_USER_DETAILS = "userDetails";

    //Database Field Values
    private static final String TABLE_COLUMN_ID = "id";
    private static final String TABLE_COLUMN_FULL_NAME = "fullNames";
    private static final String TABLE_COLUMN_USER_NAME = "userNames";
    private static final String TABLE_COLUMN_USER_PASSWORD = "password";
    private static final String TABLE_COLUMN_EMAIL = "email";
    private static final String TABLE_COLUMN_PHONE_NUMBER = "phoneNumber";


    //Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This method is called when creating the database
     *
     * @param sqLiteDatabase Database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "CREATE TABLE " + TABLE_USER_DETAILS + "("
                + TABLE_COLUMN_ID + "INTEGER AUTO INCREMENT PRIMARY KEY, "
                + TABLE_COLUMN_FULL_NAME + "TEXT, "
                + TABLE_COLUMN_USER_NAME + "TEXT, "
                + TABLE_COLUMN_EMAIL + "TEXT, "
                + TABLE_COLUMN_USER_PASSWORD + "TEXT, "
                + TABLE_COLUMN_PHONE_NUMBER + "TEXT"
                + ");";

        sqLiteDatabase.execSQL(createCommand);

    }

    /**
     * This method is called when upgrading the database.
     *
     * @param sqLiteDatabase Database
     * @param oldVersion     Old database version
     * @param newVersion     New database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Warning!! You are upgrading your Database Version: " + newVersion);

    }

    /**
     * This method INSERTS data to the database
     *
     * @param user User Model
     */
    public void addUserDetails(user user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_USER_NAME, user.getUserName());
        contentValues.put(Constants.KEY_PASSWORD, user.getPassword());
        contentValues.put(Constants.KEY_EMAIL, user.getEmail());

        sqLiteDatabase.insert(TABLE_USER_DETAILS, null, contentValues);

    }

    /**
     * @return User details (List)
     */
    public List<user> getUserDetails() {
        List<user> userList = new ArrayList<user>();
        String selectCommand = "SELECT * FROM " + TABLE_USER_DETAILS;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectCommand, null);

        //Loop through the Result
        if (cursor.moveToFirst()) {
            do {
                String fullNames = cursor.getString(1);
                String userName = cursor.getString(2);
                String email = cursor.getString(3);
                String password = cursor.getString(4);
                String phoneNumber = cursor.getString(5);

                user userModel = new user();
                userModel.setUserName(userName);
                userModel.setPassword(password);
                userModel.setEmail(email);

                userList.add(userModel);

            } while (cursor.moveToNext());
        }

        return userList;
    }

    /**
     * This method get the number of users from the table
     *
     * @return Count
     */
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER_DETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    /**
     * This method updates user details
     *
     * @param user User Model
     * @return result
     */
    public int updateContact(user user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_USER_NAME, user.getUserName());
        values.put(Constants.KEY_EMAIL, user.getEmail());

        // updating row
        return db.update(TABLE_USER_DETAILS, values, TABLE_COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getUserName())});
    }

    /**
     * This method delete user details
     * @param user User Model
     */
    public void deleteContact(user user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_DETAILS, TABLE_COLUMN_USER_NAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
        db.close();
    }
}