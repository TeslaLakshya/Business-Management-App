package rashitrends.rashitrends;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class HomePlaceOrderActivity extends AppCompatActivity implements
        itemsfragment.OnFragmentInteractionListener,
        OrderFragment.OnFragmentInteractionListener,
        Customerfragment.OnFragmentInteractionListener,
        Employeesfragment.OnFragmentInteractionListener{

    itemsfragment itemsfragment = new itemsfragment();
    OrderFragment orderFragment = new OrderFragment();
    Employeesfragment employeesfragment = new Employeesfragment();
    Customerfragment customerfragment = new Customerfragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_place_order);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        getSupportFragmentManager().beginTransaction().
                add(R.id.container1, new itemsfragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.items :
                        selectedFragment = new itemsfragment();
                        break;

                    case R.id.order :
                        selectedFragment = new OrderFragment();
                        break;

                    case R.id.customers :
                        selectedFragment = new Customerfragment();
                        break;

                    case R.id.employees :
                        selectedFragment = new Employeesfragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container1, selectedFragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
