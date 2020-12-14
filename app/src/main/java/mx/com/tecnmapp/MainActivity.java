package mx.com.tecnmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    protected static String url = "https://www.itmatamoros.edu.mx/?rest_route=/wp/v2/posts";
    private String UTF_8_ENCODING_TYPE = "UTF-8";
    private String TYPE_TEXT_HTML = "text/html";
    private final int NUMERO_POSTS = 4;
    private WebView webView[];
    private TextView TextView_Titulo[];
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = new WebView[ NUMERO_POSTS ];
        TextView_Titulo = new TextView[ NUMERO_POSTS ];

        webView[0] = (WebView) findViewById(R.id.WebView_Contenido1);
        webView[1] = (WebView) findViewById(R.id.WebView_Contenido2);
        webView[2] = (WebView) findViewById(R.id.WebView_Contenido3);
        webView[3] = (WebView) findViewById(R.id.WebView_Contenido4);

        TextView_Titulo[0] = (TextView) findViewById(R.id.TextView_Encabezado1);
        TextView_Titulo[1] = (TextView) findViewById(R.id.TextView_Encabezado2);
        TextView_Titulo[2] = (TextView) findViewById(R.id.TextView_Encabezado3);
        TextView_Titulo[3] = (TextView) findViewById(R.id.TextView_Encabezado4);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.ToolbarMain);
        setSupportActionBar(toolbar);
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.ic_menu_24dp));
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_down_white_24));

        View logo = toolbar.getChildAt(1);
        logo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista) {
                openDrawer(drawerLayout);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < NUMERO_POSTS; i++) {
                                JSONObject PUBLICACION = response.getJSONObject(i+1);
                                JSONObject TITULO = PUBLICACION.getJSONObject("title");
                                JSONObject CONTENIDO = PUBLICACION.getJSONObject("content");

                                String html = CONTENIDO.getString("rendered");
                                String titulo = TITULO.getString("rendered");

                                TextView_Titulo[i].setText(Html.fromHtml(titulo, Html.FROM_HTML_MODE_COMPACT));

                                webView[i].getSettings().setJavaScriptEnabled(true);
                                webView[i].getSettings().setLoadsImagesAutomatically(true);
                                webView[i].getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                                webView[i].loadDataWithBaseURL(
                                        null,
                                        html,
                                        TYPE_TEXT_HTML,
                                        UTF_8_ENCODING_TYPE,
                                        null
                                );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
            }
        });
        queue.add(request);
    }

    @Override
    public void onPause(){
        super.onPause();
        closeDrawer( drawerLayout );
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Salir");
        builder.setMessage("Estas Seguro que quieres Salir");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void ClickLogo(View view){ closeDrawer( drawerLayout ); }
    public void ClickHome(View view){ closeDrawer( drawerLayout ); }

    public void ClickInfoTec(View view){
        redirectActivity(this, InfoTec.class);
    }
    public void ClickCroquis(View view){
        redirectActivity(this, Croquis.class);
    }
    public void ClickCalendario(View view){ redirectActivity(this, Calendario.class); }
    public void ClickBecas(View view){ redirectActivity(this, Becas.class); }
    public void ClickExtraEscolares(View view){
        redirectActivity(this, ExtraEscolares.class);
    }
    public void ClickConvocatoria(View view){
        redirectActivity(this, Convocatoria.class);
    }
    public void ClickLogout(View view){ logout(this); }
}
