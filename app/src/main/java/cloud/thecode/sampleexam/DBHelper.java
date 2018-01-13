package cloud.thecode.sampleexam;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Mazen on 1/11/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB = "Clinics";
    private final static int VERSION = 1;
    private final static String[] phones = {"01784738", "01888647", "01735748", "03288164"};
    private final static String[] names = {"John Doe", "Jenny Doe", "James Doe", "Jollene Doe"};
    private final static String[] regions = {"Mount Lebanon", "Beirut", "South Lebanon", "North Lebanon"};
    private final static String[] specialists = {"Eyes", "Physical", "Nose, Ears & Mouth", "Brain"};
    private final static String[] address = {"first open street, Hazmieh", "New Mar Takla, Baabda", "Bliss Street, Hamra", "Awad Building, Jbeil"};
    private final static String[] emails = {"contact@newclinic.com", "contact@operationclinic.net", "surgery@opsumclinic.com", "dentist@freeclinic.org"};

    public DBHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Doctor (phone char(8) primary key, name text, email text, address text, specialist text, region text)");
        db.rawQuery("INSERT INTO Doctor VALUES(" + phones[0] + "," + names[0] + ", " + emails[0] + ", "+ address[0] +", "+ specialists[0] +", " + regions[0] +")", null);
        db.rawQuery("INSERT INTO Doctor VALUES(" + phones[1] + "," + names[1] + ", " + emails[1] + ", "+ address[1] +", "+ specialists[1] +", " + regions[1] +")", null);
        db.rawQuery("INSERT INTO Doctor VALUES(" + phones[2] + "," + names[2] + ", " + emails[2] + ", "+ address[2] +", "+ specialists[2] +", " + regions[2] +")", null);
        db.rawQuery("INSERT INTO Doctor VALUES(" + phones[3] + "," + names[3] + ", " + emails[3] + ", "+ address[3] +", "+ specialists[3] +", " + regions[3] +")", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Doctors");
        onCreate(db);
    }

    public ArrayList<Doctor> getDoctor(String specialist, String region) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Doctor WHERE specialist = " + specialist + " and region = " + region, null);
        c.moveToFirst();
        db.close();

        for(int i =0; i < c.getCount(); i++) {
            Doctor d = new Doctor(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
            doctors.add(d);
        }

        return doctors;
    }




}
