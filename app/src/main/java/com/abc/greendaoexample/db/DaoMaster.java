package com.abc.greendaoexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.DatabaseOpenHelper;
import de.greenrobot.dao.database.EncryptedDatabaseOpenHelper;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.abc.greendaoexample.db.LocationDao;
import com.abc.greendaoexample.db.TagDao;
import com.abc.greendaoexample.db.LocationTagDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    public static abstract class EncryptedOpenHelper extends EncryptedDatabaseOpenHelper {
        public EncryptedOpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public EncryptedOpenHelper(Context context, String name, Object cursorFactory, boolean loadNativeLibs) {
            super(context, name, cursorFactory, SCHEMA_VERSION, loadNativeLibs);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class EncryptedDevOpenHelper extends EncryptedOpenHelper {
        public EncryptedDevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public EncryptedDevOpenHelper(Context context, String name, Object cursorFactory, boolean loadNativeLibs) {
            super(context, name, cursorFactory, loadNativeLibs);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }
    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        LocationDao.createTable(db, ifNotExists);
        TagDao.createTable(db, ifNotExists);
        LocationTagDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        LocationDao.dropTable(db, ifExists);
        TagDao.dropTable(db, ifExists);
        LocationTagDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends DatabaseOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }


    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(LocationDao.class);
        registerDaoClass(TagDao.class);
        registerDaoClass(LocationTagDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
