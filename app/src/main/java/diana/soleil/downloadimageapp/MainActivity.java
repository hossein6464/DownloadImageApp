package diana.soleil.downloadimageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView imageView;
    URL url;
    HttpURLConnection httpURLConnection;
    public void downloadImageButton (View view) throws ExecutionException, InterruptedException {
        imageView = (ImageView) findViewById(R.id.imageView);
        DownloadImage downloadImage = new DownloadImage();
        imageView.setImageBitmap(downloadImage.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get());

    }
    public class DownloadImage extends AsyncTask <String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();
            return  null;
            } catch (IOException e) {
                e.printStackTrace();
            return null;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}