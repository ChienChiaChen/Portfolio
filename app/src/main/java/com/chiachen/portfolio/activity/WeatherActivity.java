package com.chiachen.portfolio.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.base._MVPActivity;
import com.chiachen.portfolio.network.response.weather.WeatherResponse;
import com.chiachen.portfolio.presenter.WeatherPresenter;
import com.chiachen.portfolio.view.WeatherView;

import java.text.DateFormat;
import java.util.Date;

//Ref. https://code.tutsplus.com/tutorials/create-a-weather-app-on-android--cms-21587
public class WeatherActivity extends _MVPActivity<WeatherPresenter> implements WeatherView {

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(this);
    }

    private Typeface weatherFont;

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_weather);
        cityField = findViewById(R.id.city_field);
        updatedField = findViewById(R.id.updated_field);
        detailsField = findViewById(R.id.details_field);
        currentTemperatureField = findViewById(R.id.current_temperature_field);
        weatherIcon = findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");
        weatherIcon.setTypeface(weatherFont);

        mPresenter.loadData("Tainan");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDataSuccess(WeatherResponse model) {
        Log.e("JASON_CHIEN", "\ngetDataSuccess");

        cityField.setText(model.name.toUpperCase() + ", " + model.sys.country);
        detailsField.setText(model.weather.get(0).description.toUpperCase() + "\n" + "Humidity: " + model.main.humidity + "%" + "\n" + "Pressure: " + model.main.pressure + " hPa");
        currentTemperatureField.setText(model.main.temp + " ℃");

        DateFormat df = DateFormat.getDateTimeInstance();
        String updatedOn = df.format(new Date(model.dt * 1000));
        updatedField.setText("Last update: " + updatedOn);
        setWeatherIcon(model.weather.get(0).id, model.sys.sunrise * 1000, model.sys.sunset * 1000);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getString(R.string.weather_rainy);
                    break;
            }
        }
        weatherIcon.setText(icon);
    }

    @Override
    public void getDataFail(String msg) {
        Log.e("JASON_CHIEN", "\ngetDataFail");
    }
}
