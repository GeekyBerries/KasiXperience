package za.connectgau.com.kasixperience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TakeMeThere extends AppCompatActivity {

     String clientid = "CLIENT_ID";
     String clientSecret = "CLIENT_SECRET";

     TransportApiClient defaultClient = new TransportApiClient(new TransportApiClientSettings(clientid,clientSecret));

     TransportApiResult<List<Agency>> agencies = defaultClient.getAgencies(AgencyQueryOptions.defaultQueryOptions());
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_me_there);
    }
}
