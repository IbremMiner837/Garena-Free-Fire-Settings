package studio.jvmfrog.ffsettings.utils;

import static com.android.volley.RequestQueue.RequestEvent.REQUEST_CACHE_LOOKUP_FINISHED;
import static com.android.volley.RequestQueue.RequestEvent.REQUEST_CACHE_LOOKUP_STARTED;
import static com.android.volley.RequestQueue.RequestEvent.REQUEST_FINISHED;
import static com.android.volley.RequestQueue.RequestEvent.REQUEST_NETWORK_DISPATCH_FINISHED;
import static com.android.volley.RequestQueue.RequestEvent.REQUEST_NETWORK_DISPATCH_STARTED;
import static com.android.volley.RequestQueue.RequestEvent.REQUEST_QUEUED;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestEventListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import studio.jvmfrog.ffsettings.model.ManufacturersModel;

public class ManufacturerManager {
    private static final String GITHUB_MANUFACTURERS_FILES_PATH = "https://raw.githubusercontent.com/IbremMiner837/Garena-Free-Fire-Settings/master/app/src/main/assets/sensitivity_settings/manufacturers.json";
    private final List<ManufacturersModel> manufacturersSet = new ArrayList<>();
    private final MutableLiveData<Boolean> isRequestFinished = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isReadyLiveData = new MutableLiveData<>();
    private static ManufacturerManager instance;

    // Private constructor to prevent instantiation from outside
    private ManufacturerManager() {
    }

    // Public method to get the singleton instance
    public static synchronized ManufacturerManager getInstance() {
        if (instance == null) {
            instance = new ManufacturerManager();
        }
        return instance;
    }

    public List<ManufacturersModel> getManufacturersSet() {
        return manufacturersSet;
    }

    public MutableLiveData<Boolean> isRequestFinished() {
        return isRequestFinished;
    }

    public MutableLiveData<Boolean> isReadyLiveData() {
        return isReadyLiveData;
    }

    public boolean isReady() {
        return isReadyLiveData.getValue() != null && isReadyLiveData.getValue();
    }

    public void updateAdapterData(Context context) {
        if (manufacturersSet.isEmpty()) {
            isRequestFinished.setValue(false);
            isReadyLiveData.setValue(false);
            try {
                RequestQueue queue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, GITHUB_MANUFACTURERS_FILES_PATH, null,
                        response -> {
                            try {
                                parseResponse(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> Log.e("Volley", error.toString())
                );
                queue.addRequestEventListener(createRequestEventListener());
                queue.add(jsonObjectRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseResponse(JSONObject response) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("manufacturers");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String model = jsonObject.getString("model");
            Boolean showInProductionApp = jsonObject.getBoolean("showInProductionApp");
            Boolean isAvailable = jsonObject.getBoolean("isAvailable");
            manufacturersSet.add(new ManufacturersModel(name, model, showInProductionApp, isAvailable));
        }
        isReadyLiveData.postValue(true);
        isRequestFinished.postValue(true);
    }

    private RequestEventListener createRequestEventListener() {
        return (request, event) -> {
            switch (event) {
                case REQUEST_QUEUED:
                case REQUEST_CACHE_LOOKUP_STARTED:
                case REQUEST_NETWORK_DISPATCH_STARTED:
                    isRequestFinished.postValue(false);
                    isReadyLiveData.postValue(false);
                    break;
                case REQUEST_FINISHED:
                case REQUEST_CACHE_LOOKUP_FINISHED:
                case REQUEST_NETWORK_DISPATCH_FINISHED:
                    isRequestFinished.postValue(true);
                    isReadyLiveData.postValue(true);
                    break;
                default:
                    break;
            }
        };
    }
}