package mx.com.tecnmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InfoTec extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tec);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar)findViewById(R.id.ToolbarInfotec);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.infotec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.Item_Politicas:
                MainActivity.redirectActivity( this, Politicas.class );
                return true;
            case R.id.Item_Ubicacion:
                MainActivity.redirectActivity( this, Ubicacion.class );
                return true;
            case R.id.Item_RedesSociales:
                MainActivity.redirectActivity( this, RedesSociales.class );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

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