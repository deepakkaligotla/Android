package in.kaligotla.day03;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
    }

    /*
    Constraint Layout is set of rules.
    There are 4 constraints to be applied to each of the views,
    minimum 2 are required along with width (or) height

    there are 6 units to design view
        *use this* dpi - density per inch (width height or dimensions) - density independent
        *use this* sp  - scale per pixel (text size) - scale independent
        * ppi - resolution - no of pixels per inch
        * mm  - millimeter
        * px  - pixel
        * pt  - point
    */
}