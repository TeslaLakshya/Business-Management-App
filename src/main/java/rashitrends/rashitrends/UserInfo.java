package rashitrends.rashitrends;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfo extends AppCompatActivity {
    private static final String TAG = UserInfo.class.getSimpleName();
    EditText name, phoneNumber, email, customersince, address;
    Bitmap bitmap;
    Intent intent;
    Button submit;
    TextInputLayout userLayoutName, userLayoutPhoneNumber,userLayoutEmailId,
            userLayoutCustomerSince, userLayoutAddress;
    public static final int REQUEST_IMAGE = 100;
    @BindView(R.id.img_profile1)
    ImageView imgProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        name = findViewById(R.id.username);
        phoneNumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.emailId);
        customersince = findViewById(R.id.customerSince);
        address = findViewById(R.id.address);
        userLayoutName = findViewById(R.id.userLayoutName);
        userLayoutPhoneNumber = findViewById(R.id.userLayoutPhoneNumber);
        userLayoutEmailId = findViewById(R.id.userLayoutEmailId);
        userLayoutCustomerSince = findViewById(R.id.userLayoutCustomerSince);
        userLayoutAddress = findViewById(R.id.userLayoutAddress);
        name.addTextChangedListener(new MyTextWatcher(name));
        phoneNumber.addTextChangedListener(new MyTextWatcher(phoneNumber));
        email.addTextChangedListener(new MyTextWatcher(email));
        customersince.addTextChangedListener(new MyTextWatcher(customersince));
        address.addTextChangedListener(new MyTextWatcher(address));
        submit = findViewById(R.id.submitt);
        loadProfileDefault();
        ImagePickerActivity.clearCache(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateName() && validateCustomerSince() && validateEmailId() &&
                        validatePhoneNumber() && validateAddress())
                    new SaveDataAsyncTask().execute();
            }
        });
    }

    class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.username:
                    validateName();
                    break;
                case R.id.emailId:
                    validateEmailId();
                    break;
                case R.id.phonenumber:
                    validatePhoneNumber();
                    break;
                case R.id.customerSince:
                    validateCustomerSince();
                case R.id.address:
                    validateAddress();
            }
        }
    }

    private boolean validateName(){
        String nname = name.getText().toString().trim();
        if (nname.isEmpty()) {
            userLayoutName.setError("Please enter a valid name");
            requestFocus(name);
            return false;
        } else {
            userLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress(){
        String aaddress = address.getText().toString().trim();
        if (aaddress.isEmpty()) {
            userLayoutAddress.setError("Please enter a valid name");
            requestFocus(address);
            return false;
        } else {
            userLayoutAddress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmailId(){
        String eemail = email.getText().toString().trim();
        if (eemail.isEmpty() && eemail.contains("@") &&
                eemail.split("@").length == 0) {
            userLayoutEmailId.setError("Please enter a valid name");
            requestFocus(email);
            return false;
        } else {
            userLayoutEmailId.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhoneNumber(){
        String pphoneNumber = phoneNumber.getText().toString().trim();
        if (pphoneNumber.isEmpty() && pphoneNumber.length() < 10) {
            userLayoutPhoneNumber.setError("Please enter a valid name");
            requestFocus(phoneNumber);
            return false;
        } else {
            userLayoutPhoneNumber.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCustomerSince(){
        String since = customersince.getText().toString().trim();
        if (since.isEmpty()) {
            userLayoutCustomerSince.setError("Please enter a valid name");
            requestFocus(customersince);
            return false;
        } else {
            userLayoutCustomerSince.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        GlideApp.with(this).load(url)
                .into(imgProfile);
        imgProfile.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void loadProfileDefault() {
        GlideApp.with(this).load(R.drawable.additems)
                .into(imgProfile);
        imgProfile.setColorFilter(ContextCompat.getColor(this, R.color.profile_default_tint));
    }

    @OnClick({R.id.img_plus1, R.id.img_profile1})
    void onProfileImageClick() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this,
                new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(UserInfo.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(UserInfo.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfo.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public class SaveDataAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            intent.putExtra("username", name.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("address", address.getText().toString());
            intent.putExtra("customerSince", customersince.getText().toString());
            long pphonenumber = 0;
            try {
                pphonenumber = Long.parseLong(phoneNumber.getText().toString());
            } catch (Exception e) {
            }
            intent.putExtra("phoneNumber", pphonenumber);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();
            intent.putExtra("userimage", bArray);
            setResult(RESULT_OK, intent);
            finish();
            return null;
        }
    }
}
