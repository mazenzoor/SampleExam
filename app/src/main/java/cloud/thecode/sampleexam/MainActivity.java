package cloud.thecode.sampleexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner region_spinner, specialist_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        region_spinner = (Spinner) findViewById(R.id.region_spinner);
        specialist_spinner = (Spinner) findViewById(R.id.specialist_spinner);

        final ArrayList<String> specialist = new ArrayList<>();

        final ArrayAdapter<String> specialistArrayAdapter =
                new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, specialist);

        specialist_spinner.setAdapter(specialistArrayAdapter);

        RequestQueue rq = Volley.newRequestQueue(this);

        JsonArrayRequest jr = new JsonArrayRequest("http://thecode.cloud/android/getSpecialist.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // On successful return we fill the arralist of students by looping
                // through the returned JSON array [ {object}, {object} ]

                for(int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        String description = jsonObject.getString("Description");
                        Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
                        specialistArrayAdapter.add(description);



                    } catch (Exception ex) {
                        Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                specialistArrayAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jr);

    }
}
