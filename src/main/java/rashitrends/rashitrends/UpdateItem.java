package rashitrends.rashitrends;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class UpdateItem extends AppCompatActivity {
    EditText name, price, quantity;
    ImageView itemImage;
    Button camera, gallery, save, dimension;
    Bitmap bitmap;
    String dimensions = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        name = findViewById(R.id.name);
        price = findViewById(R.id.avgprice);
        quantity = findViewById(R.id.quantity);
        itemImage = findViewById(R.id.itemImage);
        camera = findViewById(R.id.camerabut);
        gallery = findViewById(R.id.gallerybut);
        save = findViewById(R.id.savebut);
        dimension = findViewById(R.id.editDimension);
        Intent i = getIntent();
        if (i.hasExtra("name"))
            name.setText(i.getExtras().getString("name"));
        if (i.hasExtra("price"))
            price.setText(i.getExtras().getString("price"));
        if (i.hasExtra("quantity"))
            quantity.setText(i.getExtras().getString("quantity"));
        if (i.hasExtra("image")) {
            byte[] arr = i.getByteArrayExtra("image");
            bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
            itemImage.setImageBitmap(bitmap);
        if(i.hasExtra("dimensions"))
            dimensions = i.getStringExtra("dimensions");
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 5);
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent();
                photoPickerIntent.setType("image/*");
                photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"),
                        0);
            }
        });

        dimension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateItem.this, DimensionsEditor.class);
                intent.putExtra("dimensions", dimensions);
                startActivityForResult(intent, 7);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", name.getText().toString());
                double pprice = 0.0;
                try {
                    pprice = Double.parseDouble(price.getText().toString());
                }
                catch (Exception e){}
                intent.putExtra("price", pprice);
                int qquantity = 0;
                try {
                    qquantity = Integer.parseInt(quantity.getText().toString());
                }
                catch (Exception e){}
                intent.putExtra("quantity", qquantity);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] bArray = bos.toByteArray();
                intent.putExtra("image", bArray);
                intent.putExtra("dimensions", dimensions);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            /*String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeFile(imagePath, options);

            // Do something with the bitmap
            imageView.setImageBitmap(bitmap);


            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();*/
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), pickedImage);
                itemImage.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
        }

        if (requestCode == 5 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            itemImage.setImageBitmap(bitmap);
        }

        if (requestCode == 7 && resultCode == RESULT_OK) {
            dimensions = data.getStringExtra("dimensions");
        }
    }
}
