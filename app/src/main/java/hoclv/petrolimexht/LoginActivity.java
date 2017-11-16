package hoclv.petrolimexht;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rlLogin;
    private TextView tvVersion, tvVersion1;
    private ScrollView login_form;
    private ViewGroup viewGroup;
    private String strUserName = "";
    private String strUserPass = "";
    private ProgressDialog dlgLoading = null;
    private Button btnLogin;
    private EditText edtUserName, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    public void initView() {
        viewGroup = findViewById(android.R.id.content);
        tvVersion = findViewById(R.id.tvVersion);
        tvVersion1 = findViewById(R.id.tvVersion1);
        login_form = findViewById(R.id.login_form);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edtPass = findViewById(R.id.edtPass);
        edtUserName = findViewById(R.id.edtUserName);


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        if (width > height && height < 1536) {
            tvVersion.setVisibility(View.GONE);
            tvVersion1.setVisibility(View.VISIBLE);
        } else {
            tvVersion.setVisibility(View.VISIBLE);
            tvVersion1.setVisibility(View.GONE);
            viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
        }

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            tvVersion.setText(getResources().getString(R.string.version) + " " + versionName);
            tvVersion1.setText(getResources().getString(R.string.version) + " " + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isFirstShow = true;
    private int firstDiffHeight = 0;
    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            int rootHeight = viewGroup.getRootView().getHeight();
            int currentHeight = viewGroup.getHeight();
            if (isFirstShow) {
                firstDiffHeight = rootHeight - currentHeight;//413
                isFirstShow = false;
            }
            int heightDiff = rootHeight - currentHeight;
            if (heightDiff > firstDiffHeight + 200) {
                tvVersion.setVisibility(View.GONE);
            } else {
                tvVersion.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
            default:
                break;
        }
    }
}
