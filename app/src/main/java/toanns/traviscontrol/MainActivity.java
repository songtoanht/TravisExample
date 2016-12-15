package toanns.traviscontrol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mDownload;
    private TextView mTvPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDownload = (Button) findViewById(R.id.btnDownload);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mTvPercent = (TextView) findViewById(R.id.tvPercent);

        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Download task = new Download();
//                task.execute();
                startProgress(v);
            }
        });

    }

    public void startProgress(View view) {
        mProgressBar.setProgress(0);
        new Thread(new Task()).start();
    }

    class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {

                final int value= i;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvPercent.setText(value+"");
                        mProgressBar.setProgress(value);
                    }
                });
            }
        }
    }

    class Download extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            int value = 0;
            for (int i = 0; i <= 10; i++) {
                value = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            publishProgress(value+"");
            return value + "";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            mTvPercent.setText(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mTvPercent.setText(s);
        }
    }
}
