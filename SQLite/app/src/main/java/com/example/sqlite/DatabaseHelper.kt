import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "contacts.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE contacts (id INTEGER PRIMARY KEY, name TEXT, phone TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS contacts")
        onCreate(db)
    }

    fun addContact(id: Int, name: String, phone: String): Boolean {
        return writableDatabase.use { db ->
            val values = ContentValues().apply {
                put("id", id)
                put("name", name)
                put("phone", phone)
            }
            db.insert("contacts", null, values) != -1L
        }
    }

    fun updateContact(id: Int, name: String, phone: String): Boolean {
        return writableDatabase.use { db ->
            val values = ContentValues().apply {
                put("name", name)
                put("phone", phone)
            }
            db.update("contacts", values, "id=?", arrayOf(id.toString())) > 0
        }
    }

    fun deleteContact(id: Int): Boolean {
        return writableDatabase.use { db ->
            db.delete("contacts", "id=?", arrayOf(id.toString())) > 0

        }
    }

    fun getAllContacts(): List<String> {
        val contactList = mutableListOf<String>()
        readableDatabase.use { db ->
            db.rawQuery("SELECT * FROM contacts", null).use { cursor ->
                while (cursor.moveToNext()) {
                    contactList.add("${cursor.getInt(0)} - ${cursor.getString(1)}: ${cursor.getString(2)}")
                }
            }
        }
        return contactList
    }
}
