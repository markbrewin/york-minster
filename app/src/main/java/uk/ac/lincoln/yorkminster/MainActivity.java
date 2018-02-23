package uk.ac.lincoln.yorkminster;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAR(View view)
    {
        Intent intent = new Intent(MainActivity.this, ARActivity.class);
        startActivity(intent);
    }
}

