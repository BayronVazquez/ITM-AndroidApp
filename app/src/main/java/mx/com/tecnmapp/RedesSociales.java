package mx.com.tecnmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RedesSociales extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Button btnFacebook, btnWhatsApp;
    public static String FACEBOOK_URL = "https://www.facebook.com/tecnmcampusmatamoros/";
    public static String FACEBOOK_PAGE_ID = "tecnmcampusmatamoros";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes_sociales);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.ToolbarRedesSociales);
        btnFacebook = (Button)findViewById(R.id.btnFacebook);
        btnWhatsApp = (Button)findViewById(R.id.btnWhatsApp);

        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.ic_menu_24dp));
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_down_white_24));
        setSupportActionBar(toolbar);

        View logo = toolbar.getChildAt(1);
        logo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista) {
                MainActivity.openDrawer(drawerLayout);
            }
        });
    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    public void openFacebook(View view){
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    public void openWhatsApp(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+527851012803"));
        startActivity(intent);
    }

/*  Bug pendiente de resolver se crashea por instancia NULL en drawer layout
    @Override
    public void onPause(){
        super.onPause();
        MainActivity.closeDrawer( drawerLayout );
    }
*/
    public void ClickLogo(View view){ MainActivity.closeDrawer( drawerLayout ); }
    public void ClickHome(View view){ MainActivity.closeDrawer( drawerLayout ); }

    public void ClickInfoTec(View view){
        MainActivity.redirectActivity(this, InfoTec.class);
    }
    public void ClickCroquis(View view){
        MainActivity.redirectActivity(this, Croquis.class);
    }
    public void ClickCalendario(View view){ MainActivity.redirectActivity(this, Calendario.class); }
    public void ClickBecas(View view){ MainActivity.redirectActivity(this, Becas.class); }
    public void ClickExtraEscolares(View view){ MainActivity.redirectActivity(this, ExtraEscolares.class); }
    public void ClickConvocatoria(View view){
        MainActivity.redirectActivity(this, Convocatoria.class);
    }
    public void ClickLogout(View view){ MainActivity.logout(this); }
}