package mx.com.tecnmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Politicas extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.ToolbarPoliticas);
        setSupportActionBar(toolbar);

        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.ic_menu_24dp));
        toolbar.setOverflowIcon(ContextCompat.getDrawable( this, R.drawable.ic_arrow_down_white_24 ));

        View logo = toolbar.getChildAt(1);
        logo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista) {
                MainActivity.openDrawer(drawerLayout);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_infotec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch ( item.getItemId() ) {
            case R.id.uno:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/
    public void ClickLogo(View view){ MainActivity.closeDrawer( drawerLayout ); }
    public void ClickHome(View view){ MainActivity.redirectActivity( this, MainActivity.class ); }
    public void ClickInfoTec(View view){
        MainActivity.redirectActivity(this, InfoTec.class);
    }
    public void ClickCroquis(View view){
        MainActivity.redirectActivity(this, Croquis.class);
    }
    public void ClickCalendario(View view){ MainActivity.redirectActivity(this, Calendario.class); }
    public void ClickBecas(View view){ MainActivity.redirectActivity(this, Becas.class); }
    public void ClickExtraEscolares(View view){ MainActivity.redirectActivity(this, ExtraEscolares.class); }
    public void ClickConvocatoria(View view){ MainActivity.redirectActivity(this, Convocatoria.class); }
    public void ClickLogout(View view){ MainActivity.logout(this); }
}
