package net.tomlins.demo.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    private FaceViewFragment faceViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "In onCreate");

        faceViewFragment = new FaceViewFragment();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, faceViewFragment).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.i(TAG, "In onCreateOptionsMenu");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Toast.makeText(getApplicationContext(), "onCreateOptionsMenu", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.i(TAG, "In onOptionsItemSelected");

        switch (item.getItemId()) {
            case R.id.action_settings:
                MainActivity.this.finish();
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder faceViewFragment containing a simple view.
     */
    public static class FaceViewFragment extends Fragment {

        public FaceViewFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "In onStart");
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();

        View fragView = faceViewFragment.getView();
        ImageView myFace = (ImageView) fragView.findViewById(R.id.splash_anim);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_rotate_scale);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Thread(new Runnable() {
                    public void run(){
                        final MediaPlayer myMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tada);
                        myMediaPlayer.start();
                    }
                }).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        myFace.startAnimation(anim);

        String[] list_items = {
                getResources().getString(R.string.list_item_1),
                getResources().getString(R.string.list_item_2),
                getResources().getString(R.string.list_item_3)
        };

        ArrayAdapter<String> adapt =
                new ArrayAdapter<String>(this, R.layout.list_item, list_items);

        ListView listView = (ListView)findViewById(R.id.list_view);

        listView.setAdapter(adapt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyAlertDialog myAlertDialog = null;
                TextView textView = (TextView)view;
                String text = textView.getText().toString();
                if(text.equalsIgnoreCase(getResources().getString(R.string.list_item_1))) {
                    myAlertDialog = MyAlertDialog.newInstance("Thank you!");
                } else if(text.equalsIgnoreCase(getResources().getString(R.string.list_item_2))) {
                    myAlertDialog = MyAlertDialog.newInstance("Ouch!");
                } else {
                    myAlertDialog = MyAlertDialog.newInstance("I'm not the messiah");
                }
                myAlertDialog.show(getFragmentManager(), "tag");
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "In onRestart");
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "In onResume");
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "In onPause");
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "In onStop");
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "In onDestroy");
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }


}
