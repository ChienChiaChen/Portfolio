package com.chiachen.portfolio.activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.base._MVPActivity;
import com.chiachen.portfolio.network.response.weather.WeatherResponse;
import com.chiachen.portfolio.presenter.WeatherPresenter;
import com.chiachen.portfolio.view.WeatherView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

//Ref. https://code.tutsplus.com/tutorials/create-a-weather-app-on-android--cms-21587
public class WeatherActivity extends _MVPActivity<WeatherPresenter> implements WeatherView {

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    SearchView searchView;
    Typeface weatherFont;

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_weather);
        initToolBarAsHome(this.getClass().getSimpleName());

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

        cityField.setText(model.name.toUpperCase() + ", " + model.sys.country);
        detailsField.setText(model.weather.get(0).description.toUpperCase() + "\n" + "Humidity: " + model.main.humidity + "%" + "\n" + "Pressure: " + model.main.pressure + " hPa");
        currentTemperatureField.setText(new DecimalFormat("##.0").format(model.main.temp-273.15) + " ℃");

        DateFormat df = DateFormat.getDateTimeInstance();
        String updatedOn = df.format(new Date(model.dt * 1000));
        updatedField.setText("Last update: " + updatedOn);
        setWeatherIcon(model.weather.get(0).id, model.sys.sunrise * 1000, model.sys.sunset * 1000);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = getString(R.string.weather_cloudy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
            }
        }
        weatherIcon.setText(icon);
    }

    @Override
    public void getDataFail(String msg) {
        Log.e("JASON_CHIEN", "\ngetDataFail");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Enter City name..");
        mPresenter.getResultsBasedOnQuery(searchView);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_search == item.getItemId()) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
