package za.connectgau.com.kasixperience;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.places.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.List;

/**
 * This fragment implements login to Facebook. This step is optional when
 * using the Places Graph SDK.
 *
 * The Places Graph SDK supports two types of authentication tokens:
 * <ul>
 *     <li>Client Token: these do NOT require users to log in Facebook.
 * Refer to {@link com.example.places.MainActivity} to see how to use a Client Token.</li>
 *     <li>User Access Token: these are the tokens obtained when a user logs into Facebook.
 *     This fragment illustrates how to login to Facebook and get a user token.
 *     For more information about Facebook login, see the "HelloFacebookSample".</li>
 * </ul>
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private static final String PUBLIC_PERMISSION = "public_profile";

    private Listener listener;
    private CallbackManager callbackManager;

    public interface Listener {
        void onLoginComplete();
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            listener = (Listener) context;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        List<String> permissions = new ArrayList<>();
        permissions.add(PUBLIC_PERMISSION);
        loginButton.setReadPermissions(permissions);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(
            callbackManager,
            new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        listener.onLoginComplete();
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "onCancel");
                        showAlert();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "onError: " + exception);
                        showAlert();
                    }
                });
    }

    private void showAlert() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.cancelled)
                .setMessage(R.string.permission_not_granted)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
