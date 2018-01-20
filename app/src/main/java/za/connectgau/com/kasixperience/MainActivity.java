package za.connectgau.com.kasixperience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

private Search(String location)
{
    PlaceSearchRequestParams.Builder builder = 
      new PlaceSearchRequestParams.Builder();

builder.setSearchText("Location");
builder.setDistance(1000); // 1,000 m. max distance.
builder.setLimit(10);
builder.addField(PlaceFields.NAME);
builder.addField(PlaceFields.LOCATION);
builder.addField(PlaceFields.PHONE);

PlaceSearchRequestCallback callback = new PlaceSearchRequestCallback();

// The SDK will automatically retrieve the device location and invoke 
// the OnRequestReadyCallback when the request is ready to be executed.
PlaceManager.newPlaceSearchRequest(builder.build(), callback);
}

private class PlaceSearchRequestCallback
    implements PlaceManager.OnRequestReadyCallback, GraphRequest.Callback {

    @Override
    public void onRequestReady(GraphRequest graphRequest) {
        // The place search request is ready to be executed.
        // The request can be customized here if needed.

        // Sets the callback and executes the request.
        graphRequest.setCallback(this);
        graphRequest.executeAsync();
    }

    @Override
    public void onCompleted(GraphResponse response) {
        // This event is invoked when the place search response is received.
        // Parse the places from the response object.
        
    }

    @Override
    public void onLocationError(PlaceManager.LocationError error) {
        // Invoked if the Places Graph SDK failed to retrieve
        // the device location.
    }
}
