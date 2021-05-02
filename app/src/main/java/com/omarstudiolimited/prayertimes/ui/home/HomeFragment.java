package com.omarstudiolimited.prayertimes.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.omarstudiolimited.prayertimes.GpsTracker;
import com.omarstudiolimited.prayertimes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Provider;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    View view;
    Spinner SpinnerCity;
    String final_city;
    TextView fjer,dher,aser,mgrep,asha,english_city,english_datee,hjre_datee;
    private GpsTracker gpsTracker;
    double latitude,longitude;

    String current_date,locationx;
    Button bf;

    @SuppressLint("MissingPermission")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        /// Ckeck for location permission
        try {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        //// Get Current date When app Opend
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        current_date = sdf.format(date);
        // spinner Parameters
        SpinnerCity =(Spinner) view.findViewById(R.id.SpinnerCity);
        String[] cities_array = new String[] {
                "اختر الموقع من هنا","القدس - فلسطين",
                "عمٌان - الاردن","مدينة الكويت - الكويت","القاهرة - مصر","الرباط - المغرب",
                "الدوحة - قطر","صنعاء - اليمن","ابو ظبي - الأمارات", "تونس العاصمة - تونس","الخرطوم - السودان",
                "مسقط - عمان","دمشق - سوريا","الجزائر العاصمة - الجزائر","السعودية - الرياض","العراق - بغداد",
                "نواكشط - موريتانيا","مقديشو - الصومال","القدس - فلسطين","بيروت - لبنان"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, cities_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCity.setAdapter(adapter);
        SpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                get_data_from_api();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        english_city=(TextView) view.findViewById(R.id.english_city);
        english_datee=(TextView) view.findViewById(R.id.english_date);
        hjre_datee=(TextView) view.findViewById(R.id.hjre_date);
        fjer =(TextView) view.findViewById(R.id.fjer);
        dher =(TextView) view.findViewById(R.id.dher);
        aser =(TextView) view.findViewById(R.id.aser);
        mgrep =(TextView) view.findViewById(R.id.mgrep);
        asha =(TextView) view.findViewById(R.id.asha);
        getLocation();
        return view;
    }


    public void getLocation(){
        gpsTracker = new GpsTracker(getActivity());
        if(gpsTracker.canGetLocation()){
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

//            english_city.setText(latitude+" lat");
//            english_datee.setText(longitude+" long");
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

public void get_data_from_api(){
        spinner_to_english();
        String url;
        if (final_city.equals("x")){

            url = "https://api.pray.zone/v2/times/day.json?longitude=" + longitude + "&latitude=" + latitude + "&date=" + current_date + "&school=4&timeformat=1";
        }else {
            url = "https://api.pray.zone/v2/times/day.json?city="+final_city+"&date="+current_date+"&school=4&timeformat=1";
        }
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                for (int i =0 ; i<response.length();i++){
                try
                {
                    english_city.setText("");
                    JSONObject results_object = response.getJSONObject("results");
                    JSONArray datetime_array=results_object.getJSONArray("datetime");
                    JSONObject datetime_object=datetime_array.getJSONObject(0);
                    JSONObject times_object = datetime_object.getJSONObject("times");
                    String Fajr = times_object.getString("Fajr");
                    String Dhuhr = times_object.getString("Dhuhr");
                    String Asr = times_object.getString("Asr");
                    String Maghrib = times_object.getString("Maghrib");
                    String Isha = times_object.getString("Isha");
                    fjer.setText(Fajr);
                    dher.setText(Dhuhr);
                    aser.setText(Asr);
                    mgrep.setText(Maghrib);
                    asha.setText(Isha);

                    JSONObject date_object = datetime_object.getJSONObject("date");
                    String english_date = date_object.getString("gregorian");
                    english_datee.setText(english_date);
                    String hjre_date = date_object.getString("hijri");
                    hjre_datee.setText(hjre_date);


                    JSONObject city_and_countery = results_object.getJSONObject("location");
                    String city = city_and_countery.getString("city");
                    String countery = city_and_countery.getString("country");
                    english_city.setText(city+" - "+countery);

                    if (city.equals("")){
                        english_city.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                        english_city.setText("Cannot Find City Name!!!");
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }}


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(view, "Erorr" , Snackbar.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(jor);
    }






public void spinner_to_english(){
       String spinner_value = SpinnerCity.getSelectedItem().toString();
    if(spinner_value.equals("اختر الموقع من هنا")){
        final_city = "x";
    }
       if(spinner_value.equals("عمٌان - الاردن")){
            final_city = "amman";
        }

        if(spinner_value.equals("مدينة الكويت - الكويت")){
            final_city = "kuwait-city";
        }
        if(spinner_value.equals("القاهرة - مصر")){
            final_city = "Cairo";
        }
        if(spinner_value.equals("الرباط - المغرب")){
            final_city = "Rabat";
        }
        if(spinner_value.equals("الدوحة - قطر")){
            final_city = "Doha";
        }
        if(spinner_value.equals("صنعاء - اليمن")){
            final_city = "sanaa";
        }
        if(spinner_value.equals("ابو ظبي - الأمارات")){
            final_city = "abu-dhabi";
        }
        if(spinner_value.equals("تونس العاصمة - تونس")){
            final_city = "Tunis";
        }
        if(spinner_value.equals("الخرطوم - السودان")){
            final_city = "Khartoum";
        }
        if(spinner_value.equals("مسقط - عمان")){
            final_city = "Muscat";
        }

        if(spinner_value.equals("دمشق - سوريا")){
            final_city = "damascus";
        }

        if(spinner_value.equals("الجزائر العاصمة - الجزائر")){
            final_city = "Algiers";
        }

        if(spinner_value.equals("السعودية - الرياض")){
            final_city = "riyadh";
        }

        if(spinner_value.equals("العراق - بغداد")){
            final_city = "baghdad";
        }
        if(spinner_value.equals("نواكشط - موريتانيا")){
            final_city = "Nouakchott";
        }

        if(spinner_value.equals("مقديشو - الصومال")){
            final_city = "Mogadishu";
        }

        if(spinner_value.equals("القدس - فلسطين")){
            final_city = "jerusalem";
        }

        if(spinner_value.equals("بيروت - لبنان")){
            final_city = "beirut";
        }
    }


}// Fragment Class End















//    String[] cities_array = new String[] {
//            "عمٌان - الاردن","طرابلس - ليبيا","مدينة الكويت - الكويت","القاهرة - مصر","الرابط - المغرب",
//            "الدوحة - قطر","صنعاء - اليمن","Abu Dhabi","Manama", "Tunis","Khartoum",
//            "Muscat","Damascus","Algiers","Riyadh","Baghdad","Djibouti",
//            "Nouakchott","Mogadishu ","Jerusalem","Beirut"};









//                    JSONObject results_object = response.getJSONObject("results");
//                    JSONObject settings_object = results_object.getJSONObject("settings");
//                    String tt = settings_object.getString("fajr_angle");
//                    fjer.setText(tt);
//                    JSONArray datetime_array=results_object.getJSONArray("datetime");
//                    JSONObject times_object=datetime_array.getJSONObject(0);
//                    String imsak = times_object.getString("Imsak");
//                    fjer.setText(imsak);
//                    JSONObject date_object=datetime_array.getJSONObject(1);
//                    String timestamp = date_object.getString("timestamp");
//                    dher.setText(timestamp);
//                    String tt = response.getString("code");
//                    fjer.setText(tt);
//                    String city = response.getString("status");
//                    ttt.setText(city);