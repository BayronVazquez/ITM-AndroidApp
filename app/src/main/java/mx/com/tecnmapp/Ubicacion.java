package mx.com.tecnmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Ubicacion extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.ToolbarUbicacion);
        setSupportActionBar(toolbar);

        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.ic_menu_24dp));
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_down_white_24));

        View logo = toolbar.getChildAt(1);
        logo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista) {
                MainActivity.openDrawer(drawerLayout);
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        MainActivity.closeDrawer( drawerLayout );
    }

    // Controladores de Eventos en Menu
    //public void ClickMenu(View view){ MainActivity.openDrawer( drawerLayout ); }
    public void ClickLogo(View view){ MainActivity.closeDrawer( drawerLayout ); }
    public void ClickHome(View view){ MainActivity.redirectActivity(this, MainActivity.class); }
    public void ClickPoliticas(View view){ MainActivity.redirectActivity(this, Politicas.class); }
    public void ClickUbicacion(View view){ recreate(); }
    public void ClickExtraEscolares(View view){ MainActivity.redirectActivity(this, ExtraEscolares.class); }
    public void ClickCroquis(View view){ MainActivity.redirectActivity(this, Croquis.class); }
    public void ClickCalendario(View view){ MainActivity.redirectActivity(this, Calendario.class); }
    public void ClickLogout(View view){ MainActivity.logout(this); }
    // FIN Controladores de Eventos en Menu

}
